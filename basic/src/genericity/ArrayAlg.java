package genericity;

public class ArrayAlg {
	/*
	 * ��ͨ���еķ��ͷ���
	 */
	//T... ��ʾ����Ĳ��������� ����������T���ͣ��������Ž�T���͵�Array��a��
	@SafeVarargs
	public static <T> T getMiddle(T...a) {
		return a[a.length/2];
	}

	public static void main(String[] args) {
		Integer middle = getMiddle(1,2,6,55);
		System.out.println(middle);
	}
	
}
