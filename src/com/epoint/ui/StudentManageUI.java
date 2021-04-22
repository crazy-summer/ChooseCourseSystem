package com.epoint.ui;

import java.util.Map;
import java.util.Scanner;

import com.epoint.domin.Student;
import com.epoint.service.ChooseCourseService;
import com.epoint.service.StudentService;
import com.epoint.utils.CheckIntUtil;

/**
 * ����Աѡ��ѧ��������ҳ��
 * ѡ��������ִ����Ӧ����
 * @author hp
 *
 */
public class StudentManageUI {
	private StudentService studentService = new StudentService();
	private ChooseCourseService chooseCourseService = new ChooseCourseService();
	
	/**
	 * ����Աӵ�е�ѧ�������ܵĲ˵�
	 */
	public void studentManageUI() {
		System.out.println(
				"��ѡ��ѧ�������ܣ�\r\n" + 
				"1������ѧ��   2���鿴ѧ��   3��ѧ��ɾ��   4��ѧ���޸�   5��չʾ����ѧ��   6�� ������һ��");
		int input = CheckIntUtil.check();
		switch (input) {
		case 1:
			studentManageUIOne();
			break;
		case 2:
			studentManageUITwo();
			break;
		case 3:
			studentManageUIThree();
			break;
		case 4:
			studentManageUIFour();
			break;
		case 5:
			studentManageUIFive();
			break;
		default:new AdminRightsUI().adminRightsUI();
			break;
		}
	}
	
	/**
	 * ����ѧ��
	 */
	public void studentManageUIOne() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������ѧ��ѧ��");
		String sid = scanner.next();
		System.out.println("������ѧ������");
		String sname = scanner.next();
		System.out.println("������ѧ������");
		String password = scanner.next();
		System.out.println("������ѧ���Ա�");
		String sex = scanner.next();
		System.out.println("������ѧ������");
		Integer age = scanner.nextInt();
		Student student = new Student(sid, sname, password, sex, age);
		boolean flag = studentService.addStudent(student);
		if(flag) {
			System.out.println("����ѧ���ɹ�");
			studentManageUI();
		}else {
			System.out.println("����ѧ��ʧ��");
			studentManageUI();
		}
		
	}
	
	/**
	 * ����id��ѯһ��ѧ����¼
	 */
	public void studentManageUITwo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������Ҫ��ѯ��ѧ��id");
		String sid = scanner.next();
		Student student = studentService.getStudentById(sid);
		if(student!=null) {
			System.out.println(student);
			studentManageUI();
		}else {
			System.out.println("�����ڸ�ѧ��");
			studentManageUI();
		}
	}
	
	/**
	 * ����ԱȨ��-->ѧ������-->3��ѧ��ɾ��������ɾ����ɾ��ѧ���ɹ�ʱ������ѧ����Ӧ��ѧ�μ�¼ɾ����ɾ��choosecourse��ļ�¼����
	 */
	public void studentManageUIThree() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������Ҫɾ����ѧ��id");
		String sid = scanner.next();
		boolean flag = studentService.deleteStudentById(sid);
		if(flag) {
			System.out.println("ɾ��ѧ���ɹ�");
			chooseCourseService.deleteAllChooseCourseByStudent(sid);
			studentManageUI();
		}else {
			System.out.println("ɾ��ѧ��ʧ��");
			studentManageUI();
		}
	}
	
	/**
	 * ����ԱȨ��-->ѧ������-->4��ѧ���޸�
	 */
	public void studentManageUIFour() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������Ҫ�޸ĵ�ѧ��id");
		String sid = scanner.next();
		Student student = studentService.getStudentById(sid);
		if(student==null) {
			System.out.println("�����ڸ�ѧ��");
			studentManageUI();
		}else {
			System.out.println("������Ҫ�޸ĵ�ѧ������");
			String sname = scanner.next();
			System.out.println("������Ҫ�޸ĵ�ѧ������");
			String password = scanner.next();
			System.out.println("������Ҫ�޸ĵ�ѧ���Ա�");
			String sex = scanner.next();
			System.out.println("������Ҫ�޸ĵ�ѧ������");
			Integer age = scanner.nextInt();
			Student student2 = new Student(sid, sname, password, sex, age);
			boolean flag = studentService.updateStudent(student2);
			if(flag) {
				System.out.println("�޸�ѧ����Ϣ�ɹ�");
				studentManageUI();
			}else {
				System.out.println("�޸�ѧ����Ϣʧ��");
				studentManageUI();
			}
		}
	}
	
	/**
	 * ����ԱȨ��-->ѧ������-->5��չʾ����ѧ��
	 */
	public void studentManageUIFive() {
		System.out.println("-- չʾ����ѧ�� --");
		Map<String , Student> map = studentService.getAllStudent();
		if(map.isEmpty()) {
			System.out.println("��ǰû��ѧ����¼");
			studentManageUI();
		}else {
			for(Student student : map.values()) {
				System.out.println(student);
			}
			studentManageUI();
		}
	}
}
