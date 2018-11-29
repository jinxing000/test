package com.ef.golf.pojo;

import com.ef.golf.vo.SolrSiteVo;

import java.io.Serializable;
import java.util.List;

public class SearchResult<T> implements Serializable{
	
	//当前页
	private Integer page;
	//总页码
	private Integer totalPages;
	//总条数
	private Integer totalCount;
	//总记录
	private List<SolrSiteVo> siteList;

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

	public List<SolrSiteVo> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<SolrSiteVo> siteList) {
		this.siteList = siteList;
	}
}
