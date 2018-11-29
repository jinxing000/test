package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.SearchResult;
import com.ef.golf.service.SiteService;
import com.ef.golf.util.DistanceUtil;
import com.ef.golf.vo.SolrSiteVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 * 查询solr索引库
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SolrSiteListAction extends AbstractService {

    private String city;
    private Integer page = 1;
    private Integer rows = 5;
    //@NotNull(message = "经度必须")
    private Double lat2;
    //@NotNull(message = "纬度必须")
    private Double lng2;
    private String priceSort;//0 高到低 1 低到高
    private String distanceSort;// 0 远到近 1 近到远
    @Resource
    private SiteService siteService;
    private Double dis = 0.0;

    public Object doService() {
        SearchResult searchResult = siteService.getSiteListBySolrIndex(city, priceSort, page, rows);
        List<SolrSiteVo> list = searchResult.getSiteList();
        for (SolrSiteVo ssv : list
                ) {
            Double distance = DistanceUtil.getDistance(ssv.getReserve1(), ssv.getReserve2(), lat2, lng2);
            dis = Math.round(distance / 100d) / 10d;
            ssv.setDistance(dis + "km");
        }
        return searchResult;
    }

    public void setPriceSort(String priceSort) {
        this.priceSort = priceSort;
    }

    public void setDistanceSort(String distanceSort) {
        this.distanceSort = distanceSort;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setLat2(Double lat2) {
        this.lat2 = lat2;
    }

    public void setLng2(Double lng2) {
        this.lng2 = lng2;
    }
}
