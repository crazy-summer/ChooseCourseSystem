package com.epoint.service;

import java.util.Map;

import com.epoint.dao.StudentDao;
import com.epoint.domin.Student;

/**
 * 学生service
 * @author hp
 *
 */
public class StudentService {
	private StudentDao studentDao = new StudentDao();
	
	/**
	 * 增加一个学生,map没有该学生添加，有则添加失败，返回false
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
	 * 根据学生id获取一条学生记录
	 * @param sid
	 * @return
	 */
	public Student getStudentById(String sid) {
		Map<String, Student> map = studentDao.readStudent();
		Student student = map.get(sid);	
		return student;
	}

	/**
	 * 根据学生id删除一条学生记录
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
	 * 修改一条学生记录
	 * @param student2
	 * @return
	 */
	public boolean updateStudent(Student student2) {
		Map<String, Student> map = studentDao.readStudent();
		map.put(student2.getSid(), student2);
		return studentDao.writeStudent(map);
	}
	
	/**
	 * 获取所有学生信息
	 * @return
	 */
	public Map<String, Student> getAllStudent() {
		Map<String, Student> map = studentDao.readStudent();
		
		return map;
	}

}
