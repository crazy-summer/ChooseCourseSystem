package com.epoint.utils;

public class StringUtil {
	/**
	 * �ж��ַ����Ƿ�Ϊnull����Ϊ��
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
