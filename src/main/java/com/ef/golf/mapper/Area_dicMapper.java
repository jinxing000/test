package com.ef.golf.mapper;

import com.ef.golf.pojo.Area_dic;

import java.util.List;

public interface Area_dicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Area_dic record);

    int insertSelective(Area_dic record);

    Area_dic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Area_dic record);

    int updateByPrimaryKey(Area_dic record);

    //查询国内所有的城市
    List<Area_dic> selectAllCityInChina();

    //查询国外所有的城市
    List<Area_dic> selectAllCityInForeign();
}