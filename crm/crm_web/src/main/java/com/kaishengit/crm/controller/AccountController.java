package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.AccountDept;
import com.kaishengit.crm.service.AccountDeptService;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.dto.DataTableResult;
import com.kaishengit.dto.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
@Controller
@RequestMapping("/acc")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountDeptService accountDeptService;

    /**
     * 显示
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
//    @ResponseBody
    public ModelAndView list(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("manage/accounts");
        modelAndView.addObject("list",accountService.findAll());
        return modelAndView;
    }

    @GetMapping("/load.json")
    @ResponseBody
    public DataTableResult load(HttpServletRequest request){
        String draw=request.getParameter("draw");
        String deptId=request.getParameter("deptId");

        String id=null;
        if(StringUtils.isNumeric(deptId)){
            id=deptId;
        }
        //总数
        Long total=accountService.count();
        //获取Account过滤后的数量
        Long filtedTotal = accountService.countByDeptId(id);
        List<Account> list=accountService.findByDeptId(id);

        return new DataTableResult<>(draw,total,filtedTotal,list);
    }




    /**
     * 添加员工
     */
   @PostMapping("/add")
   @ResponseBody
    public Result save(Account account,Integer[] deptId){

        accountService.save(account,deptId);

       return Result.success();
   }
    /**
     * 删除员工
     */
    @PostMapping("/del")
    @ResponseBody
    public Result del(Integer id){
        accountService.delect(id);
        return Result.success();
    }


}
