package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.google.zxing.WriterException;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Incident;
import com.kaishengit.crm.entity.Sale;
import com.kaishengit.crm.exception.NotFoundException;
import com.kaishengit.crm.exception.NotYouException;
import com.kaishengit.crm.service.*;
import com.kaishengit.dto.StringUtil;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.utils.QrCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RecordsService recordsService;
    @Autowired
    private SaleService saleService;
    @Autowired
    private  IncidentService incidentService;


    /**
     * 显示我的客户
     * @keyword 查询条件
     */
    @GetMapping("/my")
    public String myCusteomer(Model model,HttpSession session,String keyword){

        keyword= StringUtil.utf8(keyword);
        Map<String,Object> map= Maps.newHashMap();
        map.put("keyword",keyword);
        Account account= (Account) session.getAttribute("acc");
      List<Customer> custList=customerService.findMyCust(account,map);
      model.addAttribute("myCustomer",custList);
      model.addAttribute("keyword",keyword);
        return "customer/my_home";
    }
    /**
     * 导出Excel
     */
    @GetMapping("/my/export")
    public void export(HttpServletResponse response,HttpSession session )throws IOException{
        Account account= (Account) session.getAttribute("acc");
        response.setContentType("application/vnd.ms-excel");
        //设置文件名称
        response.addHeader("Content-Disposition","attachment;filename=\"customer.xls\"");
        customerService.export(account,response.getOutputStream());
    }
    /**
     * 添加客户
     */
    @GetMapping("/my/new")
    public String save(Model model){
        model.addAttribute("sourceList",customerService.findAllSource());
        model.addAttribute("tradeList",customerService.findAllTrade());

        return "customer/new_mycustomer";
    }
    @RequestMapping(value = "/my/new",method = RequestMethod.POST)
//    @ResponseBody
    public String save(Customer customer, HttpSession session, RedirectAttributes redirectAttributes){
        Account account= (Account) session.getAttribute("acc");
       try{
           customerService.save(customer,account);
           redirectAttributes.addFlashAttribute("message","添加成功");
           return "redirect:/customer/my";
       }catch(ServiceException e){
           redirectAttributes.addFlashAttribute("message",e.getMessage());
           return "redirect:customer/my/new";
       }

   /*
       try{
           customerService.save(customer,account);
           return Result.success();
       }catch (ServiceException e){
           return Result.error(e.getMessage());
       }*/


    }


    /**
     * 显示
     */
    //
    @GetMapping("/my/{id}")
    public String update(Model model,HttpSession session,@PathVariable String id){
        Customer customer=findByid(id);
        Account account= (Account) session.getAttribute("acc");
        isMy(account,customer);
        model.addAttribute("customer",customer);
        model.addAttribute("accountList",accountService.findAll());
        //销售机会
        List<Sale> list= saleService.findByCustomer(customer);
        model.addAttribute("records",list);
        //日程安排
        List<Incident> list1= incidentService.findByCustId(customer.getId());
        model.addAttribute("taskList",list1);
        //TODO 相关资料

       return "customer/info";
    }

    /**
     * 修改客户信息
     *
     * @param session
     * @return
     */
    @GetMapping("/my/{id:\\d+}/edit")
    public String update(@PathVariable String id, Model model,HttpSession session){
        Customer customer=findByid(id);
        Account account= (Account) session.getAttribute("acc");
        isMy(account,customer);
        model.addAttribute("customer",customer);
        model.addAttribute("tradeList",customerService.findAllTrade());
        model.addAttribute("sourceList",customerService.findAllSource());
        return "customer/edit_mycustomer";
    }

    @PostMapping("/my/edit")
    public String update(Customer customer,HttpSession session,RedirectAttributes redirectAttributes){
        //判断是否存在
        findByid(customer.getId().toString());
        //判断是否属于当前员工
        Account account= (Account) session.getAttribute("acc");
        isMy(account,customer);
        customerService.update(customer,account,account,"修改客户信息");
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/customer/my/"+customer.getId();

    }

    /**
     * 删除
     * @param session
     * @param id
     * @return
     */
    @GetMapping("/my/{id}/del")
    public String del(HttpSession session,@PathVariable String id){
        //判断是否存在
        Customer customer= findByid(id);
        Account account= (Account) session.getAttribute("acc");
//        isMy(account,customer);

        isMy(account,customer);
        customerService.del(customer);
        return "redirect:/customer/my";
    }

    /**
     * 将客户放到公海中
     *
     * @param
     * @return
     */
    @GetMapping("/my/{id:\\d+}/share/public")
    public String giveUp(@PathVariable String id,HttpSession session,RedirectAttributes redirectAttributes){
        Customer customer=findByid(id);
        Account account= (Account) session.getAttribute("acc");
        isMy(account,customer);
        customerService.update(customer,account,new Account(),"放入到公海");
        redirectAttributes.addFlashAttribute("message","已成功将"+customer.getCustName()+"放入公海中");
        return "redirect:/customer/my";
    }

    /**
     * 转交客户
     * @param
     * @return
     */
    @GetMapping("/my/{custId:\\d+}/tran/{accountId:\\d+}")
    public String  transfer(HttpSession session,@PathVariable String custId,@PathVariable String accountId,
                            RedirectAttributes redirectAttributes){
        Customer customer=findByid(custId);
        Account account= (Account) session.getAttribute("acc");
        isMy(account,customer);
       Account account1= accountService.findById(accountId);
       customerService.update(customer,account,account1,"转交");
        redirectAttributes.addFlashAttribute("message","成功将客户[ "+customer.getCustName()+" ]转移");
        return "redirect:/customer/my";
    }




    //判断是否存在客户
    public Customer findByid(String id){
        Customer customer=customerService.findById(id);
        if(customer==null){
            throw new NotFoundException();
        }
        return customer;
    }
    //判断是否属于该员工或是公海客户
    public static void isMy(Account account,Customer customer){
        if(account.getId().equals(customer.getAccountId())||customer.getAccountId()==null){
            return;
        }else{
            throw new NotYouException();
        }

    }


    /**
     * 显示公海客户
     */
    @GetMapping("/public")
    public String shared(Model model,@RequestParam(required = false,defaultValue="1",value = "p") Integer p,
                        String keyword){
        Integer size=3;

        PageInfo<Customer> customerList=customerService.findCustomerByNull(p,size,keyword);

        model.addAttribute("customerList",customerList);
        model.addAttribute("keyword",keyword);
        return "customer/public";
    }
    /**
     * 添加公海客户
     */
    @GetMapping("/public/new")
    public String newPublic(Model model){
        model.addAttribute("sourceList",customerService.findAllSource());
        model.addAttribute("tradeList",customerService.findAllTrade());
        return "customer/new_public";
    }
    @PostMapping("public/new")
    public String newPublic(Customer customer,RedirectAttributes redirectAttributes){
        try{
            customerService.save(customer,new Account());
            redirectAttributes.addFlashAttribute("message","添加成功");
            return "redirect:/customer/public";
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:customer/public/new";
        }

    }



    /**
     * 将客户电话生成二维码
     * 显示客户二维码图片
     */
    @GetMapping("/my/qrcode/{id:\\d+}")
    public void showCustomerQRCode(@PathVariable Integer id,HttpServletResponse response) {
        Customer customer = customerService.findById(id.toString());

        response.setContentType("image/png");

        //vcard 格式 https://zxing.appspot.com/generator
        StringBuffer str = new StringBuffer();
        str.append("BEGIN:VCARD\r\n");
        str.append("VERSION:3.0\r\n");
        str.append("N:").append(customer.getCustName()).append("\r\n");
        str.append("TITLE:").append(customer.getJob()).append("\r\n");
        str.append("TEL:").append(customer.getTell()).append("\r\n");
        str.append("ADR:").append(customer.getAddress()).append("\r\n");
        str.append("END:VCARD\r\n");

        try {
            OutputStream outputStream = response.getOutputStream();
            QrCodeUtil.writeToStream(str.toString(), outputStream, 300, 300);
            outputStream.flush();
            outputStream.close();
        } catch (IOException|WriterException ex) {
            throw new RuntimeException("渲染二维码失败",ex);
        }
    }

}


