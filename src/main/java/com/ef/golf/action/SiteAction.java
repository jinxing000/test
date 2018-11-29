package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Site;
import com.ef.golf.service.ProductScoreService;
import com.ef.golf.service.SiteService;
import com.ef.golf.vo.SiteVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * for efgolf
 * Created by Bart on 2017/9/22.
 * Date: 2017/9/22 17:52
 * 快速订场
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SiteAction extends AbstractService {


    @Resource
    private SiteService siteService;
    @Resource
    private ProductScoreService productScoreService;

    private String city;

    private String siteName;

    private String riqi;


    //默认按照推荐排序  {0:推荐排序,1:价格排序,2:距离排序}
    private int sortType = 0;

    private int showCount = 10;

    private int pageNum;


    private String lon = "";//经度

    private String lat = "";//纬度

    public Object doService() throws SystemException {
        Map<String, Object> siteMap = new HashMap<String, Object>();
        if (StringUtils.isEmpty(riqi)) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            riqi = sdf.format(date);
        }
        Site site = new Site();
        site.setShowCount(showCount);
        site.setCurrentPage(pageNum);

        if(null!=city) {
            if (city.contains("市")) {
                city = city.substring(0, city.length() - 1);
            }
        }

        site.setSiteCity(city);
        site.setReserve1(lon);
        site.setReserve2(lat);
        site.setGreenGrassType(riqi);
        site.setReserve4(siteName);
        site.setSortType(sortType);
        List<SiteVo> sites = siteService.getSiteListPage(site);
        siteMap.put("totalPage", site.getTotalPage());
        siteMap.put("currentPage", site.getCurrentPage());
        siteMap.put("resultList", sites);
        siteMap.put("totalResult", site.getTotalResult());
        System.out.println(sites.size());
        return siteMap;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
