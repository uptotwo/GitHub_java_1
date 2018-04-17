package listener;

class Main {
	public static void main(String[] args) {
		Neighbor n = new Neighbor();
		BedListener bl = new SomeBedListener(); 
		n.setListener(bl);
		n.doInterestingStuff();
	}
}
