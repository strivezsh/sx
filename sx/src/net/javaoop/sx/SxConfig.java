package net.javaoop.sx;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.javaoop.sx.cache.SqlCache;
import net.javaoop.sx.parser.Parser;
import net.javaoop.sx.scanner.Scanner;

public class SxConfig {
	/**
	 * 所有SQL语句的缓存
	 */
	private SqlCache sqlCache;
	private Map<String, Parser> parsers;
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

	public SqlCache getSqlCache() {
		return sqlCache;
	}

	public void setSqlCache(SqlCache sqlCache) {
		this.sqlCache = sqlCache;
	}

	public Map<String, Parser> getParsers() {
		return parsers;
	}

	public void setParsers(Map<String, Parser> parsers) {
		this.parsers = parsers;
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
}
