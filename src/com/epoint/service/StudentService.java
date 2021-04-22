package com.epoint.service;

import java.util.Map;

import com.epoint.dao.StudentDao;
import com.epoint.domin.Student;

/**
 * ѧ��service
 * @author hp
 *
 */
public class StudentService {
	private StudentDao studentDao = new StudentDao();
	
	/**
	 * ����һ��ѧ��,mapû�и�ѧ����ӣ��������ʧ�ܣ�����false
	 * @param student
	 * @return
	 */
	public boolean addStudent(Student student) {
		Map<String, Student> map = studentDao.readStudent();
		if(map.get(student.getSid())==null) {
			map.put(student.getSid(), student);
			return studentDao.writeStudent(map);
		}else {
			return false;
		}
	}

	/**
	 * ����ѧ��id��ȡһ��ѧ����¼
	 * @param sid
	 * @return
	 */
	public Student getStudentById(String sid) {
		Map<String, Student> map = studentDao.readStudent();
		Student student = map.get(sid);	
		return student;
	}

	/**
	 * ����ѧ��idɾ��һ��ѧ����¼
	 * @param sid
	 * @return
	 */
	public boolean deleteStudentById(String sid) {
		Map<String, Student> map = studentDao.readStudent();
		Student student = map.remove(sid);
		if(student!=null) {
			return studentDao.writeStudent(map);
		}else {
			return false;
		}
	}
	
	/**
	 * �޸�һ��ѧ����¼
	 * @param student2
	 * @return
	 */
	public boolean updateStudent(Student student2) {
		Map<String, Student> map = studentDao.readStudent();
		map.put(student2.getSid(), student2);
		return studentDao.writeStudent(map);
	}
	
	/**
	 * ��ȡ����ѧ����Ϣ
	 * @return
	 */
	public Map<String, Student> getAllStudent() {
		Map<String, Student> map = studentDao.readStudent();
		
		return map;
	}

}
