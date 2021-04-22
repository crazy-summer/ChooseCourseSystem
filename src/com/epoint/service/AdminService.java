package com.epoint.service;

import java.util.Map;

import com.epoint.dao.AdminDao;
import com.epoint.domin.Admin;
import com.epoint.utils.StringUtil;
/**
 * ����Աservice
 * @author hp
 *
 */
public class AdminService {
	AdminDao adminDao = new AdminDao();
	
	 /**
	  * ע�����Ա�˺ţ�����Աʵ�����hashmap,���ҵ���dao���л�
	  * @param admin
	  * @return
	  */
	public boolean adminRegister(Admin admin) {
		boolean flag = false;
		Map<String, Admin> map = adminDao.readAdmin();
		//�жϸ��˺��Ƿ��Ѿ�����
		String acount = admin.getAcount();
		if(map.get(acount)==null) {
			map.put(acount, admin);
			flag = adminDao.writeAdmin(map);
		}
		return flag;
	}

	 /**
	  * �����˻�����ѯ����Աadminʵ�����
	  * @param acount
	  * @return
	  */
	public Admin getAdminByAcount(String acount) {
		Map<String, Admin> map = adminDao.readAdmin();
		Admin adimin = map.get(acount);
		return adimin;
	}
	
	/**
	 * ����Ա�޸�����֮ǰ�ж�����������Ƿ���ȷ
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
