package com.ef.golf.service;


import com.ef.golf.pojo.AppVersion;

import java.util.Map;

public interface AppVersionService {

    Map<String,Object> findAppVersionByPlatform(String platform, String versionName);
}
