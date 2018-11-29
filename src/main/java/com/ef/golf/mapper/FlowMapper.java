package com.ef.golf.mapper;

import com.ef.golf.pojo.Flow;

import java.util.List;
import java.util.Map;

public interface FlowMapper {
    int deleteByPrimaryKey(Integer flowId);

    int insert(Flow record);

    int insertSelective(Flow record);

    Flow selectByPrimaryKey(Integer flowId);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);

    List<Flow> getFlowRecord(Map map);
    Integer getFlowRecordCount(Map map);
    Double getTodayZenSongMoney(Integer userId);
}