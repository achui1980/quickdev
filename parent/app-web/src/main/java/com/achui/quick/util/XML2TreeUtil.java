package com.achui.quick.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;

import com.achui.quick.domain.custom.ZTreeNode;
import com.google.common.collect.Maps;
import static org.joox.JOOX.*;
public class XML2TreeUtil {

	private static final TransformerFactory  transformerFactory = TransformerFactory.newInstance() ;
	public static Element parse(InputStream in){
		
		if(in == null) return null;
		SAXReader reader = new SAXReader();
		Element element = null;
		try {
			Document document = reader.read(in);
			element = document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return element;
	}
	
	public static ZTreeNode ztree(Element element){
		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setName(element.getName());
		if(!StringUtils.isEmpty(element.getTextTrim())){
			zTreeNode.setName(element.getName()+" - "+element.getTextTrim() );
		}
		zTreeNode.setLeaf(true);
		org.w3c.dom.Element w3cElement = convert2W3cElement(element);
		
		System.out.println($(w3cElement).xpath());
		Iterator<Attribute> attrs = element.attributeIterator();
		Map<String,String> map = Maps.newHashMap();
		while(attrs.hasNext()){
			Attribute attribute = attrs.next();
			map.put(attribute.getName(), attribute.getValue());
		}
		//zTreeNode.setAttrs(map);
		if(element.elements().size() > 0){
			zTreeNode.setLeaf(false);
		}
		Iterator<Element> iterator = element.elementIterator();
		List<ZTreeNode> list = new ArrayList<ZTreeNode>();
		while(iterator.hasNext()){
			Element ele = iterator.next();
			list.add(ztree(ele));
		}
		zTreeNode.setChildren(list);
		return zTreeNode;
		
	}
	public static Element parse(String xml){
		if(StringUtils.isEmpty(xml)) return null;
		InputStream in = new ByteArrayInputStream(xml.getBytes());
		return parse(in);
	}
	public static Element parse(File xmlFile){
		if(!xmlFile.exists()) return null;
		InputStream in = null;
		try {
			in = new FileInputStream(xmlFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return parse(in);
	}
	
	public static org.w3c.dom.Element convert2W3cElement(Element element){
		SAXSource source = new DocumentSource(element);
		DOMResult result = new DOMResult();
		try {
			Transformer transform = transformerFactory.newTransformer();
			transform.transform(source, result);
			return (org.w3c.dom.Element)result.getNode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
