package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.dto.Result;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/7/19.
 */
@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;
    /**
     * 登陆页
     * @return
     */
    @GetMapping("/")
    public String login(HttpSession session,RedirectAttributes redirectAttributes){
        session.invalidate();
        return "login";
    }
    /**
     * 验证登陆
     */
    @PostMapping("/")
    @ResponseBody
    public Result login(String mobile, String password, HttpSession session,String callback){
        if(!StringUtils.isNotEmpty(callback)){
            callback="/home";
        }
       try{
           Account account=accountService.findByModile(mobile,password);
           session.setAttribute("acc",account);
           return Result.success(callback,account);
       }catch(ServiceException e){
           return Result.error(e.getMessage());
        }

    }
    @GetMapping("/logout")
    public String out(HttpSession session,RedirectAttributes redirectAttributes){
        session.invalidate();
        return "redirect:/";
    }
    /**
     * 个人设置
     *
     */
    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
    //修改密码
    @PostMapping("/profile")
    @ResponseBody
    public Result profile(String oldPassword,Account acc,HttpSession session){
        Account account= (Account) session.getAttribute("acc");
            try{
                accountService.update(oldPassword,acc,account);
                return Result.success();
            }catch (ServiceException e){
                return Result.error(e.getMessage());
            }




    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }







}
