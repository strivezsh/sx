package net.javaoop.sx.parser.impl;

import java.util.Map;

import net.javaoop.sx.xml.XNode;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class SqlNodeParser {

	/**
	 * 解析sql标签内特定标签
	 * 
	 * @param node
	 *            标签对象
	 * @param context
	 *            解析结果
	 */
	protected abstract void parseNode(XNode node, StringBuilder context);

	/**
	 * 把sql标签的 内容 转换为脚本语言
	 * 
	 * @param node
	 *            sql节点
	 * @return 转换后的脚本语言
	 */
	public String parseSqlNode(Map<String, SqlNodeParser> sqlNodeParsers, XNode node) {
		StringBuilder contents = new StringBuilder();
		NodeList childs = node.getNode().getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			XNode child = new XNode(childs.item(i));
			if (child.getNode().getNodeType() == Node.CDATA_SECTION_NODE || child.getNode().getNodeType() == Node.TEXT_NODE) {
				contents.append(child.getText());
			} else {
				String nodeName = child.getNode().getNodeName();
				SqlNodeParser parser = sqlNodeParsers.get(nodeName);
				if (parser == null) {
					throw new RuntimeException("不能解析此类型节点 <" + nodeName + "> !!!");
				}
				parser.parseNode(child, contents);
			}
		}
		return contents.toString();
	}
}
