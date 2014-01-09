package com.hugo.cxyclubnews.utils;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Foo {

	private static final String requestUri = "http://cxyclub.cn/BaiDu.xml";

	public static void main(String[] args) {
		System.out.println("Test SAX");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			MyHandler myHandler = new MyHandler();
			long start = System.currentTimeMillis();
			parser.parse(requestUri, myHandler);
			long end = System.currentTimeMillis();
			System.out.format("Elapsed %d seconds.", (end - start) / 1000);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static class MyHandler extends DefaultHandler {
		private int eCnt = 0;

		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.startDocument();
			System.out.println("Foo.MyHandler.startDocument()");
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			super.startElement(uri, localName, qName, attributes);
			eCnt++;
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// TODO Auto-generated method stub
			super.endElement(uri, localName, qName);
			// System.out.println("element: " + localName);
		}

		@Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.endDocument();
			System.out.println("Foo.MyHandler.endDocument()");
			System.out.format("total read elements: %d%n", eCnt);
		}
	}
}
