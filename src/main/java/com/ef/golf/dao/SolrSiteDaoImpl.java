package com.ef.golf.dao;

import com.ef.golf.pojo.SearchResult;
import com.ef.golf.vo.SolrSiteVo;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SolrSiteDaoImpl implements SolrSiteDao {

    private SolrClient solrClient;
    @Override
    public SearchResult getSiteListSolrIndex(SolrQuery solrQuery) {
        solrClient =new HttpSolrClient.Builder("http://localhost:8089/solr/ef_site").build();
        SearchResult result = new SearchResult();
        //封装文档数据
        List<SolrSiteVo> solrSiteVoList = new ArrayList<SolrSiteVo>();
        try {
            // 查询索引库
            QueryResponse queryResponse =  solrClient.query(solrQuery);
            //获取文档集合对象
            SolrDocumentList results = queryResponse.getResults();
            //获取命中总记录数
            Long count = results.getNumFound();
            //把总记录数封装到分页包装类对象
            result.setTotalCount(count.intValue());
            //循环文档集合,把文档数据封装搜索对象
            for (SolrDocument sdt : results) {
                SolrSiteVo solrSiteVo = new SolrSiteVo();
                //获取id
                int site_id =(int)sdt.get("site_id");
                //封装id
                solrSiteVo.setSiteId(site_id);
                //获取球场name
                String reserve4 =(String)sdt.get("reserve4");
                solrSiteVo.setReserve4(reserve4);
                //获取球场数据
                String site_param = (String) sdt.get("site_param");
                solrSiteVo.setSiteParam(site_param);
                //获取价格
                Double price = (Double) sdt.get("price");
                solrSiteVo.setPrice(price);
                //获取球场模式
                String site_model =(String) sdt.get("site_model");
                solrSiteVo.setSiteModel(site_model);
                //获取地址
                String site_address = (String) sdt.get("site_address");
                solrSiteVo.setSiteAddress(site_address);
                //获取年月日周
                String c_year = (String) sdt.get("c_year");
                solrSiteVo.setcYear(c_year);
                String c_month = (String)sdt.get("c_month");
                solrSiteVo.setcMonth(c_month);
                String c_day = (String)sdt.get("c_day");
                solrSiteVo.setcDay(c_day);
                String c_week = (String)sdt.get("c_week");
                solrSiteVo.setcWeek(c_week);
                //获取经纬度
                Double reserve1 = (Double)sdt.get("reserve1");
                solrSiteVo.setReserve1(reserve1);
                Double reserve2 =(Double)sdt.get("reserve2");
                solrSiteVo.setReserve2(reserve2);
                //把文档数据添加到集合
                solrSiteVoList.add(solrSiteVo);
            }
            //把集合添加到分页包装类对象
            result.setSiteList(solrSiteVoList);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    }
