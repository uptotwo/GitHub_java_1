package manyThreads;

public class Mytest {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			Thread my = new Thread("my"+i);
			//rebound
			my.start();
			try {
				my.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}
}
