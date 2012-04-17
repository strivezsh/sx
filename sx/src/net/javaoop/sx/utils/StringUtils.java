package net.javaoop.sx.utils;

import java.util.Map;

public class StringUtils {

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static String format(String format, Map map) {
		StringBuilder result = new StringBuilder();
		char[] cs = format.toCharArray();
		char[] temp = null;
		String key = null;
		int formatStart = 0;
		for (int keyStart = 0; keyStart < cs.length; keyStart++) {
			if (cs[keyStart] == '$' && cs[keyStart + 1] == '{') {
				for (int keyEnd = keyStart = keyStart + 2; keyEnd < cs.length; keyEnd++) {
					if (cs[keyEnd] == '}') {
						temp = new char[keyEnd - keyStart];
						System.arraycopy(cs, keyStart, temp, 0, temp.length);
						key = String.valueOf(temp);
						result.append(cs, formatStart, keyStart - formatStart - 2);

						if (map.containsKey(key)) {
							result.append(map.get(key));
						}

						keyStart = formatStart = keyEnd + 1;
						break;
					}
					// else if (cs[keyEnd] == '$' && cs[keyEnd + 1] == '{') {
					// temp = new char[keyEnd - keyStart + 4];
					// System.arraycopy(cs, keyStart - 2, temp, 0, temp.length);
					// key = String.valueOf(temp);
					// throw new RuntimeException(" " + key + "...... 占位符内不能包含
					// ${ 组合字符!!!");
					// }
				}
			}
		}
		if (formatStart < cs.length) {
			result.append(cs, formatStart, cs.length - formatStart);
		}
		return result.toString();
	}
}
