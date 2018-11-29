package com.ef.golf.service;


import com.ef.golf.pojo.Site_price;
import com.ef.golf.vo.SitePriceVo;

import java.util.List;

/**
 * create by xzw
 */
public interface SitePriceService {

    //获取球场三个月的价格信息
    List<Site_price> getSitePrice(SitePriceVo sitePriceVo);


}
