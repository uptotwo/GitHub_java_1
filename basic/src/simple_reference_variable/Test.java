package simple_reference_variable;


public class Test {
	public static void _main(String[] args) {
		/*
		 * 　1）对于==，如果作用于基本数据类型的变量，则直接比较其存储的 “值”是否相等；

			　　　　如果作用于引用类型的变量，则比较的是所指向的对象的地址

			　　2）对于equals方法，注意：equals方法不能作用于基本数据类型的变量

			　　　　如果没有对equals方法进行重写，则比较的是引用类型的变量所指向的对象的地址；

			　　　　诸如String、Date等类对equals方法进行了重写的话，比较的是所指向的对象的内容。
		 */
		
		
		
		
		//简单类型直接在栈内存中存放值
		//引用类型的值存储在堆内存中，引用地址存放在栈内存中
		String str = "123";
		Integer refenceI = 128;
		Fruits apple = new Fruits();
		apple.setNum(3);
		apple.setName("apple");
		
		String str1 = str;
		Integer refenceJ = refenceI;
		System.out.println("refenceJ = refenceI "+ (refenceJ.equals(refenceI) ));
		System.out.println("refenceJ = refenceI "+ (refenceJ == refenceI));
		Fruits anotherApple = apple;
		//refenceI = 165; 
		refenceJ = 128;
		
		System.out.println("refenceJ = refenceI "+ (refenceJ.equals(refenceI)));
		System.out.println("refenceJ = refenceI "+ (refenceJ == refenceI));
		
		anotherApple.setName("anotherApple");
		
		System.out.println("refenceI = "+refenceI);
		System.out.println(apple);
		
		str1="321";
		
		System.out.println(str);
		
	}
	
	public static void main(String[] args) {
		
		 MyInteger mi = new MyInteger(128);
		 
		 MyInteger mi1 = mi;
		 
		 System.out.println("mi == mi1 "+(mi == mi1));
		 System.out.println("mi.equals(mi1) "+(mi.equals(mi1)));
		 
		 mi1.innerInt = 129;
		 
		 System.out.println("mi == mi1 "+(mi == mi1));
		 System.out.println("mi.equals(mi1) "+(mi.equals(mi1)));
		 
		 System.out.println("mi :" + mi);
		 
	}
	
	
	
}
