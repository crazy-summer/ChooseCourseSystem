package com.epoint.utils;

import java.io.File;
import java.io.IOException;

public class ExistFileUtil {
	
	/**
	 * 参数是一个文件路径字符串，该文件路径非空的条件下，创建该文件对象，文件不存在则创建文件，返回该文件对象
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
