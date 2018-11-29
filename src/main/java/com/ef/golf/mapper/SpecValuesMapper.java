package com.ef.golf.mapper;

import com.ef.golf.pojo.Goods;
import com.ef.golf.pojo.GoodsCart;
import com.ef.golf.pojo.Product;
import com.ef.golf.pojo.SpecValues;

import java.util.Map;

public interface SpecValuesMapper {
    int deleteByPrimaryKey(Integer specValueId);

    int insert(SpecValues record);

    int insertSelective(SpecValues record);

    SpecValues selectByPrimaryKey(Integer specValueId);

    int updateByPrimaryKeySelective(SpecValues record);

    int updateByPrimaryKey(SpecValues record);


}