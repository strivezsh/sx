package net.javaoop.sx.scanner;

import net.javaoop.sx.SxConfig;

public abstract class Scanner {
	protected SxConfig sxConfig;

	public abstract void scan();

	public void setSxConfig(SxConfig sxConfig) {
		this.sxConfig = sxConfig;
	}

	public SxConfig getSxConfig() {
		return sxConfig;
	}
}
