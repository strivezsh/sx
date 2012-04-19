package net.javaoop.sx;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 此类用于扫描SqlXml配置文件
 * 
 * @author zhou
 * 
 */
public class SqlXmlScanner {
	private static final Logger log = Logger.getLogger(SxBuilder.class);

	/**
	 * 用于扫描文件名后缀为Sql的文件
	 */
	private static final String FILE_NAME_SUFFIX = "Sql";

	/**
	 * 用于扫描文件后缀为xml的文件
	 */
	private static final String FILE_SUFFIX = ".xml";

	public void doScan(File directory, Map<String, File> sqlXmls) {
		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.exists()) {
				if (file.isFile()) {
					String fileName = file.getName();
					try {
						int lastIndex = fileName.lastIndexOf('.');
						String name = fileName.substring(lastIndex - 3, lastIndex);
						if(!FILE_NAME_SUFFIX.equalsIgnoreCase(name)){
							//如果是其他方式
						}
						String suffix = fileName.substring(lastIndex);
						if (FILE_SUFFIX.equalsIgnoreCase(suffix) && FILE_NAME_SUFFIX.equalsIgnoreCase(name)) {
							sqlXmls.put(file.getAbsolutePath(), file);
						}
					} catch (RuntimeException e) {
						log.debug("文件名:" + fileName + ",不符合规范(文件必须含有后缀,且文件名后三位字母为Sql,例如:TestDaoSql.xml)!!!", e);
					}
				} else if (file.isDirectory()) {
					doScan(file, sqlXmls);
				}
			}
		}
	}
}
