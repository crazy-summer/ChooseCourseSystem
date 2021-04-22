package com.epoint.service;

import java.util.Map;

import com.epoint.dao.AdminDao;
import com.epoint.domin.Admin;
import com.epoint.utils.StringUtil;
/**
 * 管理员service
 * @author hp
 *
 */
public class AdminService {
	AdminDao adminDao = new AdminDao();
	
	 /**
	  * 注册管理员账号，管理员实体放入hashmap,并且调用dao序列化
	  * @param admin
	  * @return
	  */
	public boolean adminRegister(Admin admin) {
		boolean flag = false;
		Map<String, Admin> map = adminDao.readAdmin();
		//判断该账号是否已经存在
		String acount = admin.getAcount();
		if(map.get(acount)==null) {
			map.put(acount, admin);
			flag = adminDao.writeAdmin(map);
		}
		return flag;
	}

	 /**
	  * 根据账户名查询管理员admin实体对象
	  * @param acount
	  * @return
	  */
	public Admin getAdminByAcount(String acount) {
		Map<String, Admin> map = adminDao.readAdmin();
		Admin adimin = map.get(acount);
		return adimin;
	}
	
	/**
	 * 管理员修改密码之前判断输入的密码是否正确
	 * @param acount
	 * @param oldPwd
	 * @return
	 */
	public boolean isPwdRight(String acount, String oldPwd) {
		Map<String, Admin> map = adminDao.readAdmin();
		if(map.get(acount).getPassword()==oldPwd) {
			return true;
		}else {
			return false;
		}
	}
}
