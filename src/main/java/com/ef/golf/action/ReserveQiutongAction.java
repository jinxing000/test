package com.ef.golf.action;


import com.ef.golf.common.mob.MobPushUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.pojo.InfromMsg;
import com.ef.golf.pojo.Order;
import com.ef.golf.pojo.Qiutong_order;
import com.ef.golf.service.*;
import com.ef.golf.util.OrderNoUtil;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.util.SmsUtil;
import com.ef.golf.vo.MineVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 预约球童
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReserveQiutongAction extends AbstractService {

    @Resource
    private Qiutong_orderService qiutongOrderService;
    @Resource
    private OrderService orderService;
    @Resource
    private AccountService accountService;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private OrderNoUtil orderNoUtil;
    @Resource
    private InfromMsgService infromMsgService;
    @Resource
    private UserService userService;

    //@NotNull
    protected String sid;
    //@NotNull
    private String uid;

    private String qiuHuiName;
    //@NotNull
    private String qiuHuiId;
    private String playDate;
    private String playTime;
    private String qiutongName;
    //@NotNull
    private String qiuTongId;
    private String playerName;
    private String contect;
    private String remark;
    @Value("${caddieTitle}")
    private String title;
    @Value("${caddieContent}")
    private String content;
    @Value("${urlHead}")
    private String headUrl;
    @Value("${workOrders}")
    private String url;
    @Value("${reserveCaddieOrder}")
    private String qiutonIco;
    @Value("${templateId.caddie.pay}")
    private String templateIdCaddiePay;


    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        Order order = getOrder();
        //生成主订单
        int i = orderService.insertSelective(order);
        //生成订单详情
        int j = qiutongOrderService.insertSelective(getQiuTong(order.getOrderId(), userId));
//获取billNo
        String billNo=order.getOrderNo();
        if (i > 0 && j > 0) {

            Map<String,Object>mobMap = new HashMap<>();
            mobMap.put("title",title);
            mobMap.put("content",content);
            mobMap.put("url",headUrl+url);
            mobMap.put("icoUrl",qiutonIco);
            MobPushUtil.MobPush(2,content,new String[]{qiuTongId},"1",mobMap.toString());
            InfromMsg infromMsg = new InfromMsg(null,userId.longValue(),"0","4", "5", order.getOrderId().longValue(), new Date(),
                    new Date(),userId.toString(),qiuTongId,title,contect,headUrl+url,qiutonIco);
            infromMsgService.insert(infromMsg);
            MineVo mineVo = userService.getInfo(Integer.valueOf(qiuTongId));
            SmsUtil.sendSMS(mineVo.getPhone(),templateIdCaddiePay);
            map.put("status", 0);
            map.put("message", "已下单");
            map.put("billNo",billNo);
        } else {
            map.put("status", 1);
            map.put("message", "下单失败");
        }
        return map;
    }


    //预定球童信息
    private Qiutong_order getQiuTong(int orderId, int userId) {
        Qiutong_order qiutongOrder = new Qiutong_order();
        qiutongOrder.setOrderId(orderId);
        qiutongOrder.setContactsPhone(contect);
        qiutongOrder.setCreateUser(userId + "");
        qiutongOrder.setModifyUser(userId + "");
        qiutongOrder.setCreateTime(new Date());
        qiutongOrder.setModifyTime(new Date());
        if(StringUtils.isNotBlank(qiutongName)){
        qiutongOrder.setName(qiutongName);
        }else{
            MineVo mineVo = userService.getInfo(Integer.valueOf(qiuTongId));
            qiutongOrder.setName(mineVo.getNickName());
        }
        qiutongOrder.setQiutongId(Integer.parseInt(qiuTongId));
        qiutongOrder.setPlayDate(playDate);
        qiutongOrder.setPlayTime(playTime);
        qiutongOrder.setPlayName(playerName);
        if(!"".equals(qiuHuiId)&&null!=qiuHuiId) {
            qiutongOrder.setQiuhuiId(Integer.valueOf(qiuHuiId));
        }
        if(!"".equals(qiuHuiName)&&null!=qiuHuiName) {
            qiutongOrder.setQiuhuiName(qiuHuiName);
        }
        qiutongOrder.setRemark(remark);
        return qiutongOrder;
    }

    //生成订单详情
    private Order getOrder() {
        Order order = new Order();
        try {
            Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
            Integer accountId = accountService.selectAccountId(Long.valueOf(userId));
            order.setAccountId(accountId);
            order.setOrderNo(orderNoUtil.orderNoGenerate("03", uid));
            order.setOrderState("8");
            order.setOrderType("3");
            order.setOrderMoeny(0.0);
            order.setCreateTime(new Date());
            order.setModifyTime(new Date());
            order.setCreateUser(userId + "");
            order.setModifyUser(userId + "");
            order.setIsDel(false);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        return order;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setQiuHuiName(String qiuHuiName) {
        this.qiuHuiName = qiuHuiName;
    }

    public void setQiuHuiId(String qiuHuiId) {
        this.qiuHuiId = qiuHuiId;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public void setQiutongName(String qiutongName) {
        this.qiutongName = qiutongName;
    }

    public void setQiuTongId(String qiuTongId) {
        this.qiuTongId = qiuTongId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setContect(String contect) {
        this.contect = contect;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
