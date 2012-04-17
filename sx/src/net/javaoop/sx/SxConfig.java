package net.javaoop.sx;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.javaoop.sx.cache.SqlCache;
import net.javaoop.sx.parser.SqlNodeParser;

public class SxConfig {
	/**
	 * 所有SQL语句的缓存
	 */
	private SqlCache sqlCache;

	/**
	 * <pre>
	 * 存放所有SQL XML文件
	 * 第一层key为scheme的name
	 * 第二层key为xml文件名或标识
	 * </pre>
	 */
	private Map<String, Map<String, List<File>>> sqlXmlFile = new HashMap<String, Map<String, List<File>>>();

	/**
	 * 存放节点转换器 key为节点名
	 */
	private Map<String, SqlNodeParser> sqlNodeParser = new HashMap<String, SqlNodeParser>();

	public SqlCache getSqlCache() {
		return sqlCache;
	}

	public void setSqlCache(SqlCache sqlCache) {
		this.sqlCache = sqlCache;
	}

	public Map<String, Map<String, List<File>>> getSqlXmlFile() {
		return sqlXmlFile;
	}

	public void setSqlXmlFile(Map<String, Map<String, List<File>>> sqlXmlFile) {
		this.sqlXmlFile = sqlXmlFile;
	}

	public Map<String, SqlNodeParser> getSqlNodeParser() {
		return sqlNodeParser;
	}

	public void setSqlNodeParser(Map<String, SqlNodeParser> sqlNodeParser) {
		this.sqlNodeParser = sqlNodeParser;
	}

}
