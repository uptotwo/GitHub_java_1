package operational_character;

public class Test {
	
	public static void main(String[] args) {
		test_0();
	}
	
	
	
	public static void test_0() {
		
		@SuppressWarnings("unused")
		short s = 3;
		
		
		//������ȷ����Ϊ��+=�����������������յı������ͽ������͵�ǿ��ת��
		//��+=�� ��-+�� ��*=�� ��/=������ʵ����Ӧ�����⻹�����һ��ǿ�Ƶ�����ת��
		s+=1;
		
		//����������ʽ�ᱨ��������ԭ���� s+1 Ϊ int ��  ���� s Ϊ short �ͣ�Υ�����Զ�ת������
		//s = s + 1;
		
	}
	
	
}
