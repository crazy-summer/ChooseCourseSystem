package com.epoint.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.epoint.domin.ChooseCourse;
import com.epoint.utils.ExistFileUtil;
import com.epoint.utils.FilePathUtil;

public class ChooseCourseDao {
	/**
	 * 从文件中反序列化出map，没有的话返回的是新建的一个map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ChooseCourse> readChooseCourse(){
		List<ChooseCourse> list = new ArrayList<>();
		File file = ExistFileUtil.isExistFile(FilePathUtil.CHOOSECOURSE_PATH);
		if(file!=null && file.length()>0) {
			try(FileInputStream fileInputStream = new FileInputStream(file);
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
				list = ( List<ChooseCourse>)objectInputStream.readObject();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 将admin序列化到文件
	 * @param map
	 * @return
	 */
	public boolean writeChooseCourse( List<ChooseCourse> list) {
		boolean flag = false;
		File file = ExistFileUtil.isExistFile(FilePathUtil.CHOOSECOURSE_PATH);
		if(file!=null) {
			try(FileOutputStream fileOutputStream = new FileOutputStream(file);
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
				objectOutputStream.writeObject(list);
				flag = true;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
}
