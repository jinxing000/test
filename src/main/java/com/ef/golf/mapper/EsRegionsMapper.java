package com.ef.golf.mapper;

import com.ef.golf.pojo.EsRegions;

import java.util.List;

public interface EsRegionsMapper {
    int deleteByPrimaryKey(Integer regionId);

    int insert(EsRegions record);

    int insertSelective(EsRegions record);

    EsRegions selectByPrimaryKey(Integer regionId);

    int updateByPrimaryKeySelective(EsRegions record);

    int updateByPrimaryKey(EsRegions record);

    List<EsRegions>getTreeNodes(Integer p_region_id);
}