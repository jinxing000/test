package com.ef.golf.service.impl;

import com.ef.golf.mapper.SyslogMapper;
import com.ef.golf.pojo.Syslog;
import com.ef.golf.service.SyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/10/11 9:32
 */
@Service
public class SyslogServiceImpl implements SyslogService {

    @Autowired
    private SyslogMapper syslogMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return syslogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Syslog record) {
        return syslogMapper.insert(record);
    }

    @Override
    public int insertSelective(Syslog record) {
        return syslogMapper.insertSelective(record);
    }

    @Override
    public Syslog selectByPrimaryKey(Long id) {
        return syslogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Syslog record) {
        return syslogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Syslog record) {
        return syslogMapper.updateByPrimaryKey(record);
    }
}
