package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.javaoop.sx.Sx;
import net.javaoop.sx.SxBuilder;
import net.javaoop.sx.SxConfig;
import net.javaoop.sx.cache.SqlCache;
import net.javaoop.sx.parser.NodeParser;
import net.javaoop.sx.parser.Parser;
import net.javaoop.sx.utils.ResourceUtils;

import org.apache.log4j.Logger;

public class Test {
	private static final Logger log = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		t();
	}

	public static void t() {
		SxBuilder builder = new SxBuilder();
		Sx sx = builder.build("SX.xml");
		SxConfig config = sx.getSxConfig();
		List<String> bp = config.getBasePackages();
		System.out.println(bp);
		Map<String, Map<String, File>> map = config.getSqlXmlFiles();
		for (Iterator<Entry<String, Map<String, File>>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, Map<String, File>> e = it.next();
			System.out.println("============================================");
			System.out.println("方案名:" + e.getKey());
			for (Iterator<Entry<String, File>> it1 = e.getValue().entrySet().iterator(); it1.hasNext();) {
				Entry<String, File> e1 = it1.next();
				System.out.println("类名:" + e1.getKey());
				System.out.println("对应文件:" + e1.getValue());
			}
		}
		Map<String, Parser> parsers = config.getParsers();
		for (Entry<String, Parser> ep : parsers.entrySet()) {
			Parser parser = ep.getValue();
			System.out.println("============================================");
			System.out.println("解析方案:" + parser.getName());
			Map<String, NodeParser> nps = parser.getNodeParsers();
			for (Entry<String, NodeParser> enp : nps.entrySet()) {
				System.out.println("标签解析器:" + enp.getKey());
			}
		}

		SqlCache cache = config.getSqlCache();
		Map<String, Map<String, String>> c = (Map<String, Map<String, String>>) cache.getAll();
		for (Iterator<String> it1 = c.keySet().iterator(); it1.hasNext();) {
			String key1 = it1.next();
			for (Iterator<String> it2 = c.get(key1).keySet().iterator(); it2.hasNext();) {
				String key2 = it2.next();
				System.out.println("方案:" + key1);
				System.out.println("key:" + key2);
				System.out.println("value:" + c.get(key1).get(key2));
			}
		}
	}

	public static void testFileName() {
		String fileName = "TestDaoSql.mysql.xml";
		int fileNameSuffix = fileName.indexOf("Sql.");
		fileName = fileName.substring(fileNameSuffix + 4);
		fileName = fileName.substring(0, fileName.lastIndexOf('.'));
		System.out.println(fileName);
	}

	public static void test() {
		try {
			File file = ResourceUtils.getFile("com/test/dao/TestDao.class");
			String fileName = file.getName();
			System.out.println(fileName.substring(fileName.lastIndexOf('.')));
			System.out.println(fileName.substring(fileName.lastIndexOf('.') - 3, fileName.lastIndexOf('.')));
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getPath());
			throw new FileNotFoundException();
		} catch (FileNotFoundException e) {
			log.debug("abc", e);
		}
	}
}
