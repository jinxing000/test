package com.ef.golf.mapper;


import com.ef.golf.pojo.Poundage;

public interface PoundageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Poundage record);

    int insertSelective(Poundage record);

    Poundage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Poundage record);

    int updateByPrimaryKey(Poundage record);

    Poundage getAllPoundage(String userType);
}