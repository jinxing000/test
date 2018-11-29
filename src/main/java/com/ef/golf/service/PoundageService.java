package com.ef.golf.service;

import com.ef.golf.pojo.Poundage;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/6/15 17:07
 */
public interface PoundageService {
    int deleteByPrimaryKey(Integer id);

    int insert(Poundage record);

    int insertSelective(Poundage record);

    Poundage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Poundage record);

    int updateByPrimaryKey(Poundage record);

    Poundage getAllPoundage(String userType);
}
