package com.epoint.ui;

import java.util.Map;

import java.util.Scanner;

import com.epoint.domin.Student;
import com.epoint.domin.Course;
import com.epoint.service.ChooseCourseService;
import com.epoint.service.CourseService;
import com.epoint.utils.CheckIntUtil;

/**
 * 管理员选择课程管理后展示的页面
 * 选择相应功能，会跳转到相应方法
 * @author hp
 *
 */
public class CourseManageUI {
	private CourseService courseService = new CourseService();
	private ChooseCourseService chooseCourseService = new ChooseCourseService();
	
	/**
	 * 管理员权限1、课程管理
	 */
	public void courseManageUI() {
		System.out.println(
				"-- 课程管理 --\r\n" + 
				"请选择课程管理功能：\r\n" + 
				"1、新增课程   2、查看课程   3、课程删除   4、课程修改   5、查看所有课程   6、查看备选信息	    7、返回上一级");
		int input = CheckIntUtil.check();
		switch (input) {
		case 1:
			courseManageUIOne();
			break;
		case 2:
			courseManageUITwo();
			break;
		case 3:
			courseManageUIThree();
			break;
		case 4:
			courseManageUIFour();
			break;
		case 5:
			courseManageUIFive();
			break;
		case 6:
			courseManageUISix();
			break;
		case 7:
			courseManageUISeven();
			break;
		default:
			break;
		}
		
	}
	

	/**
	 * 在管理员权限->课程管理中选择1、新增课程后的页面
	 */
	public void courseManageUIOne() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("请输入课程id");
		String course_id = scanner.next();
		System.out.println("请输入课程名称");
		String course_name = scanner.next();
		System.out.println("请输入授课老师");
		String teacher = scanner.next();
		
		Course course = new Course(course_name, course_id, teacher);
		boolean flag = courseService.addCourse(course);
		if(flag) {
			System.out.println("新增课程成功");
			courseManageUI();
		}else {
			System.out.println("新增课程失败");
			//返回管理员权限ui
			courseManageUI();
		}
	}
	
	/**
	 * 在管理员权限->课程管理-->2、查看课程 后的页面（根据课程id查询单条记录）
	 */
	private void courseManageUITwo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要查询的课程id");
		String course_id = scanner.next();
		Course course = courseService.getCourseById(course_id);
		if(course!=null) {
			System.out.println("您查询的课程信息如下");
			System.out.println(course.toString());
			courseManageUI();
		}else {
			System.out.println("没有当前课程，请重新输入");
			courseManageUITwo();
		}
	}
	
	/**
	 * 管理员权限-->课程管理-->3、删除课程（级联删除：删除课程时，删除choousecourse中所有包含了选择了该课程的学生的记录）
	 */
	public void courseManageUIThree() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要删除的课程id");
		String course_id = scanner.next();
		boolean flag = courseService.deleteCourseById(course_id);
		if(flag) {
			System.out.println("删除课程成功");
			chooseCourseService.deleteAllChooseCourseByCourse(course_id);
			courseManageUI();
		}else {
			System.err.println("课程删除失败");
			courseManageUI();
		}
	}
	
	/**
	 * 管理员权限-->课程管理-->4、修改课程
	 */
	public void courseManageUIFour() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要修改的课程id");
		String course_id = scanner.next();
		Course course = courseService.getCourseById(course_id);
		if(course==null) {
			System.out.println("不存在该课程");
			courseManageUI();
		}else {
			System.out.println("请输入要修改的课程名称");
			String course_name = scanner.next();
			System.out.println("请输入要修改的授课老师");
			String teacher = scanner.next();
			Course course2 = new Course(course_name, course_id, teacher);
			boolean flag = courseService.updateCourse(course2);
			if(flag) {
				System.out.println("修改课程成功");
			}else {
				System.out.println("修改课程失败");
				courseManageUI();
			}
		}
		
	}
	
	/**
	 * 管理员权限-->课程管理-->5、查看所有课程
	 */
	public void courseManageUIFive() {
		Map<String, Course> map = courseService.getAllCourse();
		if(map.isEmpty()) {
			System.out.println("当前没有课程");
			courseManageUI();
		}else {
			for(Course course : map.values()) {
				System.out.println(course);
			}
			courseManageUI();
		}
	}
	
	/**
	 * 管理员权限-->课程管理-->6、查看备选信息
	 */
	public void courseManageUISix() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要查看备选信息的课程id");
		String course_id = scanner.next();
		Map<String, Student> map = chooseCourseService.getAllStudentHasChooseThisCourse(course_id);
		if(map!=null) {
			for(Student student : map.values()) {
				System.out.println(student);
			}
			courseManageUI();
		}else {
			System.out.println("没有该课程");
			courseManageUI();
		}
	}
	
	/**
	 * 管理员权限-->课程管理-->7、返回上一级
	 */
	public void courseManageUISeven() {
		new AdminRightsUI().adminRightsUI();
	}
}
