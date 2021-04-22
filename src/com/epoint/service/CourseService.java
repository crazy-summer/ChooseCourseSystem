package com.epoint.service;

import java.util.HashMap;
import java.util.Map;

import com.epoint.dao.CourseDao;
import com.epoint.domin.Course;

/**
 * �γ�service
 * @author hp
 *
 */
public class CourseService {
	private CourseDao courseDao = new CourseDao();
	/**
	 * ����һ���γ̼�¼
	 * @param course
	 * @return
	 */
	public boolean addCourse(Course course) {
		Map<String, Course> map = courseDao.readCourse();
		map.put(course.getCourse_id(), course);
		return courseDao.writeCourse(map);
	}
	
	/**
	 * ���ݿγ�id��ѯ�����γ̼�¼
	 * @param course_id
	 * @return
	 */
	public Course getCourseById(String course_id) {
		Map<String, Course> map = courseDao.readCourse();
		Course course = map.get(course_id);
		return course;
	}
	
	/**
	 * ���ݿγ�idɾ�������γ̼�¼
	 * @param course_id
	 * @return
	 */
	public boolean deleteCourseById(String course_id) {
		Map<String, Course> map = courseDao.readCourse();
		Course course = map.remove(course_id);
		if(course!=null) {
			return courseDao.writeCourse(map);
		}else {
			return false;
		}
	}
	
	/**
	 * �޸�һ���γ̼�¼
	 * @param course
	 * @return
	 */
	public boolean updateCourse(Course course) {
		Map<String, Course> map = courseDao.readCourse();
		map.put(course.getCourse_id(), course);
		return courseDao.writeCourse(map);
	}
	
	/**
	 * ��ȡ���пγ̼�¼
	 * @return
	 */
	public Map<String, Course> getAllCourse() {
		Map<String, Course> map = courseDao.readCourse();
		return map;
	}

}
