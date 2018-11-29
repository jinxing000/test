package com.ef.golf.service;

import com.ef.golf.pojo.Goods;
import com.ef.golf.pojo.GoodsGllery;
import com.ef.golf.pojo.GoodsMarketVo;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.*;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    public Goods selectByPrimaryKey(Integer goodsId);

    public List<GoodsGllery> glleryList(Integer goods_id);//详情中的商品图片轮播

    public List<CommentVo>commentList(Map map);//商品评论列表

    public  int commentCount(Integer goods_id);//某一商品评论总数

    public  List<CommentPrictureVo> pictureList(Integer goods_id);//商品评论图片列表

    public List <GoodsMarketVo> marketList(Integer goods_id);//商品购物车选项

    List<CatVo> brandFirstCat(Integer brand_id);//品牌下的一级分类
    List<GoodsCatVo>brandGoodsList(Map map);//品牌下的一级分类(含三级分类)下的所有商品
    List<GoodsSpecVo> getGoodsSpecVo(Integer goods_id);
    List<GoodsHotSpecVo> getGoodsHotVoList(Map map);//热销商品列表
    public Map getGoodsHotTime(Integer goods_id);//热销商品时间
    public List<GoodsHotSpecVo> getSelectGoodsListPage(GoodsHotSpecVo goods);//模糊查询商品
    public  int getGoodsHotVoCount(Map map);//热销商品列表总数
     public int brandGoodsCount(Map map);//品牌下的一级分类(含三级分类)下的所有商品总数
//热销商品详情
    GoodsHotSpecVo getGoodsHotSpecVo(Integer goods_id);
//普通商品详情，结构与热销一样，但是没有活动等时间相关的东西
    GoodsHotSpecVo selectGoodsDetail(Integer goods_id);

    PageBean getGoodsRecommendList(Integer pageNo,Integer pageSize);


}
