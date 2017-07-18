package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.AccountDept;
import com.kaishengit.crm.service.AccountDeptService;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 添加员工
     */
//   @PostMapping("/acc/add")
//   @ResponseBody
//   @Transactional
//    public Result save(Account account,Integer[] deptIds){
//        accountService.save(account);
//        for(Integer deptid:deptIds){
//            AccountDept accountDept=new AccountDept();
//            accountDept.setDeptId(deptid);
//            accountDept.setAccountId(account.getId());
//            accountDeptService.save(accountDept);
//        }
//       return Result.success();
//   }

}
