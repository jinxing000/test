package com.ef.golf.service;

import com.ef.golf.pojo.UserBank;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/5/31 10:43
 */
public interface UserBankService {

    int deleteByPrimaryKey(Long id);

    int insert(UserBank record);

    int insertSelective(UserBank record);

    UserBank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBank record);

    int updateByPrimaryKey(UserBank record);

    UserBank getUserBankDetails(Long userId);

}
