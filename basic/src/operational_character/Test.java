package operational_character;

public class Test {
	
	public static void main(String[] args) {
		test_0();
	}
	
	
	
	public static void test_0() {
		
		@SuppressWarnings("unused")
		short s = 3;
		
		
		//编译正确。因为“+=”运算符会根据所接收的变量类型进行类型的强制转换
		//“+=” “-+” “*=” “/=”除了实现相应功能外还会进行一次强制的类型转换
		s+=1;
		
		//下面这个表达式会报编译错误的原因是 s+1 为 int 型  ，而 s 为 short 型，违反了自动转换规则
		//s = s + 1;
		
	}
	
	
}
