package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.SellbackGoodsList;
import com.ef.golf.pojo.SellbackList;
import com.ef.golf.pojo.User;
import com.ef.golf.service.BackOrderService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单退货
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BackOrderAction extends AbstractService {
    @Autowired
    BackOrderService backOrderService;
    @Autowired
    UserService userService;
    /*private Integer[] goodsId;
    private Integer[] goodsNum;
    private Integer[] payNum;购买数量
    private Integer[] productId;
    private Double[] price;
	private Integer[] item_id;*/
    private String goodsId;
    private String goodsNum;
    private String payNum;
    private String productId;
    private String price;
    private String item_id;//订单购物项id
    private Integer userId;
    private Integer order_id;
    private Double alltotal_pay;//退款金额

    public void setAlltotal_pay(Double alltotal_pay) {
        this.alltotal_pay = alltotal_pay;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        String[] goodsIds = goodsId.split(",");
        String[] goodsNums = goodsNum.split(",");
        String[] payNums = payNum.split(",");
        String[] productIds = productId.split(",");
        String[] prices = price.split(",");
        String[] item_ids = item_id.split(",");
        User user = userService.selectByPrimaryKey(userId.longValue());
        SellbackList sellbackList = new SellbackList();
        sellbackList.setMemberId(userId);
        sellbackList.setSndto(user.getUserName());
        sellbackList.setTradeno(DateUtil.toString(System.currentTimeMillis() / 1000, "yyMMddhhmmss"));
        sellbackList.setRegoperator("会员[" + user.getUserName() + "]");
        sellbackList.setTradestatus(0);
        sellbackList.setRegtime(System.currentTimeMillis() / 1000);
        sellbackList.setOrderid(order_id);
        sellbackList.setType(2);
        sellbackList.setAlltotalPay(new BigDecimal(alltotal_pay).setScale(2, BigDecimal.ROUND_HALF_UP));
        sellbackList.setRefundWay("原支付方式退款");

/**
 * 循环页面中选中的商品，形成退货明细:goodsList
 */

        List<SellbackGoodsList> goodsList = new ArrayList<SellbackGoodsList>();

        for (int i = 0; i < goodsId.length(); i++) {
            SellbackGoodsList sellBackGoods = new SellbackGoodsList();
            sellBackGoods.setPrice(new BigDecimal(prices[i]).setScale(2, BigDecimal.ROUND_HALF_UP));
            sellBackGoods.setReturnNum(Integer.valueOf(goodsNums[i]));
            sellBackGoods.setShipNum(Integer.valueOf(payNums[i]));
            sellBackGoods.setGoodsId(Integer.valueOf(goodsIds[i]));
            //sellBackGoods.setGoods_remark(sellBack.getRemark());
            sellBackGoods.setProductId(Integer.valueOf(productIds[i]));
            sellBackGoods.setItemId(Integer.valueOf(item_ids[i]));
            goodsList.add(sellBackGoods);

        }
        int i = backOrderService.addSellBack(sellbackList, goodsList);
        if (i > 0) {
            map.put("mes", "退货申请成功");
        } else {
            map.put("mes", "退货申请失败");
        }

        return map;
    }
}
