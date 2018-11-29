package com.ef.golf.service;

import com.ef.golf.pojo.*;
import com.ef.golf.vo.CartItem;
import com.ef.golf.vo.GoodsSpecVo;
import org.apache.solr.client.solrj.io.stream.CartesianProductStream;

import java.util.List;
import java.util.Map;

public interface EsGoodsCartService {
 public Goods selectGoods(Integer goods_id);//商品
 public Product  selcetProduct (Integer product_id);//货品
 public GoodsCart selcetGoodsCart(Map map);//购物车 传入货品id productId sessionid user_id
 public Activity getActivityList();//所有促销活动
 public Integer ActivityGoods(Map map);//查询商品是否参加活动 传入商品id 活动id
 public Integer ActivityMap(Integer activity_id);//返回是否是全部商品促销活动
 public Integer  isGoodsCart(Map map);//此商品是否已经在购物车 传入product_id  and itemtype  and ( session_id=? or member_id=?)
 public Integer updateGoodsCartNum(Map map);//更新购物车数量 传入 num product_id=? and itemtype=?  and ( session_id=? or member_id=? )
 public Integer insertSelective(GoodsCart record);//保存购物车
 public List<GoodsSpecVo> getGoodsSpecVoList(Integer product_id);//货品规格列表
 public Map addCart(Product product,Integer num,String session_id,Integer user_id);
 public Integer checkGoodsAct(Map map,Integer activity_id);
 public Activity getActivity(Integer activity_id);//查询指定活动
 public  Integer add(GoodsCart cart,Integer user_id );//
 public  Map<String,Object> getCartData(Integer user_id);//购物车的商品总数，总价格
    public Integer updateCartNum(Map map);//更新购物车数量 传入 num, cart_id,user_id
 public Double totalPrice (Integer user_id);//购物车总价格 也可传session_id
 public Integer totalCount(Integer user_id);//购物车总数量 也可传session_id
    public List<CartItem> getItemList(Integer user_id,String jiesuanType);
    public Integer updateCartActivity(Integer cart_id);//活动结束更新
  public Integer deleteCart(Map map);//传入会员id 和 购物项id
 Integer updateCheckbox(Map map);
 List<GoodsCart> getAgainList(Integer userId);
 Integer delectUserCart(Integer userId);
}
