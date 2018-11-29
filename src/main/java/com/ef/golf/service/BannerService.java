package com.ef.golf.service;

import com.ef.golf.pojo.Banner;

import java.util.List;

/**
 * Created by xzw on 2017/9/22.
 */
public interface BannerService {

    //根据分组名称查询banner图集合
    List<Banner> bannerByGrouping(String grouping);
}
