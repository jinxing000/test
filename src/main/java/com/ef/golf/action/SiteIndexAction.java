package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Banner;
import com.ef.golf.pojo.Hotcity;
import com.ef.golf.pojo.Site;
import com.ef.golf.service.BannerService;
import com.ef.golf.service.SiteService;
import com.ef.golf.vo.SiteVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * for efgolf
 * Created by Bart on 2017/9/22.
 * Date: 2017/9/22 17:52
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SiteIndexAction extends AbstractService {


    @Resource
    private SiteService siteService;
    @Resource
    private BannerService bannerService;

    private String city = "天津";

    private int showCount = 10;

    private int currentPage = 1;
    private String lon = "";

    private String lat = "";

    public Object doService() throws SystemException {
        List<Hotcity> hotcities = siteService.getHotCitys();
        System.out.println("hotcities===================="+hotcities.size());
        List<Banner> bannerList = bannerService.bannerByGrouping("1");
        System.out.println("bannerList========================="+bannerList.size());
            System.out.println("开始siteindex");
        List<SiteVo> sites = siteService.getSiteListPage(getSite());
            System.out.println("sites=========================="+sites.size());


        Map<String, Object> map = new HashMap<String, Object>();

        map.put("sites", sites);
        map.put("banners", bannerList);
        map.put("hotcities", hotcities);
        System.out.println("结束siteindex");
        return map;
    }

    private Site getSite() {
        Site site = new Site();
        site.setShowCount(showCount);
        site.setCurrentPage(currentPage);
        site.setSiteCity(city);
        site.setReserve1(lon);
        site.setReserve2(lat);
        return site;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}