package net.javaoop.sx;

import java.util.Map;

public class Sx {
	private SxConfig sxConfig;

	public String getSql(String id) {
		return sxConfig.getSqlCache().get(id);
	}

	public String getSql(String id, Map<String, Object> params) {
		return sxConfig.getSqlCache().get(id);
	}

	public SxConfig getSxConfig() {
		return sxConfig;
	}
}
