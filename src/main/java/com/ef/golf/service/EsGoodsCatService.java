package com.ef.golf.service;

import com.ef.golf.pojo.GoodsCat;
import com.ef.golf.vo.GoodsCatVo;

import java.util.List;
import java.util.Map;

public interface EsGoodsCatService {

   public List<GoodsCat> list();//首页一级分类

    public  List<GoodsCat>  getCatList (Map map);//二级分类

    public  List<GoodsCatVo> getGoodsCatVo (Map map);//第一次请求一级分类下商品

    public  List<GoodsCatVo> getSecondGoodsCatVo (Map map);//二级分类下商品

    public int firstCount(Integer cat_id);

    public  int sencondCount(Integer cat_id);

    Integer getBigCatId(Integer catId);//获取商品分类的一级分类
}
