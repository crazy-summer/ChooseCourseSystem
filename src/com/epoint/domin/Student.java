package com.epoint.domin;

import java.io.Serializable;

/**
 * 学生实体
 * @author hp
 *
 */
public class Student implements Serializable{
	//学号
	private String sid;
	//姓名
	private String sname;
	//密码
	private String password;
	//性别
	private String sex;
	//年龄
	private Integer age;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Student(String sid, String sname, String password, String sex, Integer age) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.password = password;
		this.sex = sex;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", password=" + password + ", sex=" + sex + ", age=" + age
				+ "]";
	}
	


	
}
