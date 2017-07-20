package com.kaishengit.crm.controller;

import com.google.common.collect.Maps;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.dto.Result;
import com.kaishengit.dto.StringUtil;
import com.kaishengit.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


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
           redirectAttributes.addAttribute("message","添加成功");
           return "redirect:/customer/my";
       }catch(ServiceException e){
           redirectAttributes.addAttribute("message",e.getMessage());
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
     * 更改客户
     * 转让客户或将各户放到公海中
     */
    public Result update(Customer customer){
        customerService.update(customer);
        return Result.success();
    }


}


