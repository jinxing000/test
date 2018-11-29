package com.ef.golf.service.impl;


import com.ef.golf.mapper.AppVersionMapper;
import com.ef.golf.pojo.AppVersion;
import com.ef.golf.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/10/5 15:14
 */
@Service
public class AppversionServiceImpl implements AppVersionService {

    @Autowired
    private AppVersionMapper appVersionMapper;

    @Override
    public Map<String,Object> findAppVersionByPlatform(String platform, String versionName) {

        Map<String,Object>map = new HashMap<>();

        int state = 0;

        AppVersion appVersion = appVersionMapper.findAppVersionByPlatform(platform);

        if(!versionName.equals(appVersion.getVersionName())){
            state = appVersion.getState();
        }
        map.put("state",state);
        map.put("url",platform.equals("Android")?appVersion.getPackageUrl():appVersion.getShopUrl());
        return map;
    }
}
