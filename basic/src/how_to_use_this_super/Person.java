package how_to_use_this_super;

public class Person {
	@SuppressWarnings("unused")
	private String name;
	@SuppressWarnings("unused")
	private int age;
	@SuppressWarnings("unused")
	private String sex;
	
	public Person(){
		sex = "male";
	}

	public Person(String _name) {
		this();
		name = _name;
	}
/*
 * ʹ��this�����������췽��ʱ��������ڹ��췽���Ŀ�ͷ��
 */
	public Person(String _name, int _age) {
		this(_name);
		age = _age;
	}
	
	
}
