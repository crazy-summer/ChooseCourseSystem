package com.epoint.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.epoint.domin.Student;
import com.epoint.utils.ExistFileUtil;
import com.epoint.utils.FilePathUtil;

public class StudentDao {
	/**
	 * 从文件中读取map，要用ois.readObject()反序列化
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Student> readStudent(){
		Map<String, Student> map = new HashMap<>();
		File file = ExistFileUtil.isExistFile(FilePathUtil.STUDENT_PATH);
		if(file!=null && file.length()>0) {
			try(FileInputStream fileInputStream = new FileInputStream(file);
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
				map = (Map<String, Student>)objectInputStream.readObject();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 向文件中存入map对象
	 * @param map
	 * @return
	 */
	public boolean writeStudent(Map<String, Student> map) {
		boolean flag = false;
		File file = ExistFileUtil.isExistFile(FilePathUtil.STUDENT_PATH);
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
