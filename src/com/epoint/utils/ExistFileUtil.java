package com.epoint.utils;

import java.io.File;
import java.io.IOException;

public class ExistFileUtil {
	
	/**
	 * ������һ���ļ�·���ַ��������ļ�·���ǿյ������£��������ļ������ļ��������򴴽��ļ������ظ��ļ�����
	 * @param path
	 * @return
	 */
	public static File isExistFile(String path) {
		if(!StringUtil.isBlank(path)) {
			File file =  new File(path);
			if(!file.exists()) {
				file.getParentFile().mkdirs();
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return file;
		}else {
			return null;
		}
	}
}
