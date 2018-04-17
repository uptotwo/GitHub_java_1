package how_to_use_this_super;

public class Leaf {
	
	private int i = 0;
	private Leaf increament(){
		i++;
		return this;
	}
	
	private void print() {
		System.out.println("i = "+i);
	}	
	
	public static void main(String[] args) {
		Leaf x  = new Leaf();
		x.increament().increament().increament().print();
		Leaf y = new Leaf();
		y.increament().increament().print();
	}
}
