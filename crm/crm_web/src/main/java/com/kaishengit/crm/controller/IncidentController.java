package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Incident;
import com.kaishengit.crm.service.IncidentService;
import com.kaishengit.dto.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPBinding;
import java.util.List;

@Controller
@RequestMapping("/incident")
public class IncidentController {
    @Autowired
    IncidentService incidentService;
    /**
     * 添加事件
     */
    @PostMapping("")
    public String save(Incident incident, String custId, String saleId, HttpSession session){
        Account account= (Account) session.getAttribute("acc");
        incident.setAccId(account.getId());
        incidentService.save(incident,custId,saleId);
        return "";
    }
    /**
     * 查看我的代办事件
     */
    @GetMapping()
    public ModelAndView list(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        Account account= (Account) session.getAttribute("acc");
        List<Incident> list=incidentService.findByAccId(account.getId());
        modelAndView.setViewName("");
        modelAndView.addObject("",list);

        return  modelAndView;
    }


    /**
     * 删除代办事件
     */
    @GetMapping("")
    public Result del(Integer id){
        incidentService.delById(id);
        return Result.success();
    }
    /**
     * 修改代办事件
     */
    @PostMapping("")
    public Result update(Incident incident){
        incidentService.update(incident);

        return Result.success();
    }


}
