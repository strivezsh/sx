package net.javaoop.sx.parser;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.javaoop.sx.SxConfig;
import net.javaoop.sx.cache.SqlCache;
import net.javaoop.sx.xml.XNode;

/**
 * sql文件解析器
 * 
 * @author zhou
 * 
 */
public abstract class SqlXmlFileParser {
	private SxConfig sxConfig;
	/**
	 * 默认SQL标签解析类 不对SQL标签做任何处理 直接处理子标签
	 */
	protected NodeParser sqlNodeParser = new NodeParser() {
		public void parseNode(XNode node, StringBuilder context, Map<String, NodeParser> nodeParsers) {
			parseChildNode(node, nodeParsers);
		}
	};

	void doParse() {
		SqlCache cache = getSxConfig().getSqlCache();
		Map<String, Map<String, File>> sqlXmlFiles = getSxConfig().getSqlXmlFiles();
		for (Iterator<Entry<String, Map<String, File>>> ei1 = sqlXmlFiles.entrySet().iterator(); ei1.hasNext();) {
			Entry<String, Map<String, File>> ev1 = ei1.next();
			Map<String, File> classNameAndSqlXmlFile = ev1.getValue();
			for (Iterator<Entry<String, File>> ei2 = classNameAndSqlXmlFile.entrySet().iterator(); ei2.hasNext();) {
				Entry<String, File> ev2 = ei2.next();
				String className = ev2.getKey();
				File xmlFile = ev2.getValue();
				if (xmlFile != null && xmlFile.exists()) {
					parseXmlFile(cache, ev1.getKey(), className, xmlFile);
				}
			}
		}
	}

	protected abstract void parseXmlFile(SqlCache cache, String scheme, String className, File file);

	public SxConfig getSxConfig() {
		return sxConfig;
	}

	public void setSxConfig(SxConfig sxConfig) {
		this.sxConfig = sxConfig;
	}
}
