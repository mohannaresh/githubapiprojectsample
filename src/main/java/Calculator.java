
public class Calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator c = new Calculator();
		SCalculator s = new SCalculator();
	
		System.out.println(c.sum(10, 20));
		System.out.println(c.multiply(10, 20));

		int[] a1 = { 1, 2, 3, 4, 5 };
		System.out.println(c.sum(a1));

	}

	public int sum(int x, int y) {
		return x + y;
	}

	public int sum(int a[]) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}

	public int multiply(int x, int y) {
		return x * y;
	}
}

