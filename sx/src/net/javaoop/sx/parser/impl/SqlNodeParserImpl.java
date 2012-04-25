package net.javaoop.sx.parser.impl;

import java.util.Map;

import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.xml.XNode;

public class SqlNodeParserImpl extends NodeParser {

	public void parseNode(XNode node, StringBuilder context, Map<String, NodeParser> nodeParsers) {
		context.append(parseChildNode(node, nodeParsers));
	}

}
