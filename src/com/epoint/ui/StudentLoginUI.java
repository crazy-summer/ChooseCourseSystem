package com.epoint.ui;

import java.util.Scanner;

import com.epoint.domin.Student;
import com.epoint.service.StudentService;
import com.epoint.utils.CheckIntUtil;

/**
 * 学则学生角色后的页面，学生还需要登陆
 * @author hp
 *
 */
public class StudentLoginUI {
	private StudentService studentService = new StudentService();
	
	/**
	 * 学生功能菜单
	 */
	public void studentLoginUI() {
		System.out.println("你选择了学生");
		System.out.println("请选择学生功能");
		System.out.println("1、学生登录  	2、返回上一级菜单");
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
	 * 学生登录
	 */
	public void studentLogin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入学生id");
		String sid = scanner.next();
		System.out.println("请输入登陆密码");
		String password = scanner.next();
		Student student = studentService.getStudentById(sid);
		if(student==null) {
			System.out.println("学生id错误");
			studentLogin();
		}else {
			if(student.getPassword().equals(password)) {
				new StudentRightsUI().studentRightsUI(sid);
			}else {
				System.out.println("密码错误");
				studentLogin();
			}
		}
	}
}
