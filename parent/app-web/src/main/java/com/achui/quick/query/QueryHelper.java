package com.achui.quick.query;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achui.quick.constants.ServiceConstants;

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
		
		String hql = (query ==null || query.getHqlCount() == null) ? COUNT_HQL:query.getHqlCount();
		hql = String.format(hql,query.getDomain());
		StringBuilder builder = new StringBuilder(hql);
		builder.append(processQueryParams(query));
		log.info("COUNT HQL:"+builder.toString());
		return builder.toString();
	}
	
	public static Map<String, Object> buildQueryParams(Query query){
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if(query != null){
			try {
				//TODO: type convert.
				Class clzz = Class.forName(ServiceConstants.SERVICE_DOMAIN_PACKAGE+"."+query.getDomain());
				List<Parameter> params = query.getParams();
				if(CollectionUtils.isNotEmpty(params)){
					for(Parameter param : params){
						if(StringUtils.isEmpty(param.getValue())) continue;
						buildParameter(param);
						Object value = convertToType(clzz, param.getName(), param.getValue());
						if(value != null)
							paramsMap.put(param.getName(), value);
					}
				}
			} catch (Exception e) {
				log.error("Build param error:",e);
			}
		}
		log.info("Build param:"+paramsMap.toString());
		return paramsMap;
	}
	
	private static void buildParameter(Parameter parameter){
		if(StringUtils.isEmpty(parameter.getValue())) return;
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
				if(!StringUtils.isEmpty(param.getValue())){
					buildParameter(param);
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
	
	public static Object convertToType(Class clzz,String property,String value){
		Object object = null;
		try {
			Field field = FieldUtils.getField(clzz, property,true);
			object = ConvertUtils.convert(value, field.getType());
		} catch (Exception e) {
			log.error("type convert error:",e);
		}
		return object;
	}
}
