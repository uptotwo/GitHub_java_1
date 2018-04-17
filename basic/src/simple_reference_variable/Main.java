package simple_reference_variable;

public class Main {
	
	public static void _main(String[] args) {
		
		Integer inte_P = 128;
		String str_p = "str_p";
		MyInteger myI_p = new MyInteger(1);
		
		
		Integer inte_q = inte_P;
		String str_q = str_p;
		MyInteger myI_q = myI_p;
		
		
		System.out.println("inte_p == inte_q :"+(inte_P == inte_q));
		System.out.println("inte_p == inte_q :"+(inte_P.equals(inte_q) ));
		System.out.println("str_p == str_q:" + ( str_p == str_q ) );
		System.out.println("str_p.equals(str_q):" + ( str_p.equals(str_q) ) );
		System.out.println("myI_p == myI_q:" + ( myI_p == myI_q ) );
		System.out.println("myI_p.equals(myI_q):" + ( myI_p.equals(myI_q) ) );
		
		/*
		 * 在为Integer类型的变量直接以 “变量名 = 数字”的形式进行赋值，且这个要赋的值a满足该条件（a>-129&a<128）时，
		 * 该变量的值默认会直接存储在栈中（这种情况下Integer可视为简单类型变量———因为这个时候并没有对地址的引用，用 “==”进行比较时比较的是两端的值而不是地址）。
		 * 当a的值不在上述范围内时，无论直接用 “变量名 = 数字”的形式，还是“变量名 = new Integer(数字)”的形式对变量进行赋值，该值都会存储在堆中，并在栈中引用该存储地址，此时Integer
		 * 才是真实意义上的引用类型。当对某个变量进行再赋值后（即便赋的数值一样），它不会“==”原变量，因为对再赋值的时候，java会new出一个新的对象。
		 * 
		 * 而String类型的变量用“ 变量名 = 字符串”的形式进行赋值时，java都会检索堆中是否有该字符串，如果有，直接引用地址，如果没有，再去new出一个String对象。
		 */
		
		inte_q = new Integer(128);
		
		
		str_q = "str_p";
		myI_q.innerInt = 2;
		
		/*System.out.println("str_p :" + str_p);
		System.out.println("myI_p == myI_q:" + (myI_p == myI_q));*/
		System.out.println();
		
		System.out.println("inte_p == inte_q :"+(inte_P == inte_q));
		System.out.println("inte_p == inte_q :"+(inte_P.equals(inte_q) ));
		System.out.println("str_p == str_q:" + ( str_p == str_q ) );
		System.out.println("str_p.equals(str_q):" + ( str_p.equals(str_q) ) );
		System.out.println("myI_p == myI_q:" + ( myI_p == myI_q ) );
		System.out.println("myI_p.equals(myI_q):" + ( myI_p.equals(myI_q) ) );
	}
	public static void __main(String[] args) {
		String a = "123888988465456566565516156";
		String b = "123888988465456566565516156";
		
		System.out.println(a ==b);
	}
	
	
}
