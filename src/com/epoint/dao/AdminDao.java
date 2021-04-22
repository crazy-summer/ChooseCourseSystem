package com.epoint.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import com.epoint.domin.Admin;
import com.epoint.utils.ExistFileUtil;
import com.epoint.utils.FilePathUtil;


public class AdminDao {
	
	/**
	 * 从文件中反序列化出map，没有的话返回的是新建的一个map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Admin> readAdmin(){
		Map<String, Admin> map = new HashMap<>();
		File file = ExistFileUtil.isExistFile(FilePathUtil.ADMIN_PATH);
		if(file!=null && file.length()>0) {
			try(FileInputStream fileInputStream = new FileInputStream(file);
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
				map = (Map<String, Admin>)objectInputStream.readObject();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 将admin序列化到文件
	 * @param map
	 * @return
	 */
	public boolean writeAdmin(Map<String, Admin> map) {
		boolean flag = false;
		File file = ExistFileUtil.isExistFile(FilePathUtil.ADMIN_PATH);
		if(file!=null) {
			try(FileOutputStream fileOutputStream = new FileOutputStream(file);
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
				objectOutputStream.writeObject(map);
				flag = true;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
}
