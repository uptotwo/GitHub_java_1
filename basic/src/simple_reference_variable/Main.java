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
		 * ��ΪInteger���͵ı���ֱ���� �������� = ���֡�����ʽ���и�ֵ�������Ҫ����ֵa�����������a>-129&a<128��ʱ��
		 * �ñ�����ֵĬ�ϻ�ֱ�Ӵ洢��ջ�У����������Integer����Ϊ�����ͱ�����������Ϊ���ʱ��û�жԵ�ַ�����ã��� ��==�����бȽ�ʱ�Ƚϵ������˵�ֵ�����ǵ�ַ����
		 * ��a��ֵ����������Χ��ʱ������ֱ���� �������� = ���֡�����ʽ�����ǡ������� = new Integer(����)������ʽ�Ա������и�ֵ����ֵ����洢�ڶ��У�����ջ�����øô洢��ַ����ʱInteger
		 * ������ʵ�����ϵ��������͡�����ĳ�����������ٸ�ֵ�󣨼��㸳����ֵһ�����������ᡰ==��ԭ��������Ϊ���ٸ�ֵ��ʱ��java��new��һ���µĶ���
		 * 
		 * ��String���͵ı����á� ������ = �ַ���������ʽ���и�ֵʱ��java������������Ƿ��и��ַ���������У�ֱ�����õ�ַ�����û�У���ȥnew��һ��String����
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
