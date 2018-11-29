package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Banner;
import com.ef.golf.pojo.Logo;
import com.ef.golf.pojo.Site;
import com.ef.golf.service.*;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.GoodsHotSpecVo;
import com.ef.golf.vo.QuanziUserVo;
import com.ef.golf.vo.SiteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class IndexAction extends AbstractService {


    @Resource
    private SiteService siteService;

    @Resource
    private BannerService bannerService;

    @Resource
    private UserService userService;

    @Resource
    private GoodsService esGoodsService;

    @Autowired
    private AppIndexIcoService appIndexIcoService;

    @Autowired
    private TravelService travelService;

    private String city="天津市";
    private String platform;//Android iOS
    private String versionName;//版本号
    private String lon = "117.216175";//经度

    private String lat = "39.062205";//纬度

    public Object doService() throws SystemException {


        if(null!=city) {
            if (city.contains("市")) {
                city = city.substring(0, city.length() - 1);
            }
        }
        Site site = new Site();
        site.setShowCount(3);
        site.setReserve1(lon);
        site.setReserve2(lat);
        site.setSiteCity(city);
        /** 球场 */
        List<SiteVo> sites = siteService.indexSiteList(site);
        /** banner */
        List<Banner> bannerList = bannerService.bannerByGrouping("0");
        /** 固定banner */
        List<Banner> fixedBanner = bannerService.bannerByGrouping("4");
        /** 图标 */
        List<Logo> logos = appIndexIcoService.findAppIndexIcoByVersion(platform,versionName);
        /**
         * 热销商品
         */
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("pageNo", 0);
        maps.put("pageSize", 8);
        List<GoodsHotSpecVo> hotVoList = esGoodsService.getGoodsHotVoList(maps);
        /** 推荐商品 */
        PageBean recommendGoods = esGoodsService.getGoodsRecommendList(1,8);


        /** 球童 */
        QuanziUserVo quanziUserVo = new QuanziUserVo();
        quanziUserVo.setRegion(city);
        quanziUserVo.setCurrentPage(0);
        quanziUserVo.setShowCount(3);
        List<QuanziUserVo> qiuTongList = userService.indexQiuTongList(quanziUserVo);
        /** 教练 */
        QuanziUserVo quanziUserVo2 = new QuanziUserVo();
        quanziUserVo2.setRegion(city);
        quanziUserVo2.setCurrentPage(0);
        quanziUserVo2.setShowCount(3);
        List<QuanziUserVo> coachListPage = userService.indexCoachList(quanziUserVo2);
        /** 商家 */
        PageBean pageBean1 = userService.selectClubOrShop(null, "6", 1, 3,1);//门店
        PageBean pageBean2 = userService.selectClubOrShop(null, "7", 1, 3,1);//球会商家
        /** 旅游 */
        PageBean pageBean3 = travelService.getTravelLineByType("0",1,4);

        Map<String, Object> map = new HashMap<String, Object>();
        List<String> keySorts = new ArrayList<>();
        for (Logo log : logos){
            String temp=log.getLinkUrl().substring(log.getLinkUrl().indexOf("//")+2);//根据linkUrl后面的ifungolf://  后面的内容边比对
            switch (temp) {
                case "coachHome"://教练
                    if(null!=coachListPage&&!coachListPage.isEmpty()) {
                        keySorts.add("coach");
                    }
                    break;
                case "siteHome"://订场
                    if(null!=sites&&!sites.isEmpty()) {
                        keySorts.add("sites");
                    }
                    break;
                case "caddieHome"://球童
                    if(null!=qiuTongList&&!qiuTongList.isEmpty()) {
                        keySorts.add("qiuTong");
                    }
                    break;
                case "mallHome"://商城
                    if(null!=recommendGoods.getResultList()&&!recommendGoods.getResultList().isEmpty()) {
                        keySorts.add("recommendGoods");
                    }
                    break;
                case "shopperHome"://商家
                    if(null!=pageBean1.getResultList()&&!pageBean1.getResultList().isEmpty()) {
                        keySorts.add("shop");
                    }
                    if(null!=pageBean2.getResultList()&&!pageBean2.getResultList().isEmpty()){
                        keySorts.add("club");
                    }
                    break;
                case "tourHome"://旅游
                    if(null!=pageBean3.getResultList()&&!pageBean3.getResultList().isEmpty()) {
                        keySorts.add("recommendTravelLine");
                    }
                    break;
            }
        }
        map.put("keySort",keySorts);

        map.put("banners", bannerList);
        map.put("fixedBanner",fixedBanner);
        map.put("logos",logos);

        map.put("hotGoodsList", hotVoList);//推荐商品
        map.put("sites", sites);
        map.put("qiuTong", qiuTongList);
        map.put("coach", coachListPage);
        map.put("shop", pageBean1.getResultList());
        map.put("club", pageBean2.getResultList());
        map.put("recommendGoods",recommendGoods.getResultList());
        map.put("recommendTravelLine",pageBean3.getResultList());

        return map;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
