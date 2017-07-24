package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Records;
import com.kaishengit.crm.entity.Sale;
import com.kaishengit.crm.exception.NotFoundException;
import com.kaishengit.crm.exception.NotYouException;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.RecordsService;
import com.kaishengit.crm.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/record")
public class RecordsController {

    @Autowired
    private RecordsService recordsSerrvice;
    @Autowired
    private SaleService saleService;
    @Autowired
    private CustomerService customerService;

    /**
     * 显示跟进记录详情
     */
    @GetMapping("/my/{id:\\d+}")
    public String list(Model model, @PathVariable String id, HttpSession session){
        Account account= (Account) session.getAttribute("acc");
        Sale sale=saleService.findById(id);
        Customer customer=customerService.findById(sale.getCustomerId().toString());
        if(customer==null){
            throw new NotFoundException();
        }
        if(!customer.getAccountId().equals(account.getId())){
            throw new NotYouException();
        }
        //返回进度
        model.addAttribute("progressList",saleService.getProgressList());
        //返回客户信息
        model.addAttribute("customer",customer);
        //返回跟进记录
        model.addAttribute("records",recordsSerrvice.findAll(sale));
        return "";

    }
    /**
     * 更改跟进记录
     *
     */
    @PostMapping("")
    public String update(Sale sale, HttpSession session, Customer customer){
        Account account= (Account) session.getAttribute("acc");

        if(customer==null){
            throw new NotFoundException();
        }
        if(!customer.getAccountId().equals(account.getId())){
            throw new NotYouException();
        }
       saleService.update(sale);
        return "";
    }
    /**
     * 删除销售机会
     */
    @GetMapping("")
    public String del(Sale sale,HttpSession session){
        Account account= (Account) session.getAttribute("acc");
        Customer customer=customerService.findById(sale.getCustomerId().toString());
        if(customer==null){
            throw new NotFoundException();
        }
        if(!customer.getAccountId().equals(account.getId())){
            throw new NotYouException();
        }
        saleService.del(sale);
        return "";
    }


}
