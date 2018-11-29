package com.ef.golf.dao;

import com.ef.golf.util.SearchCaddieResult;
import com.ef.golf.vo.SolrCaddieVo;
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
public class SolrCaddieDaoImpl implements SolrCaddieDao {

    private SolrClient solrClient;
    @Override
    public SearchCaddieResult getCaddieListSolrIndex(SolrQuery solrQuery) {
        solrClient =new HttpSolrClient.Builder("http://localhost:8089/solr/ef_caddie").build();
        SearchCaddieResult result = new SearchCaddieResult();
        //封装文档数据
        List<SolrCaddieVo> solrCaddieList = new ArrayList<SolrCaddieVo>();
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
                SolrCaddieVo solrCaddieVo = new SolrCaddieVo();
                //获取id
                String site_id =(String) sdt.get("id");
                //封装id
                solrCaddieVo.setId(Integer.valueOf(site_id));
                //获取球童name
                String realname =(String)sdt.get("realname");
                solrCaddieVo.setRealname(realname);
                //获取球会id
                Integer qiuhuiId = (Integer) sdt.get("qiuhuiId");
                solrCaddieVo.setQiuhuiId(qiuhuiId);
                //获取球会
                String qiuhuiName = (String) sdt.get("qiuhuiName");
                solrCaddieVo.setQiuhuiName(qiuhuiName);
                //获取评分
                Double score = (Double) sdt.get("score");
                        if(score!=null){
                            solrCaddieVo.setScore(score);
                        }

                //获取推荐
                /*String recommend =(String) sdt.get("recommend");
                solrCaddieVo.setRecommend(recommend);*/
                //获取头像
                String head_portrait_url = (String) sdt.get("head_portrait_url");
                solrCaddieVo.setHeadPortraitUrl(head_portrait_url);
                //把文档数据添加到集合
                solrCaddieList.add(solrCaddieVo);
            }
            //把集合添加到分页包装类对象
            result.setSolrCaddie(solrCaddieList);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
