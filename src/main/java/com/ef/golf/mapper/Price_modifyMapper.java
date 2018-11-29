package com.ef.golf.mapper;

import com.ef.golf.pojo.Price_modify;

public interface Price_modifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Price_modify record);

    int insertSelective(Price_modify record);

    Price_modify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Price_modify record);

    int updateByPrimaryKey(Price_modify record);
}