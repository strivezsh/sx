package test;

import net.javaoop.sx.Sx;
import net.javaoop.sx.SxBuilder;

public class Test {

	public static void main(String[] args) {
		SxBuilder builder = new SxBuilder();
		Sx sx = builder.build("SX.xml");
		// System.out.println(sx.getSql("ceshi"));
	
	}
}
