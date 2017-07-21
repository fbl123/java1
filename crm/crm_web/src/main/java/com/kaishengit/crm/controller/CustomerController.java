package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.exception.NotFoundException;
import com.kaishengit.crm.exception.NotYouException;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.dto.StringUtil;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;



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
     * 到处Excel
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
           return "customer/my/new";
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
     *
     */
    //
    @GetMapping("/my/{id}")
    public String update(Model model,HttpSession session,@PathVariable String id){
        Customer customer=findByid(id);
        Account account= (Account) session.getAttribute("acc");
        isMy(account,customer);
        model.addAttribute("customer",customer);
        model.addAttribute("accountList",accountService.findAll());
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

        if(!account.getId().equals(customer.getAccountId())){
            throw new NotYouException();
        }

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
    //判断是否属于该员工
    public void isMy(Account account,Customer customer){
        if(account.getId().equals(customer.getAccountId())){
            return;
        }
        throw new NotYouException();
    }


    /**
     * 显示公海客户
     */
    @GetMapping("/public")
    public String shared(Model model,Integer p,Integer pageSize){
        Integer size=10;
        if(StringUtils.isNumeric(pageSize.toString())){
            size=pageSize;
        }
        PageInfo<Customer> customerList=customerService.findCustomerByNull(p,size);
        model.addAttribute("customerList",customerList);
        return "";
    }

}


