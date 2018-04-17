package how_to_use_instanceof;

public class Main {
	public static void main(String[] args) {
		Animal d = new Dog();
		show(d);
		Animal c = new Cat();
		show(c);
		
	}

	private static void show(Animal a) {
		if(a instanceof Dog){
			Dog dog = (Dog)a;
			dog.f1();
		}else if(a instanceof Cat){
			Cat cat = (Cat)a;
			cat.f2();
		}
	}
	
}
