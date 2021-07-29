package test;

public class demo9 {
	public static void main(String[] args) {
		add i = new add(3, 5);
		System.out.println(i.a);
	}
}

class add {
	public int a;// 可通过实例.属性(方法访问)
	private int b;

	public add(int a, int b) {
		this.a = a;
		this.b = b;
		// b;//加this 指向 类中b 不加指向局部变量b
	}

	public int getNum_1() {
		return this.a;
	}

	public int getNum_2() {
		return this.b;
	}

}
