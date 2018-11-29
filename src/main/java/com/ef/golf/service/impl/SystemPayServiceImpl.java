package com.ef.golf.service.impl;

import com.alibaba.fastjson.JSON;
import com.ef.golf.common.mob.MobPushUtil;
import com.ef.golf.mapper.InfromMsgMapper;
import com.ef.golf.mapper.JiaoYiHuizongMapper;
import com.ef.golf.mapper.System_payMapper;
import com.ef.golf.mapper.UserMapper;
import com.ef.golf.pojo.Coach_order;
import com.ef.golf.pojo.InfromMsg;
import com.ef.golf.pojo.JiaoYiHuizong;
import com.ef.golf.pojo.System_pay;
import com.ef.golf.service.SystemPayService;
import com.ef.golf.util.SmsUtil;
import com.ef.golf.vo.MineVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SystemPayServiceImpl implements SystemPayService {
    @Resource
    private System_payMapper systemPayMapper;
    @Resource
    private JiaoYiHuizongMapper jiaoYiHuizongMapper;
    @Resource
    private InfromMsgMapper infromMsgMapper;
    @Resource
    private UserMapper userMapper;
    @Value("${urlHead}")
    private String urlHead;
    @Value("${balanceRecord}")
    private String balanceRecord;
    @Value("${coachCloseTitle}")
    private String coachCloseTitle;
    @Value("${coachCloseContent}")
    private String coachCloseContent;
    @Value("${caddieCloseTitle}")
    private String caddieCloseTitle;
    @Value("${caddieCloseContent}")
    private String caddieCloseContent;
    @Value("${faceTofacePay}")
    private String faceTofacePay;
    @Value("${coachCloseMoney}")
    private String coachCloseMoney;
    @Value("${caddieCloseMoney}")
    private String caddieCloseMoney;
    /**
     * type		varchar(64)	NE	类型（1球童 2.教练 3.商家）
     *
     * 交易类型(1-订单支付 2-预存充值 3-退款 4-提现 5-收入 6-教练结算 7-打赏球童 8-买单 9-支持愿望 10- 红包 11-赠送 14单次课程费)12预约课程 13预约教练
     * */
    @Override//（1球童 2教练 3商家 4愿望 5课程 6红包 7预约教练 8单次课结算）
    public int insertSelective(System_pay record,String orderNo,Double oldMoney) {
        if("1".equals(record.getType())){//打赏球童
            JiaoYiHuizong jiaoYiHuizongChu = new JiaoYiHuizong
                    (null,record.getUserId(),"7",record.getCreateTime(),
                            2,record.getMoney(),orderNo,null,record.getChannel());
            /** mob推送 */
            Map<String,Object>mobMap = new HashMap<>();
            mobMap.put("title",caddieCloseTitle);
            mobMap.put("content",caddieCloseContent+record.getMoney()+"元");
            mobMap.put("url",urlHead+balanceRecord);
            MobPushUtil.MobPush(2,caddieCloseContent+record.getMoney()+"元",new String[]{record.getTakeId().toString()},"1",JSON.toJSONString(mobMap));
            InfromMsg infromMsg = new InfromMsg(null,record.getUserId().longValue(),"0","11", "27", record.getTakeId().longValue(), new Date(),
                    new Date(),record.getUserId().toString(),record.getTakeId().toString(),caddieCloseTitle,caddieCloseContent+record.getMoney()+"元",urlHead+balanceRecord,faceTofacePay);
            infromMsgMapper.insert(infromMsg);
            MineVo mineVo = userMapper.getInfo(record.getTakeId());
            SmsUtil.sendSMS(mineVo.getPhone(),caddieCloseMoney,new String[]{record.getMoney().toString()});

            JiaoYiHuizong jiaoYiHuizongJin = new JiaoYiHuizong
                    (null,record.getTakeId(),"5",record.getCreateTime(),
                            1,record.getMoney(),orderNo,null,record.getChannel());
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizongChu);
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizongJin);
        }else if("2".equals(record.getType())){
            JiaoYiHuizong jiaoYiHuizongChu = new JiaoYiHuizong
                    (null,record.getUserId(),"6",record.getCreateTime(),
                            2,record.getMoney(),orderNo,null,record.getChannel());

            Map<String,Object>mobMap = new HashMap<>();
            mobMap.put("title",coachCloseTitle);
            mobMap.put("content",coachCloseContent+record.getMoney()+"元");
            mobMap.put("url",urlHead+balanceRecord);
            MobPushUtil.MobPush(2,coachCloseContent+record.getMoney()+"元",new String[]{record.getTakeId().toString()},"1", JSON.toJSONString(mobMap));
            InfromMsg infromMsg = new InfromMsg(null,record.getUserId().longValue(),"0","11", "26", record.getTakeId().longValue(), new Date(),
                    new Date(),record.getUserId().toString(),record.getTakeId().toString(),coachCloseTitle,coachCloseContent+record.getMoney()+"元",urlHead+balanceRecord,faceTofacePay);
            infromMsgMapper.insert(infromMsg);
            MineVo mineVo = userMapper.getInfo(record.getTakeId());
            SmsUtil.sendSMS(mineVo.getPhone(),coachCloseMoney,new String[]{record.getMoney().toString()});


            JiaoYiHuizong jiaoYiHuizongJin = new JiaoYiHuizong
                    (null,record.getTakeId(),"5",record.getCreateTime(),
                            1,record.getMoney(),orderNo,null,record.getChannel());
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizongChu);
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizongJin);
        }else if("3".equals(record.getType())){
            JiaoYiHuizong jiaoYiHuizongChu = new JiaoYiHuizong
                    (null,record.getUserId(),"8",record.getCreateTime(),
                            2,record.getMoney(),orderNo,null,record.getChannel());
            /** 商家此处收入未优惠前金额 */
            JiaoYiHuizong jiaoYiHuizongJin = new JiaoYiHuizong
                    (null,record.getTakeId(),"5",record.getCreateTime(),
                            1,oldMoney,orderNo,null,record.getChannel());
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizongChu);
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizongJin);
        }else if("4".equals(record.getType())){//愿望
            JiaoYiHuizong jiaoYiHuizongChu = new JiaoYiHuizong
                    (null,record.getUserId(),"9",record.getCreateTime(),
                            2,record.getMoney(),orderNo,null,record.getChannel());
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizongChu);
        }else if("8".equals(record.getType())){
            /** 按照优惠前金额算收入 */
            JiaoYiHuizong jiaoYiHuizongChu = new JiaoYiHuizong
                    (null,record.getTakeId(),"14",record.getCreateTime(),
                            1,record.getMoney(),orderNo,null,record.getChannel());
            JiaoYiHuizong jiaoYiHuizongJin = new JiaoYiHuizong
                    (null,record.getUserId(),"14",record.getCreateTime(),
                            2,record.getMoney(),orderNo,null,record.getChannel());
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizongChu);
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizongJin);
        }else if("9".equals(record.getType())){//预约教练，教练收入的钱
            JiaoYiHuizong jiaoYiHuizongChu = new JiaoYiHuizong
                    (null,record.getTakeId(),"5",record.getCreateTime(),
                            1,record.getMoney(),orderNo,null,record.getChannel());
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizongChu);
        }

        return systemPayMapper.insertSelective(record);
    }

    @Override
    public int insertSelective(JiaoYiHuizong record) {
        return jiaoYiHuizongMapper.insertSelective(record);
    }

}
