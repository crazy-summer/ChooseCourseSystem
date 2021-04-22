package com.epoint.service;

import com.epoint.dao.ChooseCourseDao;
import com.epoint.dao.CourseDao;
import com.epoint.domin.ChooseCourse;
import com.epoint.domin.Course;
import com.epoint.domin.Student;
import com.sun.javafx.collections.ChangeHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 选课service
 * @author hp
 *
 */
public class ChooseCourseService {
	private ChooseCourseDao chooseCourseDao = new ChooseCourseDao();
	private CourseService courseService = new CourseService();
	private StudentService studentService = new StudentService();
	
	/**
	 * 先判断有无该课程，再判断该课程学生是否选过,有该课程且没有选过就添加一条choosecourse记录
	 * @param sid
	 * @param course_id
	 * @return
	 */
	public boolean chooseCourse(String sid, String course_id) {
		Course course = courseService.getCourseById(course_id);
		if(course==null) {
			System.out.println("没有该课程");
			return false;
		}else {
			if(hasBeenChooseCourse(sid, course_id)) {
				System.out.println("已经选了该课程");
				return false;
			}else {
				return addChooseCourse(sid, course_id);
			}
		}
	}
	
	/**
	 * 获取该学生的选过课程的cuorse_id集合
	 * @param sid
	 * @return
	 */
	public List<String> getHasChooseCourseIdList(String sid){
		List<ChooseCourse> list = chooseCourseDao.readChooseCourse(); 
		List<String> list2 = new ArrayList<>(); 
		for(ChooseCourse chooseCourse : list) {
			if(sid.equals(chooseCourse.getSid())) {
				list2.add(chooseCourse.getCourse_id());
			}
		}
		return list2;
	}
	
	/**
	 * 判断该学生是否选过课程
	 * @param sid
	 * @param course_id
	 * @return
	 */
	public boolean hasBeenChooseCourse(String sid,String course_id) {
		List<String> list = getHasChooseCourseIdList(sid);
		for(String course_ids : list) {
			if(course_ids.equals(course_id)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 新增一条choosecourse选课信息
	 * @param sid
	 * @param course_id
	 * @return
	 */
	public boolean addChooseCourse(String sid,String course_id) {
		ChooseCourse chooseCourse = new ChooseCourse(sid, course_id);
		List<ChooseCourse> list = chooseCourseDao.readChooseCourse();
		list.add(chooseCourse);
		return chooseCourseDao.writeChooseCourse(list);
	}
	
	/**
	 * 遍历已选课程的id的list集合，所有课程的map去除已选课程id，返回可选课程map
	 * @param sid
	 * @return
	 */
	public Map<String, Course> getCouldChooseCourse(String sid) {
		List<String> hasChooseCourseList =  getHasChooseCourseIdList(sid);
		Map<String, Course> map =  courseService.getAllCourse();
		for(String course_id : hasChooseCourseList) {
			map.remove(course_id);
		}
		return map;
	}
	
	/**
	 * 该学生已经选了的课程对象
	 * @param sid
	 * @return
	 */
	public List<Course> hasChooseCourseList(String sid){
		List<String> list1 = getHasChooseCourseIdList(sid);
		List<Course> list2 = new ArrayList<>(); 
		Map<String, Course> map = courseService.getAllCourse();
		for(String course_id : list1) {
			Course course = map.get(course_id);
			list2.add(course);
		}
		return list2;
	}
	
	/**
	 * 取消选课，先判断有没有该课程，在判断是否已经选了该课程
	 * @param sid
	 * @param course_id
	 * @return
	 */
	public boolean cancelChooseCourse(String sid, String course_id) {
		Course course = courseService.getCourseById(course_id);
		if(course==null) {
			System.out.println("没有该课程");
			return false;
		}else {
			if(hasBeenChooseCourse(sid, course_id)) {
				return deleteChooseCourse(sid, course_id);
			}else {
				return false;
			}
		}
	}
	/**
	 * 取消选课后-->删除choosecourse
	 * @param sid
	 * @param course_id
	 * @return
	 */
	public boolean deleteChooseCourse(String sid,String course_id) {
		List<ChooseCourse> list = chooseCourseDao.readChooseCourse();
		Iterator<ChooseCourse> iterator = list.iterator();
		while(iterator.hasNext()) {
			ChooseCourse chooseCourse = iterator.next();
			String s_id = chooseCourse.getSid();
			String c_id = chooseCourse.getCourse_id();
			if(s_id.equals(sid) && c_id.equals(c_id) ) {
				iterator.remove();
			}
		}
		return chooseCourseDao.writeChooseCourse(list);
	}
	
	/**
	 * 修改选课，先删除，再添加（先判断有没有）
	 * @param sid
	 * @param course_id
	 * @return
	 */
	public boolean changeChooseCourse(String sid,String course_id) {
		boolean flag = cancelChooseCourse(sid, course_id);
		if(flag) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入要修改成哪一门课程id");
			String new_course_id = scanner.next();
			Course course = courseService.getCourseById(course_id);
			if(course!=null) {
				return addChooseCourse(sid, new_course_id);
			}else {
				System.out.println("没有该课程");
				return false;
			}
		}else {
			return false;
		}
	}
	
	/**
	 * 根据sid查询出单条该学生记录，修改密码，重新添加记录。
	 * @param sid
	 * @param newPassword
	 * @return
	 */
	public boolean changePassword(String sid, String newPassword) {
		Student student = studentService.getStudentById(sid);
		student.setPassword(newPassword);
		return studentService.updateStudent(student);
		
	}
	
	/**
	 * 级联删除，管理员删除学生时，该学生所选的课程也都删除
	 * @param sid
	 */
	public void deleteAllChooseCourseByStudent(String sid) {
		List<ChooseCourse> list = chooseCourseDao.readChooseCourse();
		Iterator<ChooseCourse> iterator =  list.iterator();
		while(iterator.hasNext()) {
			 ChooseCourse chooseCourse = iterator.next();
			 if(chooseCourse.getSid().equals(sid)) {
				 iterator.remove();
			 }
		}
	}
	
	/**
	 * 级联删除，管理员删除课程时，该学生所选的课程也都删除
	 * @param course_id
	 */
	public void deleteAllChooseCourseByCourse(String course_id) {
		List<ChooseCourse> list = chooseCourseDao.readChooseCourse();
		Iterator<ChooseCourse> iterator =  list.iterator();
		while(iterator.hasNext()) {
			 ChooseCourse chooseCourse = iterator.next();
			 if(chooseCourse.getCourse_id().equals(course_id)) {
				 iterator.remove();
			 }
		}
	}
	
	/**
	 * 查看备选信息，查看选了某一课程的所有学生信息，先检查该课程是否存在，查询关联的学生id，然后查询存放学生的map
	 * @param course_id
	 * @return
	 */
	public Map<String, Student> getAllStudentHasChooseThisCourse(String course_id) {
		
		Course course = courseService.getCourseById(course_id);
		//检查课程是否存在
		if(course!=null) {
			//课程存在
			List<ChooseCourse> list = chooseCourseDao.readChooseCourse();
			Map<String, Student> map = studentService.getAllStudent();
			Map<String, Student> map2 = new HashMap<String, Student>();
			
			//遍历chooseCourseList，移除非查看课程的记录
			Iterator<ChooseCourse> iterator = list.iterator();
			while(iterator.hasNext()) {
				ChooseCourse chooseCourse = iterator.next();
				if(!(chooseCourse.getCourse_id().equals(course_id))){
					iterator.remove();
				}
			}
			
			//根据筛选后的chooseCourseList的学生id，将查询map中的符合该学生的键值对放到新map中
			for(ChooseCourse chooseCourse : list) {
				String sid = chooseCourse.getSid();
				map2.put(sid, map.get(sid));
			}
			return map2;
		}else {
			return null;
		}
	}
}
