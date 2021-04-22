package com.epoint.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.epoint.domin.Course;
import com.epoint.utils.ExistFileUtil;
import com.epoint.utils.FilePathUtil;

public class CourseDao {
	/**
	 * 从文件中反序列化出map<String,Course>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Course> readCourse(){
		Map<String, Course> map = new HashMap<>();
		File file = ExistFileUtil.isExistFile(FilePathUtil.COURSE_PATH);
		if(file!=null && file.length()>0) {
			try(FileInputStream fileInputStream = new FileInputStream(file);
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
				map = (Map<String, Course>)objectInputStream.readObject();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 向文件中写入存放课程的map，持久化
	 * @param map
	 * @return
	 */
	public boolean writeCourse(Map<String, Course> map) {
		boolean flag = false;
		File file = ExistFileUtil.isExistFile(FilePathUtil.COURSE_PATH);
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
