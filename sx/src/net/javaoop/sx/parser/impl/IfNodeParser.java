package net.javaoop.sx.parser.impl;

import net.javaoop.sx.parser.SqlNodeParser;
import net.javaoop.sx.xml.XNode;

public class IfNodeParser extends SqlNodeParser {

	public void parseNode(XNode node, StringBuilder content) {
		content.append("<#if ");
		content.append(node.getAttribute("exp"));
		content.append(">");
		content.append(parseChildNode(sqlNodeParsers, node));
		content.append("</#if>");
	}

}
