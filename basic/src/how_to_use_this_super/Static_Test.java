package how_to_use_this_super;

public class Static_Test {
	private static int count = 0 ;
	
	public Static_Test(){
		count++;
	}
	
	public static void main(String[] args) {
		Static_Test t1 = new Static_Test();
		System.out.println(Static_Test.count);
		Static_Test t2 = new Static_Test();
		System.out.println(Static_Test.count);
	}
}
