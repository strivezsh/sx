package net.javaoop.sx.parser.impl;

import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.xml.XNode;

public class ElseIfNodeParser extends NodeParser {

	public void parseNode(XNode node, StringBuilder content) {
		content.append("<#elseif ");
		content.append(node.getAttribute("exp"));
		content.append(">");
		content.append(parseChildNode(node));
	}
}
