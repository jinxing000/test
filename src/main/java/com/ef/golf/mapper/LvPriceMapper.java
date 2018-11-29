package com.ef.golf.mapper;

import com.ef.golf.pojo.LvPrice;

public interface LvPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LvPrice record);

    int insertSelective(LvPrice record);

    LvPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LvPrice record);

    int updateByPrimaryKey(LvPrice record);
}