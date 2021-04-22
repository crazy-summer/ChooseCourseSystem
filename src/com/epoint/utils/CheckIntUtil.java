package com.epoint.utils;

import java.util.Scanner;

public class CheckIntUtil {
	
	/**
	 * 接受从键盘输入一个整数，非整数需要重新输入，返回该整数值
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
				System.out.println("输入非整数，请重新输入");
				scanner = new Scanner(System.in);
			}
		}
		return input;
	}
}
