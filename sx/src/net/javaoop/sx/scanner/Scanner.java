package net.javaoop.sx.scanner;

import net.javaoop.sx.SxConfig;

public abstract class Scanner {
	protected SxConfig sxConfig;

	public void setSxConfig(SxConfig sxConfig) {
		this.sxConfig = sxConfig;
	}

	public abstract void scan();
}
