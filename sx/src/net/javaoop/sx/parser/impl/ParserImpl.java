package net.javaoop.sx.parser.impl;

import java.io.File;
import java.util.List;

import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.parser.Parser;
import net.javaoop.sx.xml.XNode;
import net.javaoop.sx.xml.XmlReader;

public class ParserImpl extends Parser {

	public void parseXmlFile(String scheme, String className, File file) {
		XmlReader reader = XmlReader.read(file);
		List<XNode> nodes = reader.evalNodes("//sqls/sql");
		for (int i = 0; i < nodes.size(); i++) {
			XNode node = nodes.get(i);
			StringBuilder sb = new StringBuilder();
			NodeParser nodeParser = getNodeParsers().get(node.getName());
			if (nodeParser != null) {
				nodeParser.parseNode(node, sb, getNodeParsers());
				className = className + "." + node.getAttribute("id");
				getSqlCache().put(scheme, className, sb.toString());
			}
		}
	}

}
