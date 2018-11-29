package com.ef.golf.service.impl;

import com.ef.golf.mapper.CashBackMapper;
import com.ef.golf.pojo.CashBack;
import com.ef.golf.service.CashBackService;
import com.ef.golf.vo.CashBackVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/4/28 11:23
 */
@Repository
public class CashBackServiceImpl implements CashBackService {

    @Resource
    private CashBackMapper cashBackMapper;

    @Override
    public List<CashBack> selectCashBackMsg() {
        return cashBackMapper.selectCashBackMsg();
    }

    @Override
    public List<CashBackVo> getCashBackScaleVo() {
        return cashBackMapper.getCashBackScaleVo();
    }
}
