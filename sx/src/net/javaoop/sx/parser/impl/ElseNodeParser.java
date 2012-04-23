package net.javaoop.sx.parser.impl;

import java.util.Map;

import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.xml.XNode;

public class ElseNodeParser extends NodeParser {

	public void parseNode(XNode node, StringBuilder content, Map<String, NodeParser> nodeParsers) {
		content.append("<#else>");
		content.append(parseChildNode(node, nodeParsers));
	}

}
