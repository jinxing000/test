package com.ef.golf.mapper;

import com.ef.golf.pojo.EsTypeArea;
import com.ef.golf.pojo.EsTypeAreaWithBLOBs;

import java.util.List;

public interface EsTypeAreaMapper {
    int insert(EsTypeArea record);

    int insertSelective(EsTypeArea record);

    List<EsTypeArea> getList(Integer type_id);

    List<EsTypeArea> getListOne();
}