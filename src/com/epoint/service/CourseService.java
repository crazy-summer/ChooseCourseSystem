package com.epoint.service;

import java.util.HashMap;
import java.util.Map;

import com.epoint.dao.CourseDao;
import com.epoint.domin.Course;

/**
 * 课程service
 * @author hp
 *
 */
public class CourseService {
	private CourseDao courseDao = new CourseDao();
	/**
	 * 增加一条课程记录
	 * @param course
	 * @return
	 */
	public boolean addCourse(Course course) {
		Map<String, Course> map = courseDao.readCourse();
		map.put(course.getCourse_id(), course);
		return courseDao.writeCourse(map);
	}
	
	/**
	 * 根据课程id查询单条课程记录
	 * @param course_id
	 * @return
	 */
	public Course getCourseById(String course_id) {
		Map<String, Course> map = courseDao.readCourse();
		Course course = map.get(course_id);
		return course;
	}
	
	/**
	 * 根据课程id删除单条课程记录
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
	 * 修改一条课程记录
	 * @param course
	 * @return
	 */
	public boolean updateCourse(Course course) {
		Map<String, Course> map = courseDao.readCourse();
		map.put(course.getCourse_id(), course);
		return courseDao.writeCourse(map);
	}
	
	/**
	 * 获取所有课程记录
	 * @return
	 */
	public Map<String, Course> getAllCourse() {
		Map<String, Course> map = courseDao.readCourse();
		return map;
	}

}
