package com.hugo.cxyclubnews.utils;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class News {
	private static final String requestUri = "http://cxyclub.cn/BaiDu.xml";
	private static final String itemExpressin = "/document/item/title";

	public static ArrayList<String> getTitles() {
		ArrayList<String> list = new ArrayList<String>();

		System.out.println("Begin get data...");

		// DOM方式加载内存中XML, 使用XPATH查找Title
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			long start = System.currentTimeMillis();

			Document document = builder.parse(requestUri);
			long end = System.currentTimeMillis();

			System.out.format("Elapsed time: %d seconds%n",
					(end - start) / 1000);

			Thread.sleep(2000);

			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xpath = xPathFactory.newXPath();

			String website = xpath.evaluate("/document/webSite", document);
			String updatePeri = xpath
					.evaluate("/document/updatePeri", document);
			NodeList titles = (NodeList) xpath.evaluate(itemExpressin,
					document, XPathConstants.NODESET);
			System.out.println("------- cxyclub.cn -------");
			System.out.println(website);
			System.out.println(updatePeri);
			System.out.println("Follows are titles info, counts = "
					+ titles.getLength());
			System.out.println("  ----- news -----  ");
			for (int i = 0; i < titles.getLength(); i++) {
				Node node = titles.item(i);
				String titleString = node.getTextContent();
				list.add(titleString);
				// System.out.format("title: %s%n", node.getTextContent());

			}

			return list;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
