package com.ef.golf.service;

import com.ef.golf.pojo.Syslog;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/10/11 9:31
 */
public interface SyslogService {

    int deleteByPrimaryKey(Long id);

    int insert(Syslog record);

    int insertSelective(Syslog record);

    Syslog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Syslog record);

    int updateByPrimaryKey(Syslog record);
}
