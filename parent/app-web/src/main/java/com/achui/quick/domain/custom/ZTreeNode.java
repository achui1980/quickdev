package com.achui.quick.domain.custom;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ZTreeNode extends TreeNode{
	

	private TreeMap<String, String> attrs = new TreeMap<>();
	private String xpath;
	private String xmlNode;
	private String xmlValue;
	
	@Override
	public String toJson() {
		String json = "{}";
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		try {
			 json =  mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(TreeMap<String, String> attrs) {
		this.attrs = attrs;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getXmlNode() {
		return xmlNode;
	}

	public void setXmlNode(String xmlNode) {
		this.xmlNode = xmlNode;
	}

	public String getXmlValue() {
		return xmlValue;
	}

	public void setXmlValue(String xmlValue) {
		this.xmlValue = xmlValue;
	}
	
}
