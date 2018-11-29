package com.ef.golf.mapper;


import com.ef.golf.pojo.Syslog;

public interface SyslogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Syslog record);

    int insertSelective(Syslog record);

    Syslog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Syslog record);

    int updateByPrimaryKey(Syslog record);
}