package com.epoint.utils;

import java.util.Scanner;

public class CheckIntUtil {
	
	/**
	 * ���ܴӼ�������һ����������������Ҫ�������룬���ظ�����ֵ
	 * @return
	 */
	public static int check() {
		Scanner scanner = new Scanner(System.in);
		int input = 0;
		while(true) {
			try {
				input = scanner.nextInt();
				break;
			}catch (Exception e) {
				System.out.println("���������������������");
				scanner = new Scanner(System.in);
			}
		}
		return input;
	}
}
