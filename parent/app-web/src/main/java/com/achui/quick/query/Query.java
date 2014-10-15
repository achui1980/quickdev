package com.achui.quick.query;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Query {

	private String domain;
	
	private List<Parameter> params;
	
	@JsonIgnore
	private String hql;

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
}
