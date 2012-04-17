package net.javaoop.sx.parser.impl;

import net.javaoop.sx.parser.SqlNodeParser;
import net.javaoop.sx.xml.XNode;

public class ElseNodeParser extends SqlNodeParser {

	public void parseNode(XNode node, StringBuilder content) {
		content.append("<#else>");
		content.append(parseSqlNode(node));
	}

}
