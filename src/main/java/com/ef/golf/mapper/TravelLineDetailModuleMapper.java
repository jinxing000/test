package com.ef.golf.mapper;

import com.ef.golf.pojo.TravelLineDetailModule;

import java.util.List;

public interface TravelLineDetailModuleMapper {
    int deleteByPrimaryKey(Integer lineModuleId);

    int insert(TravelLineDetailModule record);

    int insertSelective(TravelLineDetailModule record);

    TravelLineDetailModule selectByPrimaryKey(Integer lineModuleId);

    int updateByPrimaryKeySelective(TravelLineDetailModule record);

    int updateByPrimaryKey(TravelLineDetailModule record);

    List<TravelLineDetailModule> getModulePicUrlByLineId(Integer lineId);
}