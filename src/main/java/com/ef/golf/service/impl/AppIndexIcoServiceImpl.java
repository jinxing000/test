package com.ef.golf.service.impl;

import com.ef.golf.mapper.LogoMapper;
import com.ef.golf.pojo.Logo;
import com.ef.golf.service.AppIndexIcoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/11/3 14:39
 */
@Service
public class AppIndexIcoServiceImpl implements AppIndexIcoService {

    @Autowired
    private LogoMapper logoMapper;

    @Override
    public List<Logo> findAppIndexIcoByVersion(String platform, String versionName) {
        return logoMapper.findAppIndexIcoByVersion(platform,versionName);
    }
}
