package net.javaoop.sx.scanner.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.javaoop.sx.SxBuilder;
import net.javaoop.sx.SxConfig;
import net.javaoop.sx.parser.Parser;
import net.javaoop.sx.scanner.Scanner;
import net.javaoop.sx.utils.ResourceUtils;

import org.apache.log4j.Logger;

/**
 * 此类用于扫描SqlXml配置文件
 * 
 * @author zhou
 * 
 */
public class ScannerImpl implements Scanner {
	private static final Logger log = Logger.getLogger(SxBuilder.class);

	/**
	 * 用于扫描文件名后缀为Sql的文件
	 */
	private static final String FILE_NAME_SUFFIX = "Sql.";

	/**
	 * 用于扫描文件后缀为xml的文件
	 */
	private static final String FILE_SUFFIX = ".xml";

	public Map<String, Map<String, File>> scan(List<String> basePackages, Map<String, Parser> parsers) {
		Map<String, Map<String, File>> sqlXmlFiles = new HashMap<String, Map<String, File>>();
		for (Iterator<String> it = basePackages.iterator(); it.hasNext();) {
			String basePackage = it.next();
			try {
				File directory = ResourceUtils.getFile(basePackage.replace('.', '/'));
				doScan(basePackage, directory, sqlXmlFiles, parsers);
			} catch (FileNotFoundException e) {
				log.debug("根据包名:" + basePackage + ",未找到对应目录绝对路径!!!", e);
			}
		}
		return sqlXmlFiles;
	}

	public void doScan(String basePackage, File directory, Map<String, Map<String, File>> sqlXmlFiles, Map<String, Parser> parsers) {
		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isFile()) {
				String fileName = file.getName();
				int fileNameSuffixIndex = fileName.indexOf(FILE_NAME_SUFFIX);
				int lastIndex = fileName.lastIndexOf('.');
				String suffix = fileName.substring(lastIndex);
				if (FILE_SUFFIX.equalsIgnoreCase(suffix) && (fileNameSuffixIndex != -1)) {
					try {
						String scheme = fileName.substring(fileNameSuffixIndex + 4);
						lastIndex = scheme.lastIndexOf('.');
						if (lastIndex != -1) {
							scheme = scheme.substring(0, lastIndex);
						} else {
							scheme = SxConfig.DEFAULT_SCHEME;
						}
						Map<String, File> map = sqlXmlFiles.get(scheme);
						if (map == null) {
							map = new HashMap<String, File>();
							sqlXmlFiles.put(scheme, map);
						}
						String className = basePackage + "." + fileName.substring(0, fileNameSuffixIndex);
						parseXmlFile(scheme, className, file, parsers);
						map.put(className, file);
					} catch (RuntimeException e) {
//						log.debug("文件名:" + fileName + ",不符合规范(文件必须含有后缀,且文件名后三位字母为Sql,例如:TestDaoSql.xml)!!!");
						e.printStackTrace();
					}
				}
			} else if (file.isDirectory()) {
				doScan(basePackage + "." + file.getName(), file, sqlXmlFiles, parsers);
			}
		}
	}

	public void parseXmlFile(String scheme, String className, File file, Map<String, Parser> parsers) {
		for (Entry<String, Parser> entry : parsers.entrySet()) {
			entry.getValue().parseXmlFile(scheme, className, file);
		}
	}
}
