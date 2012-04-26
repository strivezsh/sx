package net.javaoop.sx.scanner;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.javaoop.sx.parser.Parser;

public interface Scanner {
	Map<String, Map<String, File>> scan(List<String> basePackages, Map<String, Parser> parsers);
}
