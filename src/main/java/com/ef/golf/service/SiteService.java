package com.ef.golf.service;

import com.ef.golf.pojo.Hotcity;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.pojo.SearchResult;
import com.ef.golf.pojo.Site;
import com.ef.golf.vo.SiteInMapVo;
import com.ef.golf.vo.SitePreOrderVo;
import com.ef.golf.vo.SiteVo;
import com.ef.golf.vo.SolrSiteVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * for efgolf
 * Created by Bart on 2017/9/22.
 * Date: 2017/9/22 17:54
 */
public interface SiteService {

    List<SiteVo> getSiteListPage(Site site);

    List<SiteVo> indexSiteList(Site site);

    List<Hotcity> getHotCitys();

    Site getSite(int id);

    Site selectByPrimaryKey(Integer siteId);

    List<SiteInMapVo> getSiteByCity(String cityName);

    //查询球会id
    String getqiuHuiId(String siteId);

    Site selectSiteByPrimaryKey(Integer siteId);

    Double getNowPrice(Integer siteId, Date playDate);

    SitePreOrderVo getPreOrderInfo(String uid, int siteId, String playDate);
    //索引库导入
   IfunResult fingSiteDatabaseToSolrIndex();
    //索引库查询球场列表
    SearchResult getSiteListBySolrIndex(String city,String priceSort,Integer page,Integer rows);
    //查询球会下球场
    List<SiteVo>getClubDownSiteByClubId(String qiuHuiId,String lon,String lat);
}
