package com.epoint.domin;

import java.io.Serializable;

/**
 * 管理员实体
 * @author hp
 *
 */
public class Admin implements Serializable{
	//账号
	private String acount;
	//密码
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
