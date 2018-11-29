package com.ef.golf.mapper;

import com.ef.golf.pojo.Logs;

public interface LogsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Logs record);

    int insertSelective(Logs record);

    Logs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Logs record);

    int updateByPrimaryKey(Logs record);
}