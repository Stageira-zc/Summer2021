package scanner;

import java.util.Scanner;

public class switch_case {

	public static void main(String[] args) {
		int option = 0;
		// 建立扫描器
		Scanner scanner = new Scanner(System.in);
		// 用户输入
		System.out.println("请选择：");
		System.out.println("1:rock");
		System.out.println("2:whisp");
		System.out.println("3:paper");

		option = scanner.nextInt();
		switch (option) {
		case 1:
			break;
		}

	}

}
