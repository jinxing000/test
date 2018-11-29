package com.ef.golf.service.impl;

import com.ef.golf.mapper.*;
import com.ef.golf.pojo.*;
import com.ef.golf.service.BackOrderService;
import com.ef.golf.util.DateUtil;
import com.ef.golf.util.OrderItemStatus;
import com.ef.golf.util.OrderStatus;
import com.ef.golf.vo.BackGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BackOrderServiceImpl implements BackOrderService {
    @Autowired
    SellbackGoodsListMapper sellbackGoodsListMapper;
    @Autowired
    SellbackListMapper sellbackListMapper;
    @Autowired
    EsOrderMapper esOrderMapper;
     @Autowired
    OrderItemMapper orderItemMapper;
     @Autowired
    Order_logMapper order_logMapper;
@Autowired
UserMapper userMapper;

    @Autowired
   OrderMapper orderMapper;
    @Override
    public void checkItemStateForApply(List<OrderItem> itemList) {
        for (OrderItem item:itemList){
            if(OrderItemStatus.NORMAL!=item.getState()){
                 throw new RuntimeException("商品["+item.getName()+"]已经申请过退货了");
            }
        }
    }

    @Override
    public void fillGoodsItem(List<OrderItem> itemList, List<SellbackGoodsList> returnItemList) {
                for(SellbackGoodsList sellBackGoods:returnItemList){
                        for(OrderItem item:itemList){
                            if(item.getProductId().equals(sellBackGoods.getProductId())){
                                sellBackGoods.setGoodsImage(item.getImage());
                                sellBackGoods.setGoodsSn(item.getSn());
                                sellBackGoods.setGoodsName(item.getName());
                                sellBackGoods.setSpecJson(item.getAddon());
                            }


                        }
                }
    }

    @Override
    public void addLog(Integer order_id, String message) {
        Order_log orderLog = new Order_log();
        EsOrder esOrder = esOrderMapper.selectByPrimaryKey(order_id);
        User user = userMapper.selectByPrimaryKey(esOrder.getMemberId().longValue()) ;
        orderLog.setMessage(message);
       			orderLog.setOpId(0);
       			orderLog.setOpName(user.getUserName());
       		orderLog.setOpTime(System.currentTimeMillis()/1000);
       		orderLog.setOrderId(order_id);
            order_logMapper.insertSelective(orderLog) ;
    }

    @Override
    public SellbackList ByPrimaryKey(Integer order_id) {
        return sellbackListMapper.ByPrimaryKey(order_id);
    }

    @Override
    public int updateSellBack(String s,Integer shopOrderId,Integer efOrderId) {

        int count=1;
        try{
            //更新退货单的物流单号和物流公司，注意这里把提交的这些信息写在了备注里
            SellbackList sellbackList=sellbackListMapper.selectByPrimaryKey(shopOrderId);
            sellbackList.setRemark(sellbackList.getRemark()+"-"+s);
            sellbackList.setFinanceRemark(sellbackList.getFinanceRemark()+"-"+s);
            sellbackList.setSellerRemark(sellbackList.getSellerRemark()+"-"+s);
            sellbackList.setWarehouseRemark(sellbackList.getWarehouseRemark()+"-"+s);
           count= sellbackListMapper.updateByPrimaryKeySelective(sellbackList);
            //更新ef_order 的状态为36
            // 31.确认收货 待评价   32 申请售后 退货中 待寄件 33 退款已退货 34 取消支付  35 收货后已评价 36  退货中 已寄件
            Map map=new HashMap();
            Order order= orderMapper.selectByPrimaryKey(efOrderId);
            map.put("orderState",36);
            map.put("modifyUser",order.getCreateUser());
            map.put("modifyTime",new Date());
            map.put("orderId",order.getOrderId());
            map.put("orderNo",order.getOrderNo());

            count= orderMapper.updateWorkOrderStauts(map);
        }catch (Exception ex){
            count=0;
            ex.printStackTrace();
        }

        return count;
    }

    @Override
    public SellbackList selectByPrimaryKey(Integer id) {
        return sellbackListMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BackGoodsVo> BackGoodsVoList(Integer recid) {
        return sellbackGoodsListMapper.BackGoodsVoList(recid);
    }

    @Override
    public Integer addSellBack(SellbackList sellBack, List<SellbackGoodsList> goodsList) {
        Map<String,Object>map= new HashMap<String,Object>();
        Map<String,Object>map1= new HashMap<String,Object>();
        Integer memberid = sellBack.getMemberId();
        int orderid = sellBack.getOrderid();
        EsOrder order = esOrderMapper.selectByPrimaryKey(orderid);
        sellBack.setOrdersn(order.getSn());
          List<OrderItem> orderItemList = this.getItemsByOrderidAndGoodsList(orderid,goodsList);
          this.checkItemStateForApply(orderItemList);
          this.fillGoodsItem(orderItemList,goodsList);
          int i = sellbackListMapper.insertSelective(sellBack);
          int j=0;
          for (SellbackGoodsList sellBackGoods : goodsList) {
              sellBackGoods.setRecid(sellBack.getId());
                  map.put("state",OrderItemStatus.APPLY_RETURN);
                  map.put("item_id",sellBackGoods.getItemId());
                orderItemMapper.updateItemStruts(map);
              j =sellbackGoodsListMapper.insertSelective(sellBackGoods) ;
          }
          map1.put("status", OrderStatus.ORDER_MAINTENANCE) ;
          map1.put("order_id",orderid)  ;
         esOrderMapper.upOrderStatus(map1);
        return i;
    }

    @Override
    public List<OrderItem> getItemsByOrderidAndGoodsList(int order_id, List<SellbackGoodsList> goodsList) {
        List<Integer> list = new ArrayList<Integer>();
        for (SellbackGoodsList sellBackGoodsList : goodsList) {
            list.add(sellBackGoodsList.getGoodsId());
        }
        Map<String,Object>map = new HashMap<String,Object>();
        map.put("list",list);
        map.put("order_id",order_id) ;

        return orderItemMapper.itemList(map);
    }

}