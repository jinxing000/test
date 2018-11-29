package com.ef.golf.service.impl;

import com.ef.golf.mapper.BannerMapper;
import com.ef.golf.pojo.Banner;
import com.ef.golf.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xzw on 2017/9/22.
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bm;


    public List<Banner> bannerByGrouping(String grouping) {
        List<Banner> bannerList=bm.bannerByGrouping(grouping);
        return bannerList;
    }
}
