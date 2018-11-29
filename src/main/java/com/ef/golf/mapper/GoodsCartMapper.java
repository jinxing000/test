package com.ef.golf.mapper;

import com.ef.golf.pojo.Activity;
import com.ef.golf.pojo.Goods;
import com.ef.golf.pojo.GoodsCart;
import com.ef.golf.pojo.Product;
import com.ef.golf.vo.CartItem;
import com.ef.golf.vo.GoodsSpecVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsCartMapper {
    Integer deleteByPrimaryKey(Integer cartId);

    Integer insert(GoodsCart record);

    Integer insertSelective(GoodsCart record);

    GoodsCart selectByPrimaryKey(Integer cartId);

    Integer updateByPrimaryKeySelective(GoodsCart record);

    Integer updateByPrimaryKeyWithBLOBs(GoodsCart record);

    Integer updateByPrimaryKey(GoodsCart record);

    public Goods selectGoods(Integer goods_id);//商品
    public Product  selcetProduct (Integer product_id);//货品
    public GoodsCart selcetGoodsCart(Map map);//购物车 传入货品id productId sessionid user_id
    Activity getActivityList();//所有促销活动
    Integer ActivityGoods(Map map);//查询商品是否参加活动 传入商品id 活动id
    Integer ActivityMapOne(Integer activity_id );//返回是否是全部商品促销活动
    Integer isGoodsCart(Map map);//此商品是否已经在购物车 传入product_id  and itemtype  and ( session_id=? or member_id=?)
    Integer updateGoodsCartNum(Map map);//更新购物车数量 传入 num product_id=? and itemtype=?  and ( session_id=? or member_id=? )
    public List<GoodsSpecVo> getGoodsSpecVoList(Integer product_id);//货品规格列表
    public Activity getActivity(Integer activity_id);//查询指定活动
    public Activity getActivityByGoodsId(Integer goods_id);
    public Double totalPrice (Integer user_id);//购物车总价格 也可传session_id
    public Integer totalCount(Integer user_id);//购物车总数量 也可传session_id
    public Integer updateCartNum(Map map);//更新购物车数量 传入 num, cart_id,user_id
    public List<CartItem> getItemList(@Param("user_id") Integer user_id, @Param("jiesuanType")String jiesuanType);
    public Integer updateCartActivity(Integer cart_id);//活动结束更新
    public Integer deleteCart(Map map);//传入会员id 和 购物项id
    Integer updateCheckbox(Map map);
    List<GoodsCart> getAgainList(Integer userId);
    int delectUserCart(Integer userId);

//删除购物车中的已选择的物品
    Integer deleteChooseGoodsCart(Long userId);
}