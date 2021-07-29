package test;

import java.util.Date;

//import java.util.Date;

public class demo8 {
	public static void main(String[] args) {
		int i = 8;
		String score = i < 60 ? "true" : "false";
		System.out.println(score);
		Date date = new Date();
		System.out.println(date);

		// 给定毫秒数，创建Date
		long ctm = System.currentTimeMillis();
		Date date1 = new Date(ctm);
		System.out.println(date1);
	}
}
