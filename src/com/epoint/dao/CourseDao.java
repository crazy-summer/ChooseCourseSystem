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
	 * ���ļ��з����л���map<String,Course>
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
	 * ���ļ���д���ſγ̵�map���־û�
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
