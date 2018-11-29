package com.ef.golf.service.impl;

import com.ef.golf.mapper.AccountMapper;
import com.ef.golf.mapper.JiaoYiHuizongMapper;
import com.ef.golf.pojo.Account;
import com.ef.golf.service.AccountService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 16:03
 */

@Repository
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;
    @Resource
    private JiaoYiHuizongMapper jiaoYiHuizongMapper;

    public long saveAccount(Account account) {
        int i = accountMapper.insertSelective(account);
        return i;
    }
    @Override
    public Integer getAccountUserId(Integer accountId) {
        return accountMapper.getAccountUserId(accountId);
    }

    @Override
    public int selectAccountId(Long userId) {
        int end=accountMapper.selectAccountId(userId);
        return end;
    }

    @Override
    public Account getUserBalance(Integer userId) {
        return accountMapper.getUserBalance(userId);
    }

    @Override
    public int updateUserBalance(Integer userId,Double balance) {
        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        map.put("balance",balance);
        return accountMapper.updateUserBalance(map);
    }

    @Override
    public int updateUserCzBalance(Integer userId, Double czBalance) {
        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        map.put("czBalance",czBalance);
        return accountMapper.updateUserCzBalance(map);
    }

    @Override
    public int updateUserSrBalance(Integer userId, Double srBalance) {
        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        map.put("srBalance",srBalance);
        return accountMapper.updateUserSrBalance(map);
    }

    @Override
    public int updateUserZsBalance(Integer userId, Double zsBalance) {
        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        map.put("zsBalance",zsBalance);
        return accountMapper.updateUserZsBalance(map);
    }

    @Override
    public Account getAccount(Integer userId) {
        return accountMapper.getAccount(userId);
    }

    @Override
    public Double getUserTxBalance(Date date,String userType, Integer userId) {
        return jiaoYiHuizongMapper.getUserTxBalance(date,userType,userId);
    }
}
