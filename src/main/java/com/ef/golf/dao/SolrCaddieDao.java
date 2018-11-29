package com.ef.golf.dao;

import com.ef.golf.pojo.SearchResult;
import com.ef.golf.util.SearchCaddieResult;
import org.apache.solr.client.solrj.SolrQuery;

public interface SolrCaddieDao {
    public SearchCaddieResult getCaddieListSolrIndex(SolrQuery solrQuery);
}
