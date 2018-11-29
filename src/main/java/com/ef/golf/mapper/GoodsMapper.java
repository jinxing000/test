package com.ef.golf.mapper;

import com.ef.golf.pojo.Goods;
import com.ef.golf.pojo.GoodsGllery;
import com.ef.golf.pojo.GoodsMarketVo;
import com.ef.golf.pojo.GoodsWithBLOBs;
import com.ef.golf.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(GoodsWithBLOBs record);

    int insertSelective(GoodsWithBLOBs record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(GoodsWithBLOBs record);

    int updateByPrimaryKey(Goods record);

    List<CommentVo> getList(String goodId,Integer page,Integer pageSize);

    int CountCommentVo  (String goodId);

     Goods getGoods(Integer goods_id);//商品详情
     List<GoodsGllery> glleryList(Integer goods_id);//详情中的商品图片轮播
     List<CommentVo>commentList(Map map);//商品评论列表
     int commentCount(Integer goods_id);//某一商品评论总数
     List<CommentPrictureVo> pictureList(Integer goods_id);//商品评论图片列表
     List <GoodsMarketVo> marketList(Integer goods_id);//购物车弹窗
     List<CatVo> brandFirstCat(Integer brand_id);//品牌下的一级分类
     List<GoodsCatVo>brandGoodsList(Map map);//品牌下的一级分类(含三级分类)下的所有商品
     List<GoodsSpecVo> getGoodsSpecVo(Integer goods_id);//商品的所有规格值
     List<GoodsHotSpecVo> getGoodsHotVoList(Map map);//热销商品列表
     Map getGoodsHotTime(Integer goods_id);//热销商品时间

    public List<GoodsHotSpecVo> getSelectGoodsListPage(GoodsHotSpecVo goods);//模糊查询商品
    public  int getGoodsHotVoCount(Map map);//热销商品列表总数

    int brandGoodsCount(Map map);//品牌下的一级分类(含三级分类)下的所有商品总数

    int updateGoods(Map map);
    int updateGoodsStore(Map map);
//根据商品id获得分类id
    Integer getTypeId(Integer goods_id);
//根据商品类型获得商品的规格分类
    List<Specification> getGoodsSpecVoByType(Integer type_id);
// //根据goods_id和type_id获得所有的规格对应的值
    List<SpecValue> getSpecValueByGoodsIdAndTypeId(@Param("goods_id") Integer goods_id,@Param("type_id") Integer type_id);
//热销商品的详情
    GoodsHotSpecVo getGoodsHotSpecVo(Integer goods_id);
//普通商品详情
    GoodsHotSpecVo selectGoodsDetail(Integer goods_id);
    //推荐商品
    List<GoodsHotSpecVo>getGoodsRecommendList(Map map);
    Integer getGoodsRecommendListCount();

}