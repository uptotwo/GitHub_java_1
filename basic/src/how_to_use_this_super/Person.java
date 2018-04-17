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
 * 使用this调用其他构造方法时，必须放在构造方法的开头处
 */
	public Person(String _name, int _age) {
		this(_name);
		age = _age;
	}
	
	
}
