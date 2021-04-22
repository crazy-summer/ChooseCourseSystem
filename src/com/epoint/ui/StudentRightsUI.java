package com.epoint.ui;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.epoint.domin.Course;
import com.epoint.service.ChooseCourseService;
import com.epoint.utils.CheckIntUtil;
import com.epoint.utils.StringUtil;

import sun.reflect.generics.tree.VoidDescriptor;

/**
 * ѧ����½������ӵ�еĹ���Ȩ��ҳ��
 * @author hp
 *
 */
public class StudentRightsUI {
	ChooseCourseService chooseCourseService = new ChooseCourseService();
	
	/**
	 * ��ѧ����ɫ��½��չʾ��ҳ��
	 * @param sid
	 */
	public void studentRightsUI(String sid) {
		System.out.println("--ѧ�������б�--");
		System.out.println("1��ѡ�� 2���鿴��ѡ 3���鿴��ѡ 4��ɾ��ѡ�� 5���޸�ѡ�� 6���޸����� 7��������һ���˵�");
		int input = CheckIntUtil.check();
		switch (input) {
		case 1:
			studentRightsUIOne(sid);
			break;
		case 2:
			studentRightsUITwo(sid);
			break;
		case 3:
			studentRightsUIThree(sid);
			break;
		case 4:
			studentRightsUIFour(sid);
			break;
		case 5:
			studentRightsUIFive(sid);
			break;
		case 6:
			studentRightsUISix(sid);
			break;
		case 7:
			new SystemUI().systemUI();
			break;
		default:
			System.out.println("������������������");
			studentRightsUI(sid);
			break;
		}
	}
	
	/**
	 * 1��ѡ��
	 * @param sid
	 */
	public void studentRightsUIOne(String sid) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������γ�id");
		String course_id = scanner.next();
		boolean flag = chooseCourseService.chooseCourse(sid, course_id);
		if(flag) {
			System.out.println("ѡ�γɹ�");
			studentRightsUI(sid);
		}else {
			System.out.println("ѡ��ʧ��");
			studentRightsUI(sid);
		}
	}
	
	/**
	 * 2���鿴��ѡ
	 * @param sid
	 */
	public void studentRightsUITwo(String sid) {
		System.out.println("���Ŀ�ѡ�γ��б�");
		Map<String, Course> map = chooseCourseService.getCouldChooseCourse(sid);
		for(Course course : map.values()) {
			System.out.println(course);
		}
		studentRightsUI(sid);
	}
	
	/**
	 * 3���鿴��ѡ
	 * @param sid
	 */
	public void studentRightsUIThree(String sid) {
		System.out.println("������ѡ�γ��б�");
		List<Course> list = chooseCourseService.hasChooseCourseList(sid);
		for(Course course : list) {
			System.out.println(course);
		}
		studentRightsUI(sid);
	}
	
	/**
	 * 4��ɾ��ѡ��
	 * @param sid
	 */
	public void studentRightsUIFour(String sid) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������γ�id");
		String course_id = scanner.next();
		boolean flag = chooseCourseService.cancelChooseCourse(sid, course_id);
		if(flag) {
			System.out.println("ɾ��ѡ�γɹ�");
			studentRightsUI(sid);
		}else {
			System.out.println("ɾ��ѡ��ʧ��");
			studentRightsUI(sid);
		}
	}
	
	/**
	 * 5���޸�ѡ��
	 * @param sid
	 */
	public void studentRightsUIFive(String sid) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������Ҫ�޸ĵĿγ�id");
		String course_id = scanner.next();
		boolean flag = chooseCourseService.changeChooseCourse(sid, course_id);
		if(flag) {
			System.out.println("�޸�ѡ�γɹ�");
			studentRightsUI(sid);
		}else {
			System.out.println("�޸�ѡ��ʧ��");
			studentRightsUI(sid);
		}
	}
	
	/**
	 * 6���޸�����(����ui���������г�����֤��Ȼ�󴫵�service�㣬����sid��ѯѧ����¼���޸����룩
	 * @param sid
	 */
	public void studentRightsUISix(String sid) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������������");
		String newPassword = scanner.next();
		while(StringUtil.isBlank(newPassword)) {
			System.out.println("���벻��Ϊ��");
			newPassword = scanner.next();
		}
		boolean flag = chooseCourseService.changePassword(sid,newPassword);
		if(flag) {
			System.out.println("�޸�����ɹ�");
			studentRightsUI(sid);
		}else {
			System.out.println("�޸�����ʧ��");
			studentRightsUI(sid);
		}
	}
}
