package net.javaoop.sx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.javaoop.sx.cache.SqlCache;
import net.javaoop.sx.cache.impl.MemorySqlCache;
import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.parser.Parser;
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
		// 初始化 配置类
		sxConfig = new SxConfig();

		// 加载SX.xml配置文件
		reader = XmlReader.read(ClassLoader.getSystemResourceAsStream(path));
		// 解析缓存节点
		resolveCacheNode();
		// 解析
		resolveEnabled();
		// 解析节点转换器
		resolveParserNode();

		// 解析SqlXml文件的扫描规则 以及路径
		resolveScannerNode();

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

	public void resolveEnabled() {
		XNode node = reader.evalNode("//configs/enabled");
		String scheme = node.getAttribute("scheme");
		String parser = node.getAttribute("parser");
		sxConfig.setScheme(StringUtils.isNotBlank(scheme) ? scheme
				: SxConfig.DEFAULT_SCHEME);
		sxConfig.setParser(parser);
	}

	/**
	 * 解析节点解析器
	 * 
	 * @param reader
	 * @param sxConfig
	 */
	public void resolveParserNode() {
		List<XNode> node_parsers = reader.evalNodes("//configs/parser");
		Map<String, Parser> parsers = new HashMap<String, Parser>();
		try {
			for (XNode node : node_parsers) {
				String resolve = node.getAttribute("resolve");
				if (StringUtils.isBlank(resolve)
						|| "true".equalsIgnoreCase(resolve)) {
					String parserName = node.getAttribute("name");
					String className = node.getAttribute("class");
					Parser parser = (Parser) Class.forName(className)
							.newInstance();
					parser.setName(parserName);
					List<XNode> childs = node.getChildNodes("node");
					Map<String, NodeParser> nodeParsers = new HashMap<String, NodeParser>();
					for (XNode child : childs) {
						String nodeName = child.getAttribute("name");
						String nodeClass = child.getAttribute("class");
						nodeParsers.put(nodeName,
								(NodeParser) Class.forName(nodeClass)
										.newInstance());
					}
					parser.setNodeParsers(nodeParsers);
					parser.setSqlCache(sxConfig.getSqlCache());
					parsers.put(parserName, parser);
				}
			}
			sxConfig.setParsers(parsers);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
		String className = scannerNode.getAttribute("class");
		Scanner scanner = null;
		try {
			if (StringUtils.isBlank(className)) {
				scanner = new ScannerImpl();
			} else {
				scanner = (Scanner) Class.forName(className).newInstance();
			}
			sxConfig.setScanner(scanner);
			sxConfig.setSqlXmlFiles(scanner.scan(sxConfig.getBasePackages(),
					sxConfig.getParsers()));
		} catch (InstantiationException e) {
			log.debug("扫描器:" + className + "类创建失败!!!");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			log.debug("扫描器:" + className + "类创建失败!!!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			log.debug("未找到扫描器:" + className + "类!!!");
			e.printStackTrace();
		}
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
		List<String> basePackages = new ArrayList<String>();
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
		sxConfig.setBasePackages(basePackages);
	}

}
