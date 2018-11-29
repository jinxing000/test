package com.ef.golf.mapper;

import com.ef.golf.pojo.UserSign;

public interface UserSignMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserSign record);

    int insertSelective(UserSign record);

    UserSign selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserSign record);

    int updateByPrimaryKey(UserSign record);

    UserSign getUserSignRecord(Integer userId);
}