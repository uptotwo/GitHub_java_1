package simple_reference_variable;


public class Test {
	public static void _main(String[] args) {
		/*
		 * ��1������==����������ڻ����������͵ı�������ֱ�ӱȽ���洢�� ��ֵ���Ƿ���ȣ�

			������������������������͵ı�������Ƚϵ�����ָ��Ķ���ĵ�ַ

			����2������equals������ע�⣺equals�������������ڻ����������͵ı���

			�����������û�ж�equals����������д����Ƚϵ����������͵ı�����ָ��Ķ���ĵ�ַ��

			������������String��Date�����equals������������д�Ļ����Ƚϵ�����ָ��Ķ�������ݡ�
		 */
		
		
		
		
		//������ֱ����ջ�ڴ��д��ֵ
		//�������͵�ֵ�洢�ڶ��ڴ��У����õ�ַ�����ջ�ڴ���
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
