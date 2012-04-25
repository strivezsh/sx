package net.javaoop.sx.parser.impl;

import java.util.Map;

import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.xml.XNode;

public class ElseIfNodeParser extends NodeParser {

	public void parseNode(XNode node, StringBuilder content, Map<String, NodeParser> nodeParsers) {
		content.append("<#elseif ");
		content.append(node.getAttribute("exp"));
		content.append(">");
		content.append(parseChildNode(node, nodeParsers));
	}
}
