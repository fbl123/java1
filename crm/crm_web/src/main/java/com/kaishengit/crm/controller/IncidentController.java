package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Incident;
import com.kaishengit.crm.mapper.IncidentMapper;
import com.kaishengit.crm.service.IncidentService;
import com.kaishengit.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/task")
public class IncidentController {
    @Autowired
    IncidentService incidentService;
    /**
     * 添加事件
     */
    @GetMapping("/new")
    public String save(){
        return "task/new";
    }


    @PostMapping("/new")
    public String save(Incident incident){
        incidentService.save(incident);
        return "redirect:/task";
    }
    /**
     * 查看我的代办事件
     */
    @GetMapping
    public ModelAndView list(HttpSession session, @RequestParam(required = false,defaultValue = "") String show){
        ModelAndView modelAndView=new ModelAndView();
        Account account= (Account) session.getAttribute("acc");
        List<Incident> list=incidentService.findByAccId(account.getId(),show);
        modelAndView.setViewName("task/home");
        modelAndView.addObject("IncidentList",list);

        return  modelAndView;
    }


    /**
     * 删除代办事件
     */
    @GetMapping("/{id:\\d+}/del")
    public String del(@PathVariable Integer id){
        incidentService.delById(id);
        return "redirect:/task";
    }
    /**
     * 修改状态
     */
    @GetMapping("/{id:\\d+}/state/{done}")
    public String update(@PathVariable("id") String id,@PathVariable("done") String done){
        Incident incident= incidentService.findById(id);
        if(done.equals("done")){
            incident.setState(true);
        }else{
            incident.setState(false);
        }
        incidentService.update(incident);

       return "redirect:/task";
    }


}
