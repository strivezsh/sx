package test;

import java.io.File;
import java.io.FileNotFoundException;

import net.javaoop.sx.Sx;
import net.javaoop.sx.SxBuilder;
import net.javaoop.sx.utils.ResourceUtils;

import org.apache.log4j.Logger;

public class Test {
	private static final Logger log = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		SxBuilder builder = new SxBuilder();
		Sx sx = builder.build("SX.xml");
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
