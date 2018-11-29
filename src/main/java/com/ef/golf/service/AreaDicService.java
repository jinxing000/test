package com.ef.golf.service;

import com.ef.golf.pojo.Area_dic;

import java.util.List;

public interface AreaDicService {

    //查询国内所有的城市
    List<Area_dic> selectAllCityInChina();

    //查询国外所有的城市
    List<Area_dic> selectAllCityInForeign();
}
