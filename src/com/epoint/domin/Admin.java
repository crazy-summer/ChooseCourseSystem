package com.epoint.domin;

import java.io.Serializable;

/**
 * ����Աʵ��
 * @author hp
 *
 */
public class Admin implements Serializable{
	//�˺�
	private String acount;
	//����
	private String password;
	public String getAcount() {
		return acount;
	}
	public void setAcount(String acount) {
		this.acount = acount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Admin(String acount, String password) {
		super();
		this.acount = acount;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [acount=" + acount + ", password=" + password + "]";
	}
	
}
