package com.ef.golf.mapper;


import com.ef.golf.pojo.Withdrawal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WithdrawalMapper {
    int deleteByPrimaryKey(Integer withdrawalId);

    int insert(Withdrawal record);

    int insertSelective(Withdrawal record);

    Withdrawal selectByPrimaryKey(Integer withdrawalId);

    int updateByPrimaryKeySelective(Withdrawal record);

    int updateByPrimaryKey(Withdrawal record);

    List<Withdrawal>getWithdrawalRecordByUserId(@Param("userId") Integer userId,@Param("state") String state,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    Integer getWithdrawalRecordByUserIdCount(Integer userId);
}