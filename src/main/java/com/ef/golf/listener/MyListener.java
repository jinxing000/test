package com.ef.golf.listener;

import com.ef.golf.mapper.SiteMapper;
import com.ef.golf.vo.SolrSiteVo;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
@Component
public class MyListener{}
/*public class MyListener implements MessageListener {*/
   /* @Resource
    private SiteMapper siteMapper;
    private SolrClient solrClient;
    @Override
    public void onMessage(Message message) {
        Integer siteId = null;
        if(message instanceof TextMessage){
            TextMessage tm = (TextMessage) message;

            solrClient = new HttpSolrClient.Builder("http://localhost:8089/solr/ef_site").build();
            try {
                System.out.println("监听到消息:"+tm.getText());
                siteId = Integer.valueOf(tm.getText());
                SolrSiteVo solrSiteVo = siteMapper.findBySiteIdDatabaseToSolrIndex(siteId);
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
                solrClient.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }}
    */

