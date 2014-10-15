package com.achui.quick.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;

public class QueryHelper {

	private static String HQL = "Select obj from %s obj where 1=1 ";
	
	private static String COUNT_HQL = "Select count(obj) from %s obj where 1=1 ";
	
	private static Logger log = LoggerFactory.getLogger(QueryHelper.class);
	
	public static String buildHQL(Query query){
		String hql = query.getHql() == null ? HQL:query.getHql();
		hql = String.format(hql,query.getDomain());
		StringBuilder builder = new StringBuilder(hql);
		builder.append(processQueryParams(query));
		log.info("Build HQL:"+builder.toString());
		return builder.toString();
	}
	
	public static String buildCountHQL(Query query){
		String hql = query.getHqlCount() == null ? COUNT_HQL:query.getHqlCount();
		hql = String.format(hql,query.getDomain());
		StringBuilder builder = new StringBuilder(hql);
		builder.append(processQueryParams(query));
		log.info("COUNT HQL:"+builder.toString());
		return builder.toString();
	}
	
	public static Map<String, Object> buildQueryParams(Query query){
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		List<Parameter> params = query.getParams();
		if(CollectionUtils.isNotEmpty(params)){
			for(Parameter param : params){
				buildParameter(param);
				paramsMap.put(param.getName(), param.getValue());
			}
		}
		log.info("Build param:"+paramsMap.toString());
		return paramsMap;
	}
	
	private static void buildParameter(Parameter parameter){
		String name = parameter.getName();
		if(!StringUtils.isEmpty(name)){
			if(name.indexOf("_") > -1){
				name = name.substring(name.indexOf("_")+1);
				parameter.setName(name);
			}
		}
		if("like".equalsIgnoreCase(parameter.getOp())){
			parameter.setValue("%"+parameter.getValue()+"%");
		}
	} 
	
	private static String processQueryParams(Query query){
		StringBuilder builder = new StringBuilder("");
		List<Parameter> params = query.getParams();
		if(CollectionUtils.isNotEmpty(params)){
			for(Parameter param : params){
				buildParameter(param);
				if(!StringUtils.isEmpty(param.getValue())){
					builder.append(" and obj."+param.getName())
					.append(" ")
					.append(param.getOp())
					.append(" :")
					.append(param.getName());
				}
			}
		}
		return builder.toString();
	}
}
