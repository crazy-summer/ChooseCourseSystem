package com.epoint.ui;

import java.util.Scanner;

import com.epoint.domin.Admin;
import com.epoint.service.AdminService;
import com.epoint.utils.CheckIntUtil;
import com.epoint.utils.StringUtil;
/**
 * 管理员登陆界面，可以登陆，注册管理员账号
 * @author hp
 *
 */
public class AdminLoginUI {
	
	AdminService adminService = new AdminService();
	/**
	 * 管理员功能列表界面
	 */
	public  void adminLoginUI() {
		System.out.println("你选择了管理员：");
		System.out.println("-- 管理员功能列表 --");
		System.out.println("1、管理员注册   2、管理员登录    3、返回上一级菜单");
		int input = CheckIntUtil.check();
		switch (input) {
		case 1:
			adminRegister();
			break;
		case 2:
			adminLogin();
			break;
		default:
			new SystemUI().systemUI();
			break;
		}
	}
	/**
	 * 管理员功能列表页面-->1、管理员注册
	 */
	public void adminRegister() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("请填写管理员账号：");
		String acount = scanner.next();
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
		Admin admin = new Admin(acount, password);
		boolean flag = adminService.adminRegister(admin);
		if(flag==true) {
			System.out.println("管理员注册成功");
			adminLoginUI();
		}else {
			System.out.println("管理员注册失败");
			adminLoginUI();
		}
		adminLoginUI();
	}
	/**
	 * 管理员功能列表页面-->2、管理员登录
	 */
	public void adminLogin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("-- 管理员登录菜单 --");
		String acount = null;
		String password = null;
		while(true) {
			System.out.println("请输入管理员账号：");
			acount = scanner.next();
			if(!StringUtil.isBlank(acount)) {
				break;
			}else {
				System.out.println("输入不能为空");
			}
		}
		while(true) {
			System.out.println("请输入管理员密码：");
			password = scanner.next();
			if(!StringUtil.isBlank(password)) {
				break;
			}else {
				System.out.println("输入不能为空");
			}
		}
		//业务层根据acount查询admin实体对象，判断admin实体对象的密码和输入密码是否一致
		Admin admin = adminService.getAdminByAcount(acount);
		if(admin!=null && password.equals(admin.getPassword())) {
			System.out.println("登录成功，欢迎你："+acount);
			//跳转到管理员权限菜单
			AdminRightsUI adminRightsUI = new AdminRightsUI(acount);
			adminRightsUI.adminRightsUI();
		}else {
			System.out.println("账户或密码错误");
			adminLoginUI();
		}
		
	}
	
	/**
	 * 返回上一级页面
	 */
	public void returnToLastUI() {
		new SystemUI().systemUI();
	}
}
