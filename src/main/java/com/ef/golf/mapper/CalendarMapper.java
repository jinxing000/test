package com.ef.golf.mapper;

import com.ef.golf.pojo.Calendar;

public interface CalendarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Calendar record);

    int insertSelective(Calendar record);

    Calendar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Calendar record);

    int updateByPrimaryKey(Calendar record);
}