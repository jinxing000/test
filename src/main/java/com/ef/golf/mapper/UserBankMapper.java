package com.ef.golf.mapper;


import com.ef.golf.pojo.UserBank;

public interface UserBankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserBank record);

    int insertSelective(UserBank record);

    UserBank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBank record);

    int updateByPrimaryKey(UserBank record);

    UserBank getUserBankDetails(Long userId);
}