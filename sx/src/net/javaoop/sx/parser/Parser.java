package net.javaoop.sx.parser;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import net.javaoop.sx.SxConfig;
import net.javaoop.sx.cache.SqlCache;

public abstract class Parser {
	private String name;
	private Map<String, NodeParser> nodeParsers;
	private SxConfig sxConfig;

	public void parser() {
		SqlCache cache = getSxConfig().getSqlCache();
		Map<String, Map<String, File>> sqlXmlFiles = getSxConfig().getSqlXmlFiles();
		for (Entry<String, Map<String, File>> entry1 : sqlXmlFiles.entrySet()) {
			Map<String, File> classNameWidthSqlXmlFile = entry1.getValue();
			for (Entry<String, File> entry2 : classNameWidthSqlXmlFile.entrySet()) {
				String className = entry2.getKey();
				File xmlFile = entry2.getValue();
				if (xmlFile != null && xmlFile.exists()) {
					parseXmlFile(entry1.getKey(), className, xmlFile, cache);
				}
			}
		}
	}

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
