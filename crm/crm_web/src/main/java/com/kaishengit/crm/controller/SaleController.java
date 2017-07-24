package com.kaishengit.crm.controller;


import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Sale;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.SaleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;
    @Autowired
    private CustomerService customerService;


    /**
     * 添加机会
     */
    @GetMapping("/new")
    public String save(Model model, HttpSession session){
        Account account= (Account) session.getAttribute("acc");
        //去配置文件中获取进度
        model.addAttribute("progressList",saleService.getProgressList());
        //查找我的客户
        model.addAttribute("customerList",customerService.findMyCust(account,new HashMap<>()));
        return "sale/sale_new";
    }
    @PostMapping("/new")
    public String save(Sale sale, HttpSession session,RedirectAttributes redirectAttributes){
        Account account= (Account) session.getAttribute("acc");
        sale.setAccountId(account.getId());
        saleService.save(sale);
        redirectAttributes.addFlashAttribute("message","添加成功");
            return "redirect:/sales/my";


    }
    /**
     * 显示我销售机会
     */
    @GetMapping("/my")
    public String show(Model model, HttpSession session, String p, String size){
        Map<String,Object> map= Maps.newHashMap();
        int pageNo=1;
        int pageSize=5;
        if(StringUtils.isNumeric(p)){
            pageNo=new Integer(p).intValue();
        }
        if(StringUtils.isNumeric(size)){
            pageSize=new Integer(size).intValue();
        }
        map.put("page",pageNo);
        map.put("size",pageSize);
        Account account= (Account) session.getAttribute("acc");
        PageInfo<Sale> pageInfo=saleService.findMySale(account,map);
        model.addAttribute("pageInfo",pageInfo);

        return "sale/sale_my";
    }

}
