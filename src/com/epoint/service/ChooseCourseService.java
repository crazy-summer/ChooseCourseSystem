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
 * ѡ��service
 * @author hp
 *
 */
public class ChooseCourseService {
	private ChooseCourseDao chooseCourseDao = new ChooseCourseDao();
	private CourseService courseService = new CourseService();
	private StudentService studentService = new StudentService();
	
	/**
	 * ���ж����޸ÿγ̣����жϸÿγ�ѧ���Ƿ�ѡ��,�иÿγ���û��ѡ�������һ��choosecourse��¼
	 * @param sid
	 * @param course_id
	 * @return
	 */
	public boolean chooseCourse(String sid, String course_id) {
		Course course = courseService.getCourseById(course_id);
		if(course==null) {
			System.out.println("û�иÿγ�");
			return false;
		}else {
			if(hasBeenChooseCourse(sid, course_id)) {
				System.out.println("�Ѿ�ѡ�˸ÿγ�");
				return false;
			}else {
				return addChooseCourse(sid, course_id);
			}
		}
	}
	
	/**
	 * ��ȡ��ѧ����ѡ���γ̵�cuorse_id����
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
	 * �жϸ�ѧ���Ƿ�ѡ���γ�
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
	 * ����һ��choosecourseѡ����Ϣ
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
	 * ������ѡ�γ̵�id��list���ϣ����пγ̵�mapȥ����ѡ�γ�id�����ؿ�ѡ�γ�map
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
	 * ��ѧ���Ѿ�ѡ�˵Ŀγ̶���
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
	 * ȡ��ѡ�Σ����ж���û�иÿγ̣����ж��Ƿ��Ѿ�ѡ�˸ÿγ�
	 * @param sid
	 * @param course_id
	 * @return
	 */
	public boolean cancelChooseCourse(String sid, String course_id) {
		Course course = courseService.getCourseById(course_id);
		if(course==null) {
			System.out.println("û�иÿγ�");
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
	 * ȡ��ѡ�κ�-->ɾ��choosecourse
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
	 * �޸�ѡ�Σ���ɾ��������ӣ����ж���û�У�
	 * @param sid
	 * @param course_id
	 * @return
	 */
	public boolean changeChooseCourse(String sid,String course_id) {
		boolean flag = cancelChooseCourse(sid, course_id);
		if(flag) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("������Ҫ�޸ĳ���һ�ſγ�id");
			String new_course_id = scanner.next();
			Course course = courseService.getCourseById(course_id);
			if(course!=null) {
				return addChooseCourse(sid, new_course_id);
			}else {
				System.out.println("û�иÿγ�");
				return false;
			}
		}else {
			return false;
		}
	}
	
	/**
	 * ����sid��ѯ��������ѧ����¼���޸����룬������Ӽ�¼��
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
	 * ����ɾ��������Աɾ��ѧ��ʱ����ѧ����ѡ�Ŀγ�Ҳ��ɾ��
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
	 * ����ɾ��������Աɾ���γ�ʱ����ѧ����ѡ�Ŀγ�Ҳ��ɾ��
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
	 * �鿴��ѡ��Ϣ���鿴ѡ��ĳһ�γ̵�����ѧ����Ϣ���ȼ��ÿγ��Ƿ���ڣ���ѯ������ѧ��id��Ȼ���ѯ���ѧ����map
	 * @param course_id
	 * @return
	 */
	public Map<String, Student> getAllStudentHasChooseThisCourse(String course_id) {
		
		Course course = courseService.getCourseById(course_id);
		//���γ��Ƿ����
		if(course!=null) {
			//�γ̴���
			List<ChooseCourse> list = chooseCourseDao.readChooseCourse();
			Map<String, Student> map = studentService.getAllStudent();
			Map<String, Student> map2 = new HashMap<String, Student>();
			
			//����chooseCourseList���Ƴ��ǲ鿴�γ̵ļ�¼
			Iterator<ChooseCourse> iterator = list.iterator();
			while(iterator.hasNext()) {
				ChooseCourse chooseCourse = iterator.next();
				if(!(chooseCourse.getCourse_id().equals(course_id))){
					iterator.remove();
				}
			}
			
			//����ɸѡ���chooseCourseList��ѧ��id������ѯmap�еķ��ϸ�ѧ���ļ�ֵ�Էŵ���map��
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
