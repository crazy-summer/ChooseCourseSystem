package com.epoint.ui;

import java.util.Scanner;

import com.epoint.domin.Admin;
import com.epoint.utils.CheckIntUtil;
/**
 * 系统主界面ui，选择角色
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
	 * 系统主界面，供角色选择
	 */
	public void systemUI() {
		System.out.println("--欢迎来到本系统--");
		System.out.println("请选择您的用户角色");
		System.out.println("1.管理员   2.学生   3.退出系统");
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
			System.out.println("输入有误，请重新输入");
			systemUI();
			break;
		}
	}
}
