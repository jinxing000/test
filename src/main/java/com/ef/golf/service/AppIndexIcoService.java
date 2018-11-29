package com.ef.golf.service;

import com.ef.golf.pojo.Logo;

import java.util.List;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/11/3 14:38
 */
public interface AppIndexIcoService {

    List<Logo> findAppIndexIcoByVersion(String platform, String versionName);
}
