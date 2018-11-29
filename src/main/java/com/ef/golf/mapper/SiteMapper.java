package com.ef.golf.mapper;

import com.ef.golf.pojo.IfunResult;
import com.ef.golf.pojo.Site;
import com.ef.golf.vo.SiteInMapVo;
import com.ef.golf.vo.SitePreOrderVo;
import com.ef.golf.vo.SiteVo;
import com.ef.golf.vo.SolrSiteVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SiteMapper {
    int deleteByPrimaryKey(Integer siteId);

    int insert(Site record);

    int insertSelective(Site record);

    Site selectByPrimaryKey(Integer siteId);

    Site selectSiteByPrimaryKey(Integer siteId);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKeyWithBLOBs(Site record);

    int updateByPrimaryKey(Site record);

    List<SiteVo> getSiteListPage(Site site);

    List<SiteVo> indexSiteList(Site site);

    List<SiteInMapVo> getSiteByCity(String cityName);

    //查询球会id
    String getqiuHuiId(String siteId);

    //预下单接口
    SitePreOrderVo getPreOrderInfo(@Param("uid") String uid, @Param("siteId") int siteId,@Param("playDate") String playDate);

    //导入solr索引库字段
    List<SolrSiteVo> findSiteDatabaseToSolrIndex();
    //根据id更新索引库数据
    SolrSiteVo findBySiteIdDatabaseToSolrIndex(Integer siteId);
    //查询球会下球场
    List<SiteVo>getClubDownSiteByClubId(@Param("qiuHuiId")String qiuHuiId,@Param("lon")String lon,@Param("lat")String lat);
}