package com.ef.golf.mapper;

import com.ef.golf.pojo.Account;

import java.util.Map;

public interface AccountMapper{
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    Integer getAccountIdByUid(Integer userId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int selectAccountId(Long userId);
    /** 查询用户余额 */
    Account getUserBalance(Integer userId);
    /** 更新余额 */
    int updateUserBalance(Map map);
    /** 更新充值余额 */
    int updateUserCzBalance(Map map);
    /** 更新收入余额 */
    int updateUserSrBalance(Map map);
    /** 更新赠送余额 */
    int updateUserZsBalance(Map map);
    Integer getAccountUserId(Integer accountId);
    Account getAccount(Integer userId);
}