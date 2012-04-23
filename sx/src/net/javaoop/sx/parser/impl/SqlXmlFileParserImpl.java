package net.javaoop.sx.parser.impl;

import java.io.File;
import java.util.List;

import net.javaoop.sx.cache.SqlCache;
import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.parser.SqlXmlFileParser;
import net.javaoop.sx.xml.XNode;
import net.javaoop.sx.xml.XmlReader;

public class SqlXmlFileParserImpl extends SqlXmlFileParser {

	public void parseXmlFile(SqlCache cache, String scheme, String className, File file) {
		XmlReader reader = XmlReader.read(file);
		List<XNode> nodes = reader.evalNodes("//sqls/sql");

		for (int i = 0; i < nodes.size(); i++) {
			StringBuilder sb = new StringBuilder();
			XNode node = nodes.get(i);
			className = className + "." + node.getAttribute("id");
			parseSqlNode(node, sb);
			cache.put(scheme, className, sb.toString());
		}
	}

	public void parseSqlNode(XNode node, StringBuilder context) {
		NodeParser sqlNodeParser = new SqlNodeParserImpl();
		sqlNodeParser.parseNode(node, context);
	}
}
