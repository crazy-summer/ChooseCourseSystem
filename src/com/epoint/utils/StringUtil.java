package com.epoint.utils;

public class StringUtil {
	/**
	 * 判断字符串是否为null或者为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if(str==null || "".equals(str) || "".equals(str.trim()) ) {
			return true;
		}
		return false;
	}
}
