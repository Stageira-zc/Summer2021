package scanner;

import java.util.Scanner;

public class demo5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int i = 0;
		double sum = 0.0d;
		while (scanner.hasNextDouble()) {
			double x = scanner.nextDouble();

			i = i + 1;

			sum = sum + x;
		}

		scanner.close();
		System.out.println("numbers: " + i);
		System.out.println("total is " + sum);
		double mean = sum / i;
		System.out.println("mean is " + mean);
	}
}
