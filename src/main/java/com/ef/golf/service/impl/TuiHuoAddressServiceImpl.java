package com.ef.golf.service.impl;

import com.ef.golf.mapper.TuiHuoAddressMapper;
import com.ef.golf.pojo.TuiHuoAddress;
import com.ef.golf.service.TuiHuoAddressService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/6/14.
 */
@Repository
public class TuiHuoAddressServiceImpl implements TuiHuoAddressService {
    @Resource
    private TuiHuoAddressMapper tuiHuoAddressMapper;
    @Override
    public TuiHuoAddress selectTuiHuoAddress() {
        return tuiHuoAddressMapper.selectTuiHuoAddress();
    }
}
