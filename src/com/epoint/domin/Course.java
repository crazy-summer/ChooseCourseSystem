package com.epoint.domin;

import java.io.Serializable;

/**
 * �γ�ʵ��
 * @author hp
 *
 */
public class Course implements Serializable{
	//�γ���
	private String course_name;
	//�γ�id
	private String course_id;
	//�ڿ���ʦ
	private String teacher;
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public Course(String course_name, String course_id, String teacher) {
		super();
		this.course_name = course_name;
		this.course_id = course_id;
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return "Course [course_name=" + course_name + ", course_id=" + course_id + ", teacher=" + teacher + "]";
	}
	
	
}
