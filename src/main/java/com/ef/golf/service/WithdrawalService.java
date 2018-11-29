package com.ef.golf.service;

import com.ef.golf.pojo.Withdrawal;
import com.ef.golf.util.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/6/15 20:11
 */
public interface WithdrawalService {
    int deleteByPrimaryKey(Integer withdrawalId);

    int insert(Withdrawal record);

    int insertSelective(Withdrawal record);

    Withdrawal selectByPrimaryKey(Integer withdrawalId);

    int updateByPrimaryKeySelective(Withdrawal record);

    int updateByPrimaryKey(Withdrawal record);

    PageBean getWithdrawalRecordByUserId(Integer userId, String state, Integer pageNo, Integer pageSize);
}
