package com.epoint.ui;

import java.util.Map;
import java.util.Scanner;

import com.epoint.domin.Course;
import com.epoint.service.AdminService;
import com.epoint.service.CourseService;
import com.epoint.utils.CheckIntUtil;

/**
 * ����Ա��¼����ʾ�Ľ��棬�пγ̹���ѧ�������޸Ĺ���Ա���빦��
 * @author hp
 *
 */
public class AdminRightsUI {
	CourseManageUI courseManageUI = new CourseManageUI();
	StudentManageUI studentManageUI = new StudentManageUI();
	AdminService adminService = new AdminService();
	
	private String acount;
	public AdminRightsUI() {};
	public AdminRightsUI(String acount) {
		this.acount = acount;
	}
	
	/**
	 * ����Ա��½��ӵ��Ȩ�޲˵�
	 */
	public void adminRightsUI() {
		System.out.println(
				"-- ����ԱȨ�޲˵� --\r\n" + 
				"1���γ̹���     2��ѧ������   3���޸�����   	4��������һ��");
		int input = CheckIntUtil.check();
		switch (input) {
		case 1:
			courseManageUI.courseManageUI();
			break;
		case 2:
			studentManageUI.studentManageUI();
			break;
		case 3:
			chagePwd(acount);
			break;
		case 4:
			new SystemUI().systemUI();
			break;
		default:
			System.out.println("������������������");
			adminRightsUI();
			break;
		}
	}
	
	/**
	 * �޸�����
	 * @param acount
	 */
	public void chagePwd(String acount) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("����������");
		String oldPwd = scanner.next();
		boolean flag = adminService.isPwdRight(acount,oldPwd);
		if(flag) {
			System.out.println("����д����Ա���룺");
			String password = scanner.next();
			System.out.println("���ٴ��������Ա���룺");
			String password2 = scanner.next();
			while(!(password.equals(password2))) {
				System.out.println("�������벻һ��");
				System.out.println("����д����Ա���룺");
				password = scanner.next();
				System.err.println("���ٴ��������Ա����");
				password2 = scanner.next();
			}
		}else {
			System.out.println("�������");
			adminRightsUI();
		}
		
	}
}
 