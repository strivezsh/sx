package net.javaoop.sx.parser;

import java.util.Map;

public class Parser {
	private String name;
	private SqlXmlFileParser sqlXmlFileParser;
	private NodeParser sqlNodeParser;
	private Map<String, NodeParser> nodeParsers;

	public Parser() {
		super();
	}

	public Parser(String name, SqlXmlFileParser sqlXmlFileParser, NodeParser sqlNodeParser, Map<String, NodeParser> nodeParsers) {
		super();
		this.name = name;
		this.sqlXmlFileParser = sqlXmlFileParser;
		this.sqlNodeParser = sqlNodeParser;
		this.nodeParsers = nodeParsers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SqlXmlFileParser getSqlXmlFileParser() {
		return sqlXmlFileParser;
	}

	public void setSqlXmlFileParser(SqlXmlFileParser sqlXmlFileParser) {
		this.sqlXmlFileParser = sqlXmlFileParser;
	}

	public NodeParser getSqlNodeParser() {
		return sqlNodeParser;
	}

	public void setSqlNodeParser(NodeParser sqlNodeParser) {
		this.sqlNodeParser = sqlNodeParser;
	}

	public Map<String, NodeParser> getNodeParsers() {
		return nodeParsers;
	}

	public void setNodeParsers(Map<String, NodeParser> nodeParsers) {
		this.nodeParsers = nodeParsers;
	}
}
