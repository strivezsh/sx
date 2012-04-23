package net.javaoop.sx.parser.impl;

import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.xml.XNode;

public class IfNodeParser extends NodeParser {

	public void parseNode(XNode node, StringBuilder content) {
		content.append("<#if ");
		content.append(node.getAttribute("exp"));
		content.append(">");
		content.append(parseChildNode(node));
		content.append("</#if>");
	}

}
