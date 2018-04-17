package double_float_BigDecimal;

public class Test {
	public static void _main() {
		Double d= new Double(2.2);
		System.out.println(0.05+0.01);
		System.out.println(ArithUtil.add(0.05, 0.01));
		System.out.println();
		
		System.out.println(1.0-0.42);
		System.out.println(ArithUtil.sub(1.0, 0.42));
		System.out.println();
		
		System.out.println(4.015*100);
		System.out.println(ArithUtil.mul(4.015, 100));
		System.out.println();
		
		System.out.println(123.3/100);
		System.out.println(ArithUtil.div(123.3, 100,5));
	}
	
	public static void main(String[] args) {
		//_main();
		System.out.println(0.09*5);
	}
}
