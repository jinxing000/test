package com.ef.golf.util;

import com.ef.golf.vo.SolrCaddieVo;
import com.ef.golf.vo.SolrSiteVo;

import java.io.Serializable;
import java.util.List;

public class SearchCaddieResult implements Serializable{
	
	//当前页
	private Integer page;
	//总页码
	private Integer totalPages;
	//总条数
	private Integer totalCount;

	//总记录
	private List<SolrCaddieVo> solrCaddie;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<SolrCaddieVo> getSolrCaddie() {
		return solrCaddie;
	}
	public void setSolrCaddie(List<SolrCaddieVo> solrCaddie) {
		this.solrCaddie = solrCaddie;
	}

}
