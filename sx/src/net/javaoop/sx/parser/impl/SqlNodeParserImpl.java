package net.javaoop.sx.parser.impl;

import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.xml.XNode;

public class SqlNodeParserImpl extends NodeParser {

	public void parseNode(XNode node, StringBuilder context) {
		context.append(parseChildNode(node));
	}

}
