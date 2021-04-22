package com.epoint.ui;

import java.util.Scanner;

import com.epoint.domin.Student;
import com.epoint.service.StudentService;
import com.epoint.utils.CheckIntUtil;

/**
 * ѧ��ѧ����ɫ���ҳ�棬ѧ������Ҫ��½
 * @author hp
 *
 */
public class StudentLoginUI {
	private StudentService studentService = new StudentService();
	
	/**
	 * ѧ�����ܲ˵�
	 */
	public void studentLoginUI() {
		System.out.println("��ѡ����ѧ��");
		System.out.println("��ѡ��ѧ������");
		System.out.println("1��ѧ����¼  	2��������һ���˵�");
		int intput = CheckIntUtil.check();
		switch (intput) {
		case 1:
			studentLogin();
			break;
		case 2:
			new SystemUI().systemUI();
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * ѧ����¼
	 */
	public void studentLogin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������ѧ��id");
		String sid = scanner.next();
		System.out.println("�������½����");
		String password = scanner.next();
		Student student = studentService.getStudentById(sid);
		if(student==null) {
			System.out.println("ѧ��id����");
			studentLogin();
		}else {
			if(student.getPassword().equals(password)) {
				new StudentRightsUI().studentRightsUI(sid);
			}else {
				System.out.println("�������");
				studentLogin();
			}
		}
	}
}
