package scanner;

import java.util.Scanner;

public class demo2 {
	public static void main(String[] args) {

		// 扫描器
		Scanner scanner = new Scanner(System.in);

		System.out.println("判断用户是否输出");

		//

		if (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			System.out.println("输出的内容为：" + str);
		}

		// close
		scanner.close();
	}
}
//oeaoe',.;;j;	;QJKXBMWNS-/=RLCRLCLGRGR