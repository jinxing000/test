package com.ef.golf.service;

import com.ef.golf.pojo.Account;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 16:02
 */
public interface AccountService {

    long saveAccount(Account account);

    Integer getAccountUserId(Integer accountId);

    int selectAccountId(Long userId);

    Account getUserBalance(Integer userId);
    int updateUserBalance(Integer userId,Double balance);
    int updateUserCzBalance(Integer userId,Double czBalance);
    int updateUserSrBalance(Integer userId,Double srBalance);
    int updateUserZsBalance(Integer userId,Double zsBalance);
    Account getAccount(Integer userId);

    Double getUserTxBalance(Date date,String userType,Integer userId);
}
