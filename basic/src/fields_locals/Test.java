package fields_locals;

public class Test {
	
	static int i;
	
	private void MyMethod() {
		
		System.out.println(i);
		
	}
	
	public static void main(String[] args) {
		
		int j=i;//局部变量在声明时要进行初始化，否则不能进行调用
		
		Test t = new Test();
		
		System.out.println(j);
		
		t.MyMethod();
		
	}
	
}
