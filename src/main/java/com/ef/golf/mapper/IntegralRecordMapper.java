package com.ef.golf.mapper;

import com.ef.golf.pojo.IntegralRecord;

import java.util.List;
import java.util.Map;

public interface IntegralRecordMapper {
    int deleteByPrimaryKey(Integer integralRecordId);

    int insert(IntegralRecord record);

    int insertSelective(IntegralRecord record);

    IntegralRecord selectByPrimaryKey(Integer integralRecordId);

    int updateByPrimaryKeySelective(IntegralRecord record);

    int updateByPrimaryKey(IntegralRecord record);

    List<IntegralRecord> getIntegralRecord(Map map);
    Integer getIntegralRecordCount(Map map);

}