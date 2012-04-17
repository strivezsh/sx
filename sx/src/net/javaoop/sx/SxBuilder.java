package net.javaoop.sx;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.javaoop.sx.parser.SqlNodeParser;
import net.javaoop.sx.utils.Assert;
import net.javaoop.sx.xml.XNode;
import net.javaoop.sx.xml.XmlReader;

public class SxBuilder {

	public Sx build(String path) {
		XmlReader reader = XmlReader.read(ClassLoader.getSystemResourceAsStream(path));
		SxConfig sxConfig = new SxConfig();
		buildSqlNodeParser(sxConfig, reader);

		XNode sxScan_node = reader.evalNode("//configs/sx-scan");
		buildSqlXmlFile(sxScan_node, sxConfig);

		return null;
	}

	/**
	 * 解析节点解析器
	 * 
	 * @param reader
	 * @param sxConfig
	 */
	public void buildSqlNodeParser(SxConfig sxConfig, XmlReader reader) {
		List<XNode> sql_parsers = reader.evalNodes("//configs/node-parser/parser");
		Map<String, SqlNodeParser> sqlNodeParsers = new HashMap<String, SqlNodeParser>();
		for (XNode n : sql_parsers) {
			String parserName = null;
			String parserClass = null;
			try {
				parserName = n.getAttribute("name");
				parserClass = n.getAttribute("class");
				sqlNodeParsers.put(parserName, (SqlNodeParser) Class.forName(parserClass).newInstance());
			} catch (InstantiationException e) {
				throw new RuntimeException("解析类" + parserClass + "创建失败!!!", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("解析类" + parserClass + "创建失败!!!", e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("解析类" + parserClass + "找不到!!!", e);
			}
		}
		sxConfig.setSqlNodeParser(sqlNodeParsers);
	}

	public void buildSqlXmlFile(XNode sxScan_node, SxConfig sxConfig) {
		String basePackage = sxScan_node.getAttribute("base-package");
		Assert.notBlank(basePackage, "SQL XML文件扫描包路径不能为空!!!");
		if (basePackage.indexOf(',') != -1) {
			String[] ss = basePackage.split(",");
			for (String s : ss) {
				sxConfig.getSxScan().add(s);
			}
		} else {
			sxConfig.getSxScan().add(basePackage);
		}
		String path = sxConfig.getSxScan().replace('.', '/');
	}
}
