package net.javaoop.sx.parser.impl;

import java.io.File;
import java.util.List;

import net.javaoop.sx.cache.SqlCache;
import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.parser.Parser;
import net.javaoop.sx.xml.XNode;
import net.javaoop.sx.xml.XmlReader;

public class ParserImpl extends Parser {

	protected void parseXmlFile(String scheme, String className, File file, SqlCache cache) {
		XmlReader reader = XmlReader.read(file);
		List<XNode> nodes = reader.evalNodes("//sqls/sql");
		for (int i = 0; i < nodes.size(); i++) {
			XNode node = nodes.get(i);
			StringBuilder sb = new StringBuilder();
			NodeParser nodeParser = this.getNodeParsers().get(node.getName());
			nodeParser.parseNode(node, sb, this.getNodeParsers());
			className = className + "." + node.getAttribute("id");
			cache.put(scheme, className, sb.toString());
		}
	}

}
