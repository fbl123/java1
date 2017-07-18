package com.kaishengit.crm.controller;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.AccountDept;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.service.AccountDeptService;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.crm.service.DeptService;
import com.kaishengit.dto.Result;
import com.kaishengit.dto.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.google.common.collect.Collections2;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
@Controller
@RequestMapping("/manage/account")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountDeptService accountDeptService;

    @GetMapping("/depts.json")
    public String look(){
        return "manage/accounts";
    }

    @PostMapping("/depts.json")
    @ResponseBody

    public List<ZTreeNode> find(){
        List<Dept> list=deptService.findAll();
        List<ZTreeNode> nodeList = Lists.newArrayList(Collections2.transform(list, new Function<Dept,ZTreeNode>() {
            @Nullable
            @Override
            public ZTreeNode apply(@Nullable Dept dept) {
                ZTreeNode node = new ZTreeNode();
                node.setId(dept.getId());
                node.setName(dept.getDeptName());
                node.setpId(dept.getpId());
                return node;
            }
        }));
       /* List<ZTreeNode> zTreeNodeList=new ArrayList<>();
        for(Dept dept:list){
            ZTreeNode zTreeNode=new ZTreeNode();
            zTreeNode.setId(dept.getId());
            zTreeNode.setName(dept.getDeptName());
            zTreeNode.setpId(dept.getpId());
            zTreeNodeList.add(zTreeNode);
        }*/
        return nodeList;
    }

    @PostMapping("/dept/new")
    @ResponseBody
    public Result save(Dept dept){
        deptService.save(dept);
        return Result.success();
    }


    @PostMapping("/acc/add")
    @ResponseBody

    public Result save(Account account, Integer[] deptId){
        accountService.save(account,deptId);

        return Result.success();
    }


}
