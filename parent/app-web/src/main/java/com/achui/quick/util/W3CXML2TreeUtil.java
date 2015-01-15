package com.achui.quick.util;

import static org.joox.JOOX.$;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.dom.DOMNodeHelper;
import org.dom4j.io.DOMWriter;
import org.dom4j.io.SAXReader;
import org.joox.Match;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.achui.quick.domain.custom.ZTreeNode;
import com.google.common.collect.Maps;
public class W3CXML2TreeUtil {

	public static Element parse(InputStream in){
		if(in == null) return null;
		SAXReader reader = new SAXReader();
		Element element = null;
		try {
			Document document = reader.read(in);
			DOMWriter writer = new DOMWriter();
			org.w3c.dom.Document w3cDocument = writer.write(document);
			element = w3cDocument.getDocumentElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return element;
	}
	public static ZTreeNode ztree(Node element){
		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setName(element.getNodeName());
		zTreeNode.setLeaf(false);
		if($(element).children().isEmpty()){
			zTreeNode.setName($(element).tag() +" - "+$(element).text().trim() );
			zTreeNode.setXmlValue($(element).text());
			zTreeNode.setLeaf(true);
		}
		NamedNodeMap attrs = element.getAttributes();
		TreeMap<String,String> map = new TreeMap<>();
		String xpath = $(element).xpath();
		for(int i=0; i<attrs.getLength(); i++){
			Attr attr = (Attr)attrs.item(i);
			map.put(attr.getName(), $(element).attr(attr.getName()));
			String attributeXpath = xpath + "/@" + attr.getName();
			map.put(attr.getName()+"_xpath", attributeXpath);
			System.out.println($(element).tag()+":"+attr.getName()+":"+$(element).attr(attr.getName()));
		}
		zTreeNode.setAttrs(map);
		zTreeNode.setXpath(xpath);
		zTreeNode.setXmlNode($(element).tag());
		List<ZTreeNode> list = new ArrayList<ZTreeNode>();
		for (int i=0; i < element.getChildNodes().getLength(); i++) {
		    Node node = element.getChildNodes().item(i);
		    if(node.getNodeType() == Node.ELEMENT_NODE)
		    	list.add(ztree(node));
		}
		
		zTreeNode.setChildren(list);
		return zTreeNode;
		
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
	
	public static org.dom4j.Element toXML(org.dom4j.Element element,ZTreeNode zTreeNode){
		org.dom4j.Element node = element.addElement(zTreeNode.getXmlNode());
		if(StringUtils.isNoneEmpty(zTreeNode.getXmlValue())){
			node.setText(zTreeNode.getXmlValue());
		}
		Map<String, String> attrs = zTreeNode.getAttrs();
		if(!attrs.isEmpty()){
			Iterator<String> iterator = attrs.keySet().iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				String value = attrs.get(key);
				node.addAttribute(key, value);
			}
		}
		List<ZTreeNode> list = (List<ZTreeNode>) zTreeNode.getChildren();
		if(CollectionUtils.isNotEmpty(list) && list.size() > 0){
			for(ZTreeNode ztree : list)
				toXML(node, ztree);
		}
		return node;
	}
	
}
