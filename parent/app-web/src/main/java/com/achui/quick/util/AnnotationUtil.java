package com.achui.quick.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.annotation.AnnotationUtils;

import com.google.common.collect.Maps;

public class AnnotationUtil {

	public static Map<String, String> getFieldAnnotationMap(Class clzz,Class annotationType,String attr){
		Map<String, String> map = Maps.newHashMap();
		Field[] fields = FieldUtils.getAllFields(clzz);
		for(Field field : fields){
			Annotation anno = AnnotationUtils.getAnnotation(field, annotationType);
			String val = (String)AnnotationUtils.getValue(anno, attr);
			if(StringUtils.isNotEmpty(val)){
				map.put(val.toLowerCase(), field.getName());
			}else {
				map.put(field.getName().toLowerCase(), field.getName());
			}
		}
		return map;
	}
	public static Map<String, Object> getFieldMap(Class clzz,Class annotationType,String attr){
		Map<String, Object> map = Maps.newHashMap();
		Field[] fields = FieldUtils.getAllFields(clzz);
		for(Field field : fields){
			Annotation anno = AnnotationUtils.getAnnotation(field, annotationType);
			Object val = AnnotationUtils.getValue(anno, attr);
			map.put(field.getName(),val);
		}
		return map;
	}
	public static Map<String, Map<String,Object>> getFieldMap(Class clzz,Class annotationType){
		Map<String, Map<String,Object>> map = Maps.newHashMap();
		Field[] fields = FieldUtils.getAllFields(clzz);
		for(Field field : fields){
			Annotation anno = AnnotationUtils.getAnnotation(field, annotationType);
			if(anno != null)
				map.put(field.getName(),AnnotationUtils.getAnnotationAttributes(anno));
		}
		return map;
	}
}
