package com.epoint.domin;

import java.io.Serializable;

/**
 * Ñ¡¿ÎÊµÌå
 * @author hp
 *
 */
public class ChooseCourse implements Serializable{
	private String sid;
	private String course_id;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public ChooseCourse(String sid, String course_id) {
		super();
		this.sid = sid;
		this.course_id = course_id;
	}
	@Override
	public String toString() {
		return "ChooseCourse [sid=" + sid + ", course_id=" + course_id + "]";
	}
	
	
}
