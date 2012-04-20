package net.javaoop.sx;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.javaoop.sx.cache.SqlCache;
import net.javaoop.sx.parser.SqlNodeParser;
import net.javaoop.sx.scanner.Scanner;

public class SxConfig {

	public static final String DEFAULT_SCHEME = "default";

	/**
	 * 设置启用方案
	 */
	private String scheme;

	private List<String> basePackages = new ArrayList<String>();
	private Scanner scanner;

	/**
	 * 所有SQL语句的缓存
	 */
	private SqlCache sqlCache;

	/**
	 * <pre>
	 * 存放所有SqlXml文件
	 * 第一层key为scheme的name
	 * 第二层key为所对应类全称 value为SqlXml文件
	 * </pre>
	 */
	private Map<String, Map<String, File>> sqlXmlFiles = new HashMap<String, Map<String, File>>();

	/**
	 * 存放节点转换器 key为节点名
	 */
	private Map<String, SqlNodeParser> sqlNodeParsers = new HashMap<String, SqlNodeParser>();

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public List<String> getBasePackages() {
		return basePackages;
	}

	public void setBasePackages(List<String> basePackages) {
		this.basePackages = basePackages;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public SqlCache getSqlCache() {
		return sqlCache;
	}

	public void setSqlCache(SqlCache sqlCache) {
		this.sqlCache = sqlCache;
	}

	public Map<String, Map<String, File>> getSqlXmlFiles() {
		return sqlXmlFiles;
	}

	public void setSqlXmlFiles(Map<String, Map<String, File>> sqlXmlFiles) {
		this.sqlXmlFiles = sqlXmlFiles;
	}

	public Map<String, SqlNodeParser> getSqlNodeParsers() {
		return sqlNodeParsers;
	}

	public void setSqlNodeParsers(Map<String, SqlNodeParser> sqlNodeParsers) {
		this.sqlNodeParsers = sqlNodeParsers;
	}

}
