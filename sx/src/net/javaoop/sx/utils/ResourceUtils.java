package net.javaoop.sx.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class ResourceUtils {
	public static File getFile(String location) throws FileNotFoundException {
		URL url = getDefaultClassLoader().getResource(location);
		if (null == url) {
			throw new FileNotFoundException();
		}
		return new File(url.getFile());
	}

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Throwable ex) {
		}
		if (cl == null) {
			cl = ResourceUtils.class.getClassLoader();
		}
		return cl;
	}
}
