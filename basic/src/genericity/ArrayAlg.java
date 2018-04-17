package genericity;

public class ArrayAlg {
	/*
	 * 普通类中的泛型方法
	 */
	//T... 表示传入的参数可以是 任意数量的T类型，都将被放进T类型的Array即a中
	@SafeVarargs
	public static <T> T getMiddle(T...a) {
		return a[a.length/2];
	}

	public static void main(String[] args) {
		Integer middle = getMiddle(1,2,6,55);
		System.out.println(middle);
	}
	
}
