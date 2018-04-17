package simple_reference_variable;

public class MyInteger {
	
	public int innerInt;
	
	private MyInteger(){}
	
	public MyInteger(int innerInt){
		this.innerInt = innerInt;
	}

	@Override
	public String toString() {
		return innerInt+"";
	}
	
	
}
