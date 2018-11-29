package com.ef.golf.mapper;

import com.ef.golf.pojo.GoodsCat;
import com.ef.golf.vo.GoodsCatVo;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.List;
import java.util.Map;

public interface GoodsCatMapper {
    int deleteByPrimaryKey(Integer catId);

    int insert(GoodsCat record);

    int insertSelective(GoodsCat record);

    GoodsCat selectByPrimaryKey(Integer catId);

    int updateByPrimaryKeySelective(GoodsCat record);

    int updateByPrimaryKey(GoodsCat record);

    List<GoodsCat> catList();//首页一级分类

    List<GoodsCat> secondCatList(Map map);//二级分类

    List<GoodsCatVo> secondspecCatGoodsList(Map map);//第二次请求分类下的商品

    List<GoodsCatVo> firstspecCatGoodsList(Map map);//第一次请求一级分类下的商品

    int firstCount(Integer cat_id);

    int sencondCount(Integer cat_id);

    Integer getBigCatId(Integer catId);
}