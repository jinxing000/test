package com.ef.golf.dao;

import com.ef.golf.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

public interface SolrSiteDao{
    public SearchResult getSiteListSolrIndex(SolrQuery solrQuery);
}
