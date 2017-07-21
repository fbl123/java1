package com.kaishengit.crm.service.impl;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.AccountDept;
import com.kaishengit.crm.mapper.AccountDeptMapper;
import com.kaishengit.crm.mapper.AccountMapper;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountDeptMapper accountDeptMapper;
    @Value("${salt}")
    private String salt;






    //添加员工
    @Override
    @Transactional
    public void save(Account account,Integer[] integers) {
        account.setPassword(DigestUtils.md5Hex(account.getPassword()+salt));
        account.setCreatTime(new Date());
        accountMapper.save(account);
        for(Integer dd:integers){
            AccountDept accountDept=new AccountDept();
            accountDept.setDeptId(dd);
            accountDept.setAccountId(account.getId());
            accountDeptMapper.save(accountDept);
        }

    }
    //删除员工
    @Override
    @Transactional
    public void delect(Integer id) {
        //删除员工
        accountMapper.delect(id);
        //删除员工对应的部门关系
        accountDeptMapper.delect(id);
    }
    //显示
    @Override
    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    @Override
    public Long count() {
        return accountMapper.count();
    }

    @Override
    public Long countByDeptId(String id) {
        if("10000".equals(id) ||StringUtils.isBlank(id)) {
            return accountMapper.count();
        }
        return accountMapper.countByDeptId(id);
    }

    /**
     *
     * 登陆查询
     */
    @Override
    public Account findByModile(String modile, String password) throws ServiceException{
        Account account=accountMapper.findByModile(modile);
        if(account!=null&&account.getPassword().equals(DigestUtils.md5Hex(password+salt))){

        }else{
            throw new ServiceException("账号或密码错误");
        }

       return account;
    }

    @Override
    public List<Account> findByDeptId(String id) {
        if("10000".equals(id)){
            id=null;
        }
        return accountMapper.findByDeptId(id);
    }

    @Override
    public void update(String oldPassword, Account acc, Account account) throws ServiceException {

        if(!account.getPassword().equals(DigestUtils.md5Hex(oldPassword+salt))){
            throw new ServiceException("密码错误");
        }
        account.setPassword(DigestUtils.md5Hex(acc.getPassword()+salt));
        account.setUpdateTime(new Date());
        accountMapper.update(account);

    }

    @Override
    public Account findById(String id) {
        Account account=accountMapper.findById(id);
        if(account==null){
            throw new ServiceException("该员工不存在");
        }
        return account;


    }


}
