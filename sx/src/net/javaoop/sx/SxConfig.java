package net.javaoop.sx;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.javaoop.sx.cache.SqlCache;
import net.javaoop.sx.parser.impl.SqlNodeParser;

public class SxConfig {
	private SqlCache sqlCache;
	private List<String> sxScan = new ArrayList<String>();
	private Map<String, File> sqlXmlFile = new HashMap<String, File>();

	private Map<String, SqlNodeParser> sqlNodeParser;

	public SqlCache getSqlCache() {
		return sqlCache;
	}

	public void setSqlCache(SqlCache sqlCache) {
		this.sqlCache = sqlCache;
	}

	public Map<String, SqlNodeParser> getSqlNodeParser() {
		return sqlNodeParser;
	}

	public void setSqlNodeParser(Map<String, SqlNodeParser> sqlNodeParser) {
		this.sqlNodeParser = sqlNodeParser;
	}

	public List<String> getSxScan() {
		return sxScan;
	}

	public void addSxScan(String sxScan) {
		this.getSxScan().add(sxScan);
	}

	public Map<String, File> getSqlXmlFile() {
		return sqlXmlFile;
	}

	public void setSqlXmlFile(Map<String, File> sqlXmlFile) {
		this.sqlXmlFile = sqlXmlFile;
	}

}
