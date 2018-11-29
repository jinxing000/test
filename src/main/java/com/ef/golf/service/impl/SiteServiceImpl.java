package com.ef.golf.service.impl;

import com.ef.golf.dao.SolrSiteDao;
import com.ef.golf.mapper.HotcityMapper;
import com.ef.golf.mapper.Product_scoreMapper;
import com.ef.golf.mapper.SiteMapper;
import com.ef.golf.mapper.Site_priceMapper;
import com.ef.golf.pojo.Hotcity;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.pojo.SearchResult;
import com.ef.golf.pojo.Site;
import com.ef.golf.service.SiteService;
import com.ef.golf.vo.SiteInMapVo;
import com.ef.golf.vo.SitePreOrderVo;
import com.ef.golf.vo.SiteVo;
import com.ef.golf.vo.SolrSiteVo;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * for efgolf
 * Created by Bart on 2017/9/22.
 * Date: 2017/9/22 18:00
 */

@Repository
public class SiteServiceImpl implements SiteService {


    @Resource
    private SiteMapper siteMapper;

    @Resource
    private HotcityMapper hotcityMapper;
    @Resource
    private Site_priceMapper sitePriceMapper;
    @Resource
    private Product_scoreMapper product_scoreMapper;
    @Resource
    private SolrSiteDao solrSiteDao;
   private SolrClient solrClient;

    public List<SiteVo> getSiteListPage(Site site) {
        List<SiteVo> list = siteMapper.getSiteListPage(site);
        for (SiteVo score: list) {
            Double siteScore = product_scoreMapper.getScoreAvg(Integer.valueOf(score.getSiteID()));
            score.setScore(siteScore);
        }
        return list;
    }

    @Override
    public List<SiteVo> indexSiteList(Site site) {
        List<SiteVo> list = siteMapper.indexSiteList(site);
        for (SiteVo score: list) {
            Double siteScore = product_scoreMapper.getScoreAvg(Integer.valueOf(score.getSiteID()));
            score.setScore(siteScore);
        }
        return list;
    }

    public List<Hotcity> getHotCitys() {
        return hotcityMapper.getHotCitys();
    }

    public Site getSite(int id) {
        return siteMapper.selectByPrimaryKey(id);
    }

    public Site selectByPrimaryKey(Integer siteId) {
        Site site = siteMapper.selectByPrimaryKey(siteId);
        return site;
    }


    public Site selectSiteByPrimaryKey(Integer siteId) {
        return siteMapper.selectSiteByPrimaryKey(siteId);
    }

    @Override
    public Double getNowPrice(Integer siteId,Date playDate) {
        Double price = sitePriceMapper.getNowPrice(siteId,playDate);
        return price;
    }

    @Override
    public SitePreOrderVo getPreOrderInfo(String uid, int siteId, String playDate) {
        return siteMapper.getPreOrderInfo(uid,siteId,playDate);
    }

    public List<SiteInMapVo> getSiteByCity(String cityName) {
        return siteMapper.getSiteByCity(cityName);
    }

    @Override
    public String getqiuHuiId(String siteId) {
        return siteMapper.getqiuHuiId(siteId);
    }
    static{

    }

    @Override
    public IfunResult fingSiteDatabaseToSolrIndex() {
       solrClient = new HttpSolrClient.Builder("http://localhost:8089/solr/ef_site").build();
            try {
                List<SolrSiteVo> list = siteMapper.findSiteDatabaseToSolrIndex();

                for (SolrSiteVo solrSiteVo : list) {
                    SolrInputDocument document = new SolrInputDocument();
                    document.addField("site_id",solrSiteVo.getSiteId());
                    document.addField("reserve4",solrSiteVo.getReserve4());
                    document.addField("reserve1",solrSiteVo.getReserve1());
                    document.addField("reserve2",solrSiteVo.getReserve2());
                    document.addField("site_model",solrSiteVo.getSiteModel());
                    document.addField("site_param",solrSiteVo.getSiteParam());
                    document.addField("site_address",solrSiteVo.getSiteAddress());
                    document.addField("site_city",solrSiteVo.getSiteCity());
                    document.addField("price",solrSiteVo.getPrice());
                    document.addField("c_year",solrSiteVo.getcYear());
                    document.addField("c_month",solrSiteVo.getcMonth());
                    document.addField("c_day",solrSiteVo.getcDay());
                    document.addField("c_week",solrSiteVo.getcWeek());
                    solrClient.add(document);
                }
                solrClient.commit();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return IfunResult.ok();
    }

    @Override
    public SearchResult getSiteListBySolrIndex(String city, String priceSort,Integer page, Integer rows) {
        // 创建solrQuery对象,封装参数,solrQuery是solr用来封装所有参数对象
        SolrQuery solrQuery = new SolrQuery();
        //封装查询条件
        if(!"".equals(city) && city != null){
            solrQuery.setQuery(city);
        }else{
            solrQuery.setQuery("*:*");
        }
        if(priceSort.equals("0")){
            solrQuery.setSort("price", SolrQuery.ORDER.asc);
        }
        if(priceSort.equals("1")){
            solrQuery.setSort("price", SolrQuery.ORDER.desc);
        }
        if(priceSort.equals("")&&priceSort==null){
            solrQuery.setQuery("*:*");
        }
        //分页
        //起始页
        int startNo = (page-1)*rows;
        solrQuery.setStart(startNo);
        solrQuery.setRows(rows);

        //高亮设置
        //开启高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("reserve4");
        //前缀
        solrQuery.setHighlightSimplePre("<font color='red'>");
        //后缀
        solrQuery.setHighlightSimplePost("</font>");
        //设置默认查询域字段  复制域
        solrQuery.set("df", "p_keywords");

        SearchResult searchResult= solrSiteDao.getSiteListSolrIndex(solrQuery);

        searchResult.setPage(page);

        //计算总页码数
        Integer count = searchResult.getTotalCount();

        int pages = count/rows;
        if(count%rows>0){
            pages++;
        }
        searchResult.setTotalPages(pages);
        return searchResult;
    }

    @Override
    public List<SiteVo> getClubDownSiteByClubId(String qiuHuiId, String lon, String lat) {
        List<SiteVo> list = siteMapper.getClubDownSiteByClubId(qiuHuiId,lon,lat);
        for (SiteVo score: list) {
            Double siteScore = product_scoreMapper.getScoreAvg(Integer.valueOf(score.getSiteID()));
            score.setScore(siteScore);
        }
        return list;
    }
}
