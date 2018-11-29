package com.ef.golf.mapper;

import com.ef.golf.pojo.Hotcity;

import java.util.List;

public interface HotcityMapper {
    int deleteByPrimaryKey(Integer hotcityId);

    int insert(Hotcity record);

    int insertSelective(Hotcity record);

    Hotcity selectByPrimaryKey(Integer hotcityId);

    int updateByPrimaryKeySelective(Hotcity record);

    int updateByPrimaryKey(Hotcity record);

    List<Hotcity> getHotCitys();
}