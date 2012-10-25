package net.javaoop.sx.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlReader {
	private static final Logger log = Logger.getLogger(XmlReader.class);
	private Document document;
	private XPath xpath;
	{
		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
	}

	private XmlReader() {
	}

	public static XmlReader read(InputStream inputStream) {
		XmlReader reader = null;
		try {
			reader = read(new InputSource(inputStream));
		} catch (Exception e) {
			log.debug("创建XmlReader失败!");
			e.printStackTrace();
		}
		return reader;
	}

	public static XmlReader read(File file) {
		XmlReader reader = null;
		try {
			reader = read(new FileReader(file));
		} catch (FileNotFoundException e) {
			log.debug("创建XmlReader失败," + (file == null ? "文件对象为空!" : "未找到XML文件:" + file.getPath()));
			e.printStackTrace();
		}
		return reader;
	}

	public static XmlReader read(FileReader fileReader) {
		XmlReader reader = null;
		try {
			reader = read(new InputSource(fileReader));
		} catch (Exception e) {
			log.debug("创建XmlReader失败!");
			e.printStackTrace();
		}
		return reader;
	}

	public static XmlReader read(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		XmlReader reader = new XmlReader();
		reader.document = builder.parse(inputSource);
		return reader;
	}

	public Object evaluate(String expression, QName returnType) {
		try {
			return evaluate(expression, document, returnType);
		} catch (Exception e) {
			throw new RuntimeException("Error evaluating XPath.  Cause: " + e, e);
		}
	}

	public Object evaluate(String expression, Object root, QName returnType) {
		try {
			return xpath.evaluate(expression, root, returnType);
		} catch (Exception e) {
			throw new RuntimeException("Error evaluating XPath.  Cause: " + e, e);
		}
	}

	public String evalString(String expression) {
		try {
			return (String) evaluate(expression, XPathConstants.STRING);
		} catch (Exception e) {
			throw new RuntimeException("Error evaluating XPath.  Cause: " + e, e);
		}
	}

	public Boolean evalBoolean(String expression) {
		return (Boolean) evaluate(expression, XPathConstants.BOOLEAN);
	}

	public Short evalShort(String expression) {
		return Short.valueOf(evalString(expression));
	}

	public Integer evalInteger(String expression) {
		return Integer.valueOf(evalString(expression));
	}

	public Long evalLong(String expression) {
		return Long.valueOf(evalString(expression));
	}

	public Float evalFloat(String expression) {
		return Float.valueOf(evalString(expression));
	}

	public Double evalDouble(String expression) {
		return (Double) evaluate(expression, XPathConstants.NUMBER);
	}

	public XNode evalNode(String expression) {
		Node node = (Node) evaluate(expression, XPathConstants.NODE);
		if (node == null) {
			return null;
		}
		return new XNode(node);
	}

	public List<XNode> evalNodes(String expression) {
		List<XNode> xnodes = new ArrayList<XNode>();
		NodeList nodes = (NodeList) evaluate(expression, XPathConstants.NODESET);
		for (int i = 0; i < nodes.getLength(); i++) {
			xnodes.add(new XNode(nodes.item(i)));
		}
		return xnodes;
	}
}
