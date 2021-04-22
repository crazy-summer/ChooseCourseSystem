package com.epoint.ui;

import java.util.Scanner;

import com.epoint.domin.Admin;
import com.epoint.service.AdminService;
import com.epoint.utils.CheckIntUtil;
import com.epoint.utils.StringUtil;
/**
 * ����Ա��½���棬���Ե�½��ע�����Ա�˺�
 * @author hp
 *
 */
public class AdminLoginUI {
	
	AdminService adminService = new AdminService();
	/**
	 * ����Ա�����б����
	 */
	public  void adminLoginUI() {
		System.out.println("��ѡ���˹���Ա��");
		System.out.println("-- ����Ա�����б� --");
		System.out.println("1������Աע��   2������Ա��¼    3��������һ���˵�");
		int input = CheckIntUtil.check();
		switch (input) {
		case 1:
			adminRegister();
			break;
		case 2:
			adminLogin();
			break;
		default:
			new SystemUI().systemUI();
			break;
		}
	}
	/**
	 * ����Ա�����б�ҳ��-->1������Աע��
	 */
	public void adminRegister() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("����д����Ա�˺ţ�");
		String acount = scanner.next();
		System.out.println("����д����Ա���룺");
		String password = scanner.next();
		System.out.println("���ٴ��������Ա���룺");
		String password2 = scanner.next();
		while(!(password.equals(password2))) {
			System.out.println("�������벻һ��");
			System.out.println("����д����Ա���룺");
			password = scanner.next();
			System.err.println("���ٴ��������Ա����");
			password2 = scanner.next();
		}
		Admin admin = new Admin(acount, password);
		boolean flag = adminService.adminRegister(admin);
		if(flag==true) {
			System.out.println("����Աע��ɹ�");
			adminLoginUI();
		}else {
			System.out.println("����Աע��ʧ��");
			adminLoginUI();
		}
		adminLoginUI();
	}
	/**
	 * ����Ա�����б�ҳ��-->2������Ա��¼
	 */
	public void adminLogin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("-- ����Ա��¼�˵� --");
		String acount = null;
		String password = null;
		while(true) {
			System.out.println("���������Ա�˺ţ�");
			acount = scanner.next();
			if(!StringUtil.isBlank(acount)) {
				break;
			}else {
				System.out.println("���벻��Ϊ��");
			}
		}
		while(true) {
			System.out.println("���������Ա���룺");
			password = scanner.next();
			if(!StringUtil.isBlank(password)) {
				break;
			}else {
				System.out.println("���벻��Ϊ��");
			}
		}
		//ҵ������acount��ѯadminʵ������ж�adminʵ��������������������Ƿ�һ��
		Admin admin = adminService.getAdminByAcount(acount);
		if(admin!=null && password.equals(admin.getPassword())) {
			System.out.println("��¼�ɹ�����ӭ�㣺"+acount);
			//��ת������ԱȨ�޲˵�
			AdminRightsUI adminRightsUI = new AdminRightsUI(acount);
			adminRightsUI.adminRightsUI();
		}else {
			System.out.println("�˻����������");
			adminLoginUI();
		}
		
	}
	
	/**
	 * ������һ��ҳ��
	 */
	public void returnToLastUI() {
		new SystemUI().systemUI();
	}
}
