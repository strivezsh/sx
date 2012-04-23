package net.javaoop.sx;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.javaoop.sx.cache.SqlCache;
import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.scanner.Scanner;

public class SxConfig {

	public static final String DEFAULT_SCHEME = "default";
	public static final String DEFAUTL_PARSER = "freemarker";

	/**
	 * 所有SQL语句的缓存
	 */
	private SqlCache sqlCache;

	/**
	 * 设置启用方案
	 */
	private String scheme;
	private String parser;

	private List<String> basePackages;
	private Scanner scanner;

	/**
	 * <pre>
	 * 存放所有SqlXml文件
	 * 第一层key为scheme的name
	 * 第二层key为所对应类全称 value为SqlXml文件
	 * </pre>
	 */
	private Map<String, Map<String, File>> sqlXmlFiles;

	/**
	 * 存放节点转换器 key为节点名
	 */
	private Map<String, Map<String, NodeParser>> sqlNodeParsers;

	public SqlCache getSqlCache() {
		return sqlCache;
	}

	public void setSqlCache(SqlCache sqlCache) {
		this.sqlCache = sqlCache;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getParser() {
		return parser;
	}

	public void setParser(String parser) {
		this.parser = parser;
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

	public Map<String, Map<String, File>> getSqlXmlFiles() {
		return sqlXmlFiles;
	}

	public void setSqlXmlFiles(Map<String, Map<String, File>> sqlXmlFiles) {
		this.sqlXmlFiles = sqlXmlFiles;
	}

	public Map<String, Map<String, NodeParser>> getSqlNodeParsers() {
		return sqlNodeParsers;
	}

	public void setSqlNodeParsers(Map<String, Map<String, NodeParser>> sqlNodeParsers) {
		this.sqlNodeParsers = sqlNodeParsers;
	}

}
