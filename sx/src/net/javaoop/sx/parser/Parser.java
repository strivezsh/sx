package net.javaoop.sx.parser;

import java.io.File;
import java.util.Map;

import net.javaoop.sx.SxConfig;
import net.javaoop.sx.cache.SqlCache;

public abstract class Parser {
	private String name;
	private Map<String, NodeParser> nodeParsers;
	private SxConfig sxConfig;

	protected abstract void parseXmlFile(String scheme, String className, File file, SqlCache cache);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, NodeParser> getNodeParsers() {
		return nodeParsers;
	}

	public void setNodeParsers(Map<String, NodeParser> nodeParsers) {
		this.nodeParsers = nodeParsers;
	}

	public SxConfig getSxConfig() {
		return sxConfig;
	}

	public void setSxConfig(SxConfig sxConfig) {
		this.sxConfig = sxConfig;
	}
}
