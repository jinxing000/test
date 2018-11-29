package com.ef.golf.service.impl;

import com.ef.golf.mapper.Site_priceMapper;
import com.ef.golf.pojo.Site_price;
import com.ef.golf.service.SitePriceService;
import com.ef.golf.vo.SitePriceVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository
public class SitePriceServiceImpl implements SitePriceService {

    @Resource
    private Site_priceMapper sitePriceMapper;
    @Override
    public List<Site_price> getSitePrice(SitePriceVo sitePriceVo) {
        return sitePriceMapper.getSitePrice(sitePriceVo);
    }
}
