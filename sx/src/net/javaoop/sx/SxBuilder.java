package net.javaoop.sx;

import java.util.List;
import java.util.Map;

import net.javaoop.sx.cache.SqlCache;
import net.javaoop.sx.cache.impl.MemorySqlCache;
import net.javaoop.sx.parser.SqlNodeParser;
import net.javaoop.sx.scanner.Scanner;
import net.javaoop.sx.scanner.impl.ScannerImpl;
import net.javaoop.sx.utils.AssertUtils;
import net.javaoop.sx.utils.StringUtils;
import net.javaoop.sx.xml.XNode;
import net.javaoop.sx.xml.XmlReader;

import org.apache.log4j.Logger;

public class SxBuilder {
	private static final Logger log = Logger.getLogger(SxBuilder.class);
	private SxConfig sxConfig;
	private XmlReader reader;

	public Sx build(String path) {
		// 加载SX.xml配置文件
		reader = XmlReader.read(ClassLoader.getSystemResourceAsStream(path));
		sxConfig = new SxConfig();

		resolveCacheNode();
		// 解析SqlXml文件的扫描规则 以及路径
		resolveScannerNode();
		// 解析节点转换器
		// resolveParsersNode();

		Sx sx = new Sx();
		sx.setSxConfig(sxConfig);
		return sx;
	}

	public void resolveCacheNode() {
		XNode cacheNode = reader.evalNode("//configs/cache");
		String className = cacheNode.getAttribute("class");
		SqlCache cache = null;
		if (StringUtils.isBlank(className)) {
			cache = new MemorySqlCache();
		} else {
			try {
				cache = (SqlCache) Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				log.debug("缓存:" + className + "类创建失败!!!", e);
			} catch (IllegalAccessException e) {
				log.debug("缓存:" + className + "类创建失败!!!", e);
			} catch (ClassNotFoundException e) {
				log.debug("未找到缓存:" + className + "类!!!", e);
			}
		}
		sxConfig.setSqlCache(cache);
	}

	/**
	 * 解析扫描节点
	 * 
	 * @param sxConfig
	 * @param reader
	 */
	public void resolveScannerNode() {
		XNode scannerNode = reader.evalNode("//configs/scanner");
		resolveBasePackage(scannerNode);
		resolveScheme(scannerNode);
		String className = scannerNode.getAttribute("class");
		Scanner scanner = null;
		try {
			if (StringUtils.isBlank(className)) {
				scanner = new ScannerImpl();
			} else {
				scanner = (Scanner) Class.forName(className).newInstance();
			}
			scanner.setSxConfig(sxConfig);
			sxConfig.setScanner(scanner);
		} catch (InstantiationException e) {
			e.printStackTrace();
			log.debug("扫描器:" + className + "类创建失败!!!", e);
		} catch (IllegalAccessException e) {
			log.debug("扫描器:" + className + "类创建失败!!!", e);
		} catch (ClassNotFoundException e) {
			log.debug("未找到扫描器:" + className + "类!!!", e);
		}
		scanner.scan();
	}

	/**
	 * 解析 扫描路径
	 * 
	 * @param scan
	 * @return
	 */
	public void resolveBasePackage(XNode scan) {
		String basePackage = scan.getAttribute("base-package");
		AssertUtils.notBlank(basePackage, "SQL XML文件扫描包路径不能为空!!!");
		List<String> basePackages = sxConfig.getBasePackages();
		if (basePackage.indexOf(',') != -1) {
			String[] ss = basePackage.split(",");
			for (String s : ss) {
				if (StringUtils.isNotBlank(s)) {
					basePackages.add(s);
				}
			}
		} else {
			basePackages.add(basePackage);
		}
	}

	public void resolveScheme(XNode scan) {
		String scheme = scan.getAttribute("scheme");
		sxConfig.setScheme(StringUtils.isNotBlank(scheme) ? scheme : SxConfig.DEFAULT_SCHEME);
	}

	/**
	 * 解析节点解析器
	 * 
	 * @param reader
	 * @param sxConfig
	 */
	public void resolveParsersNode() {
		List<XNode> sql_parsers = reader.evalNodes("//configs/parsers/parser");
		Map<String, SqlNodeParser> sqlNodeParsers = sxConfig.getSqlNodeParsers();
		for (XNode n : sql_parsers) {
			String parserName = null;
			String parserClass = null;
			try {
				parserName = n.getAttribute("name");
				parserClass = n.getAttribute("class");
				sqlNodeParsers.put(parserName, (SqlNodeParser) Class.forName(parserClass).newInstance());
			} catch (InstantiationException e) {
				log.debug("解析类" + parserClass + "创建失败!!!", e);
			} catch (IllegalAccessException e) {
				log.debug("解析类" + parserClass + "创建失败!!!", e);
			} catch (ClassNotFoundException e) {
				log.debug("解析类" + parserClass + "找不到!!!", e);
			}
		}
	}

}
