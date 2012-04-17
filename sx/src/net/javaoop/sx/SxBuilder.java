package net.javaoop.sx;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.javaoop.sx.parser.SqlNodeParser;
import net.javaoop.sx.utils.Assert;
import net.javaoop.sx.utils.StringUtils;
import net.javaoop.sx.xml.XNode;
import net.javaoop.sx.xml.XmlReader;

public class SxBuilder {

	public Sx build(String path) {
		// 加载SX.xml配置文件
		XmlReader reader = XmlReader.read(ClassLoader.getSystemResourceAsStream(path));
		SxConfig sxConfig = new SxConfig();
		// 解析SqlXml文件的扫描规则 以及路径
		buildSqlXmlFile(sxConfig, reader);

		// 解析节点转换器
		//buildSqlNodeParser(sxConfig, reader);

		Sx sx = new Sx();
		sx.setSxConfig(sxConfig);
		return sx;
	}

	/**
	 * 解析节点解析器
	 * 
	 * @param reader
	 * @param sxConfig
	 */
	public void buildSqlNodeParser(SxConfig sxConfig, XmlReader reader) {
		List<XNode> sql_parsers = reader.evalNodes("//configs/parser/parser");
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

	public void buildSqlXmlFile(SxConfig sxConfig, XmlReader reader) {
		XNode scan = reader.evalNode("//configs/scan");
		sxConfig.setScheme(scan.getAttribute("scheme"));
		List<String> basePackages = getBasePackage(scan);

		for (Iterator<String> it = basePackages.iterator(); it.hasNext();) {
			String basePackage = it.next().replace('.', '/');
			System.out.println(basePackage);
		}
		List<XNode> schemes = reader.evalNodes("//configs/scan/scheme");
		Map<String, List<File>> sqlXmlFiles = new HashMap<String, List<File>>();
	}

	/**
	 * 解析 扫描路径
	 * @param scan
	 * @return
	 */
	public List<String> getBasePackage(XNode scan) {
		String basePackage = scan.getAttribute("base-package");
		Assert.notBlank(basePackage, "SQL XML文件扫描包路径不能为空!!!");
		List<String> list = new ArrayList<String>();
		if (basePackage.indexOf(',') != -1) {
			String[] ss = basePackage.split(",");
			for (String s : ss) {
				if (StringUtils.isNotBlank(s)) {
					list.add(s);
				}
			}
		} else {
			list.add(basePackage);
		}
		return list;
	}
}
