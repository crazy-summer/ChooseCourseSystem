package com.epoint.ui;

import java.util.Map;

import java.util.Scanner;

import com.epoint.domin.Student;
import com.epoint.domin.Course;
import com.epoint.service.ChooseCourseService;
import com.epoint.service.CourseService;
import com.epoint.utils.CheckIntUtil;

/**
 * ����Աѡ��γ̹����չʾ��ҳ��
 * ѡ����Ӧ���ܣ�����ת����Ӧ����
 * @author hp
 *
 */
public class CourseManageUI {
	private CourseService courseService = new CourseService();
	private ChooseCourseService chooseCourseService = new ChooseCourseService();
	
	/**
	 * ����ԱȨ��1���γ̹���
	 */
	public void courseManageUI() {
		System.out.println(
				"-- �γ̹��� --\r\n" + 
				"��ѡ��γ̹����ܣ�\r\n" + 
				"1�������γ�   2���鿴�γ�   3���γ�ɾ��   4���γ��޸�   5���鿴���пγ�   6���鿴��ѡ��Ϣ	    7��������һ��");
		int input = CheckIntUtil.check();
		switch (input) {
		case 1:
			courseManageUIOne();
			break;
		case 2:
			courseManageUITwo();
			break;
		case 3:
			courseManageUIThree();
			break;
		case 4:
			courseManageUIFour();
			break;
		case 5:
			courseManageUIFive();
			break;
		case 6:
			courseManageUISix();
			break;
		case 7:
			courseManageUISeven();
			break;
		default:
			break;
		}
		
	}
	

	/**
	 * �ڹ���ԱȨ��->�γ̹�����ѡ��1�������γ̺��ҳ��
	 */
	public void courseManageUIOne() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("������γ�id");
		String course_id = scanner.next();
		System.out.println("������γ�����");
		String course_name = scanner.next();
		System.out.println("�������ڿ���ʦ");
		String teacher = scanner.next();
		
		Course course = new Course(course_name, course_id, teacher);
		boolean flag = courseService.addCourse(course);
		if(flag) {
			System.out.println("�����γ̳ɹ�");
			courseManageUI();
		}else {
			System.out.println("�����γ�ʧ��");
			//���ع���ԱȨ��ui
			courseManageUI();
		}
	}
	
	/**
	 * �ڹ���ԱȨ��->�γ̹���-->2���鿴�γ� ���ҳ�棨���ݿγ�id��ѯ������¼��
	 */
	private void courseManageUITwo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������Ҫ��ѯ�Ŀγ�id");
		String course_id = scanner.next();
		Course course = courseService.getCourseById(course_id);
		if(course!=null) {
			System.out.println("����ѯ�Ŀγ���Ϣ����");
			System.out.println(course.toString());
			courseManageUI();
		}else {
			System.out.println("û�е�ǰ�γ̣�����������");
			courseManageUITwo();
		}
	}
	
	/**
	 * ����ԱȨ��-->�γ̹���-->3��ɾ���γ̣�����ɾ����ɾ���γ�ʱ��ɾ��choousecourse�����а�����ѡ���˸ÿγ̵�ѧ���ļ�¼��
	 */
	public void courseManageUIThree() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������Ҫɾ���Ŀγ�id");
		String course_id = scanner.next();
		boolean flag = courseService.deleteCourseById(course_id);
		if(flag) {
			System.out.println("ɾ���γ̳ɹ�");
			chooseCourseService.deleteAllChooseCourseByCourse(course_id);
			courseManageUI();
		}else {
			System.err.println("�γ�ɾ��ʧ��");
			courseManageUI();
		}
	}
	
	/**
	 * ����ԱȨ��-->�γ̹���-->4���޸Ŀγ�
	 */
	public void courseManageUIFour() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������Ҫ�޸ĵĿγ�id");
		String course_id = scanner.next();
		Course course = courseService.getCourseById(course_id);
		if(course==null) {
			System.out.println("�����ڸÿγ�");
			courseManageUI();
		}else {
			System.out.println("������Ҫ�޸ĵĿγ�����");
			String course_name = scanner.next();
			System.out.println("������Ҫ�޸ĵ��ڿ���ʦ");
			String teacher = scanner.next();
			Course course2 = new Course(course_name, course_id, teacher);
			boolean flag = courseService.updateCourse(course2);
			if(flag) {
				System.out.println("�޸Ŀγ̳ɹ�");
			}else {
				System.out.println("�޸Ŀγ�ʧ��");
				courseManageUI();
			}
		}
		
	}
	
	/**
	 * ����ԱȨ��-->�γ̹���-->5���鿴���пγ�
	 */
	public void courseManageUIFive() {
		Map<String, Course> map = courseService.getAllCourse();
		if(map.isEmpty()) {
			System.out.println("��ǰû�пγ�");
			courseManageUI();
		}else {
			for(Course course : map.values()) {
				System.out.println(course);
			}
			courseManageUI();
		}
	}
	
	/**
	 * ����ԱȨ��-->�γ̹���-->6���鿴��ѡ��Ϣ
	 */
	public void courseManageUISix() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������Ҫ�鿴��ѡ��Ϣ�Ŀγ�id");
		String course_id = scanner.next();
		Map<String, Student> map = chooseCourseService.getAllStudentHasChooseThisCourse(course_id);
		if(map!=null) {
			for(Student student : map.values()) {
				System.out.println(student);
			}
			courseManageUI();
		}else {
			System.out.println("û�иÿγ�");
			courseManageUI();
		}
	}
	
	/**
	 * ����ԱȨ��-->�γ̹���-->7��������һ��
	 */
	public void courseManageUISeven() {
		new AdminRightsUI().adminRightsUI();
	}
}
