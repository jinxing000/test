package com.ef.golf.service.impl;

import com.ef.golf.mapper.JiaoYiHuizongMapper;
import com.ef.golf.mapper.SystemTransferMapper;
import com.ef.golf.pojo.JiaoYiHuizong;
import com.ef.golf.pojo.SystemTransfer;
import com.ef.golf.service.SystemTransferService;
import com.ef.golf.util.AmountUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/5/12 9:45
 */
@Repository
public class SystemTransferServiceImpl implements SystemTransferService {

    @Resource
    private SystemTransferMapper systemTransferMapper;
    @Resource
    private JiaoYiHuizongMapper jiaoYiHuizongMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return systemTransferMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SystemTransfer record) {
        return systemTransferMapper.insert(record);
    }
//(1-订单支付 2-预存充值 3-退款 4-提现 5-收入 6-教练结算 7-打赏球童 8-买单 9-支持愿望 10- 红包 11-赠送 12预约课程 13预约教练 14单次课程费)
    @Override
    public int insertSelective(SystemTransfer record) {
       /* JiaoYiHuizong jiaoYiHuizong = null;
        try {
            jiaoYiHuizong = new JiaoYiHuizong
                    (null,Integer.valueOf(record.getRecipient()),record.getDescription(),record.getCreateTime(),
                            1,Double.valueOf(AmountUtils.changeF2Y(record.getAmount())),record.getOrderNo(),null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jiaoYiHuizongMapper.insertSelective(jiaoYiHuizong);*/


        return systemTransferMapper.insertSelective(record);
    }

    @Override
    public SystemTransfer selectByPrimaryKey(Long id) {
        return systemTransferMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SystemTransfer record) {
        return systemTransferMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SystemTransfer record) {
        return systemTransferMapper.updateByPrimaryKey(record);
    }
}
