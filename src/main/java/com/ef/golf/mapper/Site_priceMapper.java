package com.ef.golf.mapper;

import com.ef.golf.pojo.Site_price;
import com.ef.golf.vo.SitePriceVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface Site_priceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Site_price record);

    int insertSelective(Site_price record);

    Site_price selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Site_price record);

    int updateByPrimaryKey(Site_price record);

    //获取球场三个月的价格信息
    List<Site_price> getSitePrice(SitePriceVo sitePriceVo);
    Double getNowPrice(@Param("siteId") Integer siteId,@Param("playDate")Date playDate);
}