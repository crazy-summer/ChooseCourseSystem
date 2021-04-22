package com.epoint.ui;

import java.util.Map;
import java.util.Scanner;

import com.epoint.domin.Student;
import com.epoint.service.ChooseCourseService;
import com.epoint.service.StudentService;
import com.epoint.utils.CheckIntUtil;

/**
 * 管理员选择学生管理后的页面
 * 选择现因功能执行相应方法
 * @author hp
 *
 */
public class StudentManageUI {
	private StudentService studentService = new StudentService();
	private ChooseCourseService chooseCourseService = new ChooseCourseService();
	
	/**
	 * 管理员拥有的学生管理功能的菜单
	 */
	public void studentManageUI() {
		System.out.println(
				"请选择学生管理功能：\r\n" + 
				"1、新增学生   2、查看学生   3、学生删除   4、学生修改   5、展示所有学生   6、 返回上一级");
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
	 * 新增学生
	 */
	public void studentManageUIOne() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入学生学号");
		String sid = scanner.next();
		System.out.println("请输入学生姓名");
		String sname = scanner.next();
		System.out.println("请输入学生密码");
		String password = scanner.next();
		System.out.println("请输入学生性别");
		String sex = scanner.next();
		System.out.println("请输入学生年龄");
		Integer age = scanner.nextInt();
		Student student = new Student(sid, sname, password, sex, age);
		boolean flag = studentService.addStudent(student);
		if(flag) {
			System.out.println("增加学生成功");
			studentManageUI();
		}else {
			System.out.println("增加学生失败");
			studentManageUI();
		}
		
	}
	
	/**
	 * 根据id查询一条学生记录
	 */
	public void studentManageUITwo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要查询的学生id");
		String sid = scanner.next();
		Student student = studentService.getStudentById(sid);
		if(student!=null) {
			System.out.println(student);
			studentManageUI();
		}else {
			System.out.println("不存在该学生");
			studentManageUI();
		}
	}
	
	/**
	 * 管理员权限-->学生管理-->3、学生删除（级联删除：删除学生成功时，将该学生对应的学课记录删除（删除choosecourse里的记录））
	 */
	public void studentManageUIThree() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要删除的学生id");
		String sid = scanner.next();
		boolean flag = studentService.deleteStudentById(sid);
		if(flag) {
			System.out.println("删除学生成功");
			chooseCourseService.deleteAllChooseCourseByStudent(sid);
			studentManageUI();
		}else {
			System.out.println("删除学生失败");
			studentManageUI();
		}
	}
	
	/**
	 * 管理员权限-->学生管理-->4、学生修改
	 */
	public void studentManageUIFour() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要修改的学生id");
		String sid = scanner.next();
		Student student = studentService.getStudentById(sid);
		if(student==null) {
			System.out.println("不存在该学生");
			studentManageUI();
		}else {
			System.out.println("请输入要修改的学生姓名");
			String sname = scanner.next();
			System.out.println("请输入要修改的学生密码");
			String password = scanner.next();
			System.out.println("请输入要修改的学生性别");
			String sex = scanner.next();
			System.out.println("请输入要修改的学生年龄");
			Integer age = scanner.nextInt();
			Student student2 = new Student(sid, sname, password, sex, age);
			boolean flag = studentService.updateStudent(student2);
			if(flag) {
				System.out.println("修改学生信息成功");
				studentManageUI();
			}else {
				System.out.println("修改学生信息失败");
				studentManageUI();
			}
		}
	}
	
	/**
	 * 管理员权限-->学生管理-->5、展示所有学生
	 */
	public void studentManageUIFive() {
		System.out.println("-- 展示所有学生 --");
		Map<String , Student> map = studentService.getAllStudent();
		if(map.isEmpty()) {
			System.out.println("当前没有学生记录");
			studentManageUI();
		}else {
			for(Student student : map.values()) {
				System.out.println(student);
			}
			studentManageUI();
		}
	}
}
