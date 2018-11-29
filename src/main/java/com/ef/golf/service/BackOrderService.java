package com.ef.golf.service;

import com.ef.golf.pojo.OrderItem;
import com.ef.golf.pojo.SellbackGoodsList;
import com.ef.golf.pojo.SellbackList;
import com.ef.golf.vo.BackGoodsVo;

import java.util.List;
import java.util.Map;

public interface BackOrderService {

    public Integer addSellBack(SellbackList sellBack, List<SellbackGoodsList> goodsList );
    public List<OrderItem> getItemsByOrderidAndGoodsList(int order_id, List<SellbackGoodsList> goodsList);
    public void checkItemStateForApply(List<OrderItem> itemList);
    public void fillGoodsItem(List<OrderItem> itemList,List<SellbackGoodsList> returnItemList);
    public void addLog(Integer order_id, String message);
    public List<BackGoodsVo> BackGoodsVoList(Integer recid);
    public  SellbackList selectByPrimaryKey(Integer id);
    public  SellbackList ByPrimaryKey(Integer order_id);

    //更新回寄信息的物流公司和单号
    int updateSellBack(String s,Integer shopOrderId,Integer efOrderId);
}
