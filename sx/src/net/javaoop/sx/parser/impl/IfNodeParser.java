package net.javaoop.sx.parser.impl;

import java.util.Map;

import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.xml.XNode;

public class IfNodeParser extends NodeParser {

	public void parseNode(XNode node, StringBuilder content, Map<String, NodeParser> nodeParsers) {
		content.append("<#if ");
		content.append(node.getAttribute("exp"));
		content.append(">");
		content.append(parseChildNode(node, nodeParsers));
		content.append("</#if>");
	}

}
