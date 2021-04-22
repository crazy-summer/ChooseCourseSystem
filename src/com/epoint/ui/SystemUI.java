package com.epoint.ui;

import java.util.Scanner;

import com.epoint.domin.Admin;
import com.epoint.utils.CheckIntUtil;
/**
 * ϵͳ������ui��ѡ���ɫ
 * @author hp
 *
 */
public class SystemUI {
	AdminLoginUI adminLoginUI = new AdminLoginUI();
	StudentLoginUI studentLoginUI = new StudentLoginUI();
	
	public static void main(String[] args) {
		new SystemUI().systemUI();
	}
	
	/**
	 * ϵͳ�����棬����ɫѡ��
	 */
	public void systemUI() {
		System.out.println("--��ӭ������ϵͳ--");
		System.out.println("��ѡ�������û���ɫ");
		System.out.println("1.����Ա   2.ѧ��   3.�˳�ϵͳ");
		Scanner scanner = new Scanner(System.in);
		int input = CheckIntUtil.check();
		switch (input) {
		case 1:
			adminLoginUI.adminLoginUI();
			break;
		case 2:
			studentLoginUI.studentLogin();
			break;
		case 3:
			System.out.println("goodbye");
			System.exit(0);
			break;
		default:
			System.out.println("������������������");
			systemUI();
			break;
		}
	}
}
