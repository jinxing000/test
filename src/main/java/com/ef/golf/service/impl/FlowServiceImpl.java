package com.ef.golf.service.impl;

import com.ef.golf.mapper.AccountMapper;
import com.ef.golf.mapper.FlowMapper;
import com.ef.golf.mapper.JiaoYiHuizongMapper;
import com.ef.golf.pojo.Flow;
import com.ef.golf.pojo.IntegralRecord;
import com.ef.golf.pojo.JiaoYiHuizong;
import com.ef.golf.service.FlowService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.JiaoYiHuizongVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class FlowServiceImpl implements FlowService {
    @Resource
    private FlowMapper flowMapper;

    @Resource
    private JiaoYiHuizongMapper jiaoYiHuizongMapper;
    @Resource
    private AccountMapper accountMapper;

    /**
     * id;
     userId;
     type;
     createTime;

     outIn;
     money;
     1-订单支付 2-预存充值 3-退款 4-提现 5-收入 6-教练结算 7-打赏球童 8-买单 9-支持愿望 10- 发红包 11-赠送 12-其他 13-红包退款

     2222充值 1111订单支付 3333提现 4444赠送 5555退回 8888-红包退款
     */
    @Override
    public int insertSelective(Flow flow,String channel) {//进出(1 进 2 出)
        JiaoYiHuizong jiaoYiHuizong=null;
        if("2222".equals(flow.getFlowType())){//充值

            jiaoYiHuizong = new JiaoYiHuizong(null,flow.getUserId(),
                    "2",flow.getCreateTime(),1,flow.getMoney(),flow.getSequenceNumber(),null,channel);
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizong);
        }else if("1111".equals(flow.getFlowType())){//订单支付
            jiaoYiHuizong = new JiaoYiHuizong(null,flow.getUserId(),"1",flow.getCreateTime(),2,flow.getMoney(),flow.getSequenceNumber(),null,channel);
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizong);
        }else if("4444".equals(flow.getFlowType())){
            jiaoYiHuizong = new JiaoYiHuizong(null,flow.getUserId(),"11",flow.getCreateTime(),1,flow.getMoney(),flow.getSequenceNumber(),null,channel);
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizong);
        }else if("3333".equals(flow.getFlowType())){
            jiaoYiHuizong = new JiaoYiHuizong(null,flow.getUserId(),"4",flow.getCreateTime(),1,flow.getMoney(),flow.getSequenceNumber(),null,channel);
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizong);
        }else if("6666".equals(flow.getFlowType())){
            jiaoYiHuizong = new JiaoYiHuizong(null,flow.getUserId(),"5",flow.getCreateTime(),1,flow.getMoney(),flow.getSequenceNumber(),null,channel);
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizong);
        }else if("8888".equals(flow.getFlowType())){
            jiaoYiHuizong = new JiaoYiHuizong(null,flow.getUserId(),"13",flow.getCreateTime(),1,flow.getMoney(),flow.getSequenceNumber(),null,channel);
            jiaoYiHuizongMapper.insertSelective(jiaoYiHuizong);
        }
        return flowMapper.insertSelective(flow);
    }

    @Override
    public PageBean jiaoYiJiLu(Long userId,Integer pageNo,Integer pageSize) {
        PageBean pageBean = new PageBean();
        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);

        List<JiaoYiHuizongVo>list = jiaoYiHuizongMapper.selectByUserId(map);
        Integer count = jiaoYiHuizongMapper.selectByUserIdCount(userId);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        pageBean.setResultList(list);
        return pageBean;
    }

    @Override
    public Double getTodayZenSongMoney(Integer userId) {
        return flowMapper.getTodayZenSongMoney(userId);
    }
}
