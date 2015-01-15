package com.achui.quick.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.joox.JOOX.$;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.joox.Match;
import org.junit.Test;
import org.w3c.dom.Element;

import com.achui.quick.domain.custom.ZTreeNode;

public class XMLUtilTest {

	@Test
	public void test() {
		try {
			FileInputStream in = new FileInputStream(new File("D:/xml.xml"));
			Element element = W3CXML2TreeUtil.parse(in);
			ZTreeNode zTreeNode = W3CXML2TreeUtil.ztree(element);
			System.out.println(zTreeNode.toJson());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testToXML() throws Exception{
//		ZTreeNode node = new ZTreeNode();
//		node.setName("node1");
//		List<ZTreeNode> list = new ArrayList<>();
//		List<ZTreeNode> list2 = new ArrayList<>();
//		
//		ZTreeNode node1 = new ZTreeNode();
//		node1.setName("node11");
//		ZTreeNode node2 = new ZTreeNode();
//		node2.setName("node12");
//		list.add(node1);
//		list.add(node2);
//		
//		ZTreeNode node22 = new ZTreeNode();
//		node22.setName("node121");
//		list2.add(node22);
//		node2.setChildren(list2);
//		node.setChildren(list);
		
		FileInputStream in = new FileInputStream(new File("D:/xml.xml"));
		Element element = W3CXML2TreeUtil.parse(in);
		ZTreeNode zTreeNode = W3CXML2TreeUtil.ztree(element);
		
		Document doc = DocumentHelper.createDocument();
		org.dom4j.Element element1 = doc.addElement("root");
		element1 = W3CXML2TreeUtil.toXML(element1, zTreeNode);
		System.out.println(doc.asXML());
	}

}
