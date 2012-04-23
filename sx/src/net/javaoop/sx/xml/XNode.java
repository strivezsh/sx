package net.javaoop.sx.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.CharacterData;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XNode {
	private Node node;
	private String name;
	private String text;
	private Map<String, String> attributes;

	public XNode() {
	}

	public XNode(Node node) {
		this.node = node;
		this.name = node.getNodeName();
		this.text = parseText(node);
		this.attributes = parseAttributes(node);
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public String getAttribute(String name) {
		return attributes.get(name);
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public List<XNode> getChildNodes() {
		NodeList nodeList = getNode().getChildNodes();
		List<XNode> list = new ArrayList<XNode>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			list.add(new XNode(nodeList.item(i)));
		}
		return list;
	}

	private Map<String, String> parseAttributes(Node node) {
		Map<String, String> attrs = new HashMap<String, String>();
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node attribute = null;
			for (int i = 0; i < attrMap.getLength(); i++) {
				attribute = attrMap.item(i);
				attrs.put(attribute.getNodeName(), attribute.getNodeValue());
			}
		}
		return attrs;
	}

	/**
	 * 如果是 嵌套标签的话 只获得第一个为text节点类型的 文本内容
	 * 
	 * @param node
	 * @return
	 */
	private String parseText(Node node) {
		String text = getTextData(node);
		if (text == null) {
			NodeList childs = node.getChildNodes();
			for (int i = 0; i < childs.getLength(); i++) {
				Node child = childs.item(i);
				text = getTextData(child);
				if (text != null) {
					break;
				}
			}
		}
		return text;
	}

	private String getTextData(Node node) {
		if (node.getNodeType() == Node.CDATA_SECTION_NODE || node.getNodeType() == Node.TEXT_NODE) {
			return ((CharacterData) node).getData();
		}
		return null;
	}

}