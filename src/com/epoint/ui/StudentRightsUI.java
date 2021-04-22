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
 * 学生登陆后所有拥有的功能权限页面
 * @author hp
 *
 */
public class StudentRightsUI {
	ChooseCourseService chooseCourseService = new ChooseCourseService();
	
	/**
	 * 以学生角色登陆所展示的页面
	 * @param sid
	 */
	public void studentRightsUI(String sid) {
		System.out.println("--学生功能列表--");
		System.out.println("1、选课 2、查看可选 3、查看已选 4、删除选课 5、修改选课 6、修改密码 7、返回上一级菜单");
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
			System.out.println("输入有误，请重新输入");
			studentRightsUI(sid);
			break;
		}
	}
	
	/**
	 * 1、选课
	 * @param sid
	 */
	public void studentRightsUIOne(String sid) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入课程id");
		String course_id = scanner.next();
		boolean flag = chooseCourseService.chooseCourse(sid, course_id);
		if(flag) {
			System.out.println("选课成功");
			studentRightsUI(sid);
		}else {
			System.out.println("选课失败");
			studentRightsUI(sid);
		}
	}
	
	/**
	 * 2、查看可选
	 * @param sid
	 */
	public void studentRightsUITwo(String sid) {
		System.out.println("您的可选课程列表");
		Map<String, Course> map = chooseCourseService.getCouldChooseCourse(sid);
		for(Course course : map.values()) {
			System.out.println(course);
		}
		studentRightsUI(sid);
	}
	
	/**
	 * 3、查看已选
	 * @param sid
	 */
	public void studentRightsUIThree(String sid) {
		System.out.println("您的已选课程列表");
		List<Course> list = chooseCourseService.hasChooseCourseList(sid);
		for(Course course : list) {
			System.out.println(course);
		}
		studentRightsUI(sid);
	}
	
	/**
	 * 4、删除选课
	 * @param sid
	 */
	public void studentRightsUIFour(String sid) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入课程id");
		String course_id = scanner.next();
		boolean flag = chooseCourseService.cancelChooseCourse(sid, course_id);
		if(flag) {
			System.out.println("删除选课成功");
			studentRightsUI(sid);
		}else {
			System.out.println("删除选课失败");
			studentRightsUI(sid);
		}
	}
	
	/**
	 * 5、修改选课
	 * @param sid
	 */
	public void studentRightsUIFive(String sid) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要修改的课程id");
		String course_id = scanner.next();
		boolean flag = chooseCourseService.changeChooseCourse(sid, course_id);
		if(flag) {
			System.out.println("修改选课成功");
			studentRightsUI(sid);
		}else {
			System.out.println("修改选课失败");
			studentRightsUI(sid);
		}
	}
	
	/**
	 * 6、修改密码(先再ui层对密码进行初步验证，然后传到service层，根据sid查询学生记录，修改密码）
	 * @param sid
	 */
	public void studentRightsUISix(String sid) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入新密码");
		String newPassword = scanner.next();
		while(StringUtil.isBlank(newPassword)) {
			System.out.println("密码不能为空");
			newPassword = scanner.next();
		}
		boolean flag = chooseCourseService.changePassword(sid,newPassword);
		if(flag) {
			System.out.println("修改密码成功");
			studentRightsUI(sid);
		}else {
			System.out.println("修改密码失败");
			studentRightsUI(sid);
		}
	}
}
