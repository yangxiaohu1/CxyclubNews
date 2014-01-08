package com.hugo.cxyclubnews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class News {
	private static final String requestUri = "http://cxyclub.cn/BaiDu.xml";
	private static final String titleExpression = "/document/item/title";

	public static List<String> requestNewsList()
			throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {
		List<String> list = new ArrayList<String>();
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(requestUri);

		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();
		NodeList titles = (NodeList) xPath.evaluate(titleExpression, document,
				XPathConstants.NODESET);
		System.out.println("begin request ...");
		for (int i = 0; i < titles.getLength(); i++) {
			Node title = titles.item(i);
			System.out.format("title: %s%n", title.getTextContent());
		}

		return list;
	}
	
	public static List<String> fakeRequestNewsList() {
		List<String> list = new ArrayList<String>();
		list.add("Aaa");
		list.add("Aab");
		list.add("Aac");
		return list;
	}
}
