package scanner;

public class demo6 {
	public static void main(String[] args) {
		for (int i = 1; i < 10; i = i + 1) {
			System.out.println("i:" + i);
			for (int j = 1; j <= i; j = j + 1) {
				int x = i * j;
				System.out.print(i + "*" + j + "=" + x + "\t");
			}
			System.out.println();
		}
	}

}
