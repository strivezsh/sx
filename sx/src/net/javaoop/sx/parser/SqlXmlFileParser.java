package net.javaoop.sx.parser;

import java.io.File;
import java.util.Map;

/**
 * sql文件解析器
 * 
 * @author zhou
 * 
 */
public interface SqlXmlFileParser {

	/**
	 * 解析SQL文件
	 * 
	 * @param file
	 *            SQL文件
	 */
	public void parse(File file, Map<String, SqlNodeParser> sqlNodeParsers);
}
