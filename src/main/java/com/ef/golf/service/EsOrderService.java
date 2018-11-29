package com.ef.golf.service;

import com.ef.golf.exception.MallException;
import com.ef.golf.pojo.*;
import com.ef.golf.vo.CartItem;
import com.ef.golf.vo.EsOrderVo;
import com.ef.golf.vo.OrderItemVo;
import com.ef.golf.vo.SpeItemVo;

import java.util.List;
import java.util.Map;

public interface EsOrderService {
    public OrderPrice countPrice(List<CartItem> cartItemList, Integer shippingid, String regionid,Integer ticket_id);
    public Double countPrice(List<EsTypeArea> areaList, Double weight,
                              Double orderPrice, String regoinId);
    public Double countExp(String exp, Double weight, Double orderprice);
    public Double[] countPrice(Integer typeId, Double weight,
                               Double orderPrice, String regionId);
    public EsOrder add(EsOrder order,List<CartItem> itemList,Order efOrder,String jiesuanType) throws MallException;

    public String createSn();
    public void onBeforeOrderCreate(EsOrder order, List<CartItem> itemList);
    public void addSnapshotData(Goods snapshot, CartItem cartItem);

    public void saveGoodsItem(List<CartItem> itemList, EsOrder order);

    public void onItemSave(EsOrder order,OrderItem item);
    public void decreaseEnable(int goodsid, int productid, int depotid,int num);

    public EsOrder createOrder(Integer add_id,Integer type_id,Integer ticket_id,Integer userId);

   public ActivityDetail selectByKey(Integer activity_id);

    List<OrderItemVo> orderList(Map map);

    List<EsOrderVo> userOrderList(Map map);

    List<Map<String,Object>> getList(Integer userId,Map map);
    int totalCount(Map map);
    int orderCount(Map map);
    List<OrderItem> getItemList(Integer order_id);
    int updateOrderStatus(Map map);
    EsOrder selectByPrimaryKey(Integer orderId);
    SpeItemVo selectSpeItemVo(Integer goods_id);


    EsOrderVo getEsOrderBySn(String orderNo);
}
