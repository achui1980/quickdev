package com.achui.quick.query;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Query {

	private String domain;
	
	private List<Parameter> params;
	
	private int page;
	
	private int pageCount;
	
	@JsonIgnore
	private String hql;
	
	@JsonIgnore
	private String hqlCount;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public List<Parameter> getParams() {
		return params;
	}

	public void setParams(List<Parameter> params) {
		this.params = params;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public String getHqlCount() {
		return hqlCount;
	}

	public void setHqlCount(String hqlCount) {
		this.hqlCount = hqlCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
}
