package com.achui.quick.db;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.sql.DataSource;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

import com.achui.quick.annotation.Display;
import com.achui.quick.annotation.Searchable;
import com.achui.quick.util.AnnotationUtil;
import com.google.common.collect.Maps;

public class DBUtil {
	protected final static Logger log = LoggerFactory.getLogger(DBUtil.class);
	public static Map<String, ColumnMeta> getMetaInfo(DataSource dataSource,Class clzz){
		try {
			Annotation annotation = AnnotationUtils.findAnnotation(clzz, Table.class);
			String tableName = (String)AnnotationUtils.getValue(annotation, "name");
			Connection conn = dataSource.getConnection();
			ResultSet rs = conn.getMetaData().getColumns(null, null, tableName, "%");
			Map<String, String> fieldMap = AnnotationUtil.getFieldAnnotationMap(clzz, Column.class, "name");
			Map<String, Object> displayFieldMap = AnnotationUtil.getFieldMap(clzz, Display.class, "display");
			Map<String, Map<String, Object>> searchFieldMap = AnnotationUtil.getFieldMap(clzz, Searchable.class);
			Map<String, ColumnMeta> metaInfo = Maps.newHashMap();
			while(rs.next()){
				ColumnMeta columnMeta = new ColumnMeta(rs);
				String columnName = columnMeta.getName().toLowerCase();
				columnName = fieldMap.get(columnName);
				Map<String, Object> searchInfo = searchFieldMap.get(columnName);
				columnMeta.setDisplay( MapUtils.getBooleanValue(displayFieldMap, columnName,true));
				columnMeta.setSearchable(MapUtils.getBooleanValue(searchInfo, "searchable",true));
				columnMeta.setOp(MapUtils.getString(searchInfo, "op","="));
				metaInfo.put(columnName,columnMeta);
			}
			return metaInfo;
		} catch (SQLException e) {
			log.error("Error", e);
		}
		return null;
	}
}
