package fields_locals;

public class Test {
	
	static int i;
	
	private void MyMethod() {
		
		System.out.println(i);
		
	}
	
	public static void main(String[] args) {
		
		int j=i;//�ֲ�����������ʱҪ���г�ʼ���������ܽ��е���
		
		Test t = new Test();
		
		System.out.println(j);
		
		t.MyMethod();
		
	}
	
}
