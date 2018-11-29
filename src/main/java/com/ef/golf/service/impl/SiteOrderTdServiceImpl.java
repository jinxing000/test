package com.ef.golf.service.impl;

import com.ef.golf.mapper.SiteOrderTdMapper;
import com.ef.golf.pojo.SiteOrderTd;
import com.ef.golf.service.SiteOrderTdService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/6/27 13:47
 */
@Repository
public class SiteOrderTdServiceImpl implements SiteOrderTdService {
    @Resource
    private SiteOrderTdMapper siteOrderTdMapper;

    @Override
    public int insertSelective(SiteOrderTd record) {
        return siteOrderTdMapper.insertSelective(record);
    }
}
