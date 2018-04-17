package threeObjFormula;

public class ThreeObjFormula {
	
	public static void main(String[] args) {
		zhangqianAsk();
	}
	
	public static void testFormula(){

		boolean flag = false;
		int i ;
		i = flag ? 1:2;
		System.out.println(i);
	}
	
	public static void zhangqianAsk(){
		
		boolean b = true?false:true == false?true:false;
		
		System.out.println(b);
	}
	
}
