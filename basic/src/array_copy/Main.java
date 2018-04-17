package array_copy;

import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
		test_1();
	}
	
	
	private  static  void test_1() {
		
		int a [] = {0,1,2,3,4,5,6,7,8,9};
		
		int b [] = {10,11,12,13,14,15,16,17,18,19};
		
		//System.arraycopy(a, 0, b, 2, 5);
		
		System.out.println(Arrays.toString(b));
		
		System.out.println(Arrays.toString(a));
		
		int[] x = new  int[20] ;
		
		System.arraycopy(b, 0, x, 5, 6);
		
		System.out.println(Arrays.toString(x));
		int[] y = Arrays.copyOf(a, 5);
		System.out.println(Arrays.toString(y));
		
	}
	

	
}
