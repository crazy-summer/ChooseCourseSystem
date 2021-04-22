package com.epoint.ui;

import java.util.Map;
import java.util.Scanner;

import com.epoint.domin.Course;
import com.epoint.service.AdminService;
import com.epoint.service.CourseService;
import com.epoint.utils.CheckIntUtil;

/**
 * 管理员登录后显示的界面，有课程管理，学生管理，修改管理员密码功能
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
	 * 管理员登陆后拥有权限菜单
	 */
	public void adminRightsUI() {
		System.out.println(
				"-- 管理员权限菜单 --\r\n" + 
				"1、课程管理     2、学生管理   3、修改密码   	4、返回上一级");
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
			System.out.println("输入有误，请重新输入");
			adminRightsUI();
			break;
		}
	}
	
	/**
	 * 修改密码
	 * @param acount
	 */
	public void chagePwd(String acount) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入密码");
		String oldPwd = scanner.next();
		boolean flag = adminService.isPwdRight(acount,oldPwd);
		if(flag) {
			System.out.println("请填写管理员密码：");
			String password = scanner.next();
			System.out.println("请再次输入管理员密码：");
			String password2 = scanner.next();
			while(!(password.equals(password2))) {
				System.out.println("两次密码不一致");
				System.out.println("请填写管理员密码：");
				password = scanner.next();
				System.err.println("请再次输入管理员密码");
				password2 = scanner.next();
			}
		}else {
			System.out.println("密码错误");
			adminRightsUI();
		}
		
	}
}
 