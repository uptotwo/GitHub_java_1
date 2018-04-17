package listener;

class Neighbor {
	
	BedListener listener;


	
	/*
	 * 这里是所谓的控制反转   其实就是在这个类中设置一个所引入的接口的set方法，
	 * 并在本类的方法中调用该接口中的方法，
	 * 在调用该类时，就可以动态的将接口的实现类写进去，
	 * 从而在调用该类中的方法时，反向控制方法所实现的具体内容
	 */
	public void setListener(BedListener listener) {
		this.listener = listener;
	}
	
	
	// 根据当地法律法规，部分内容无法显示
	void doInterestingStuff () {
	    
	    // 将事件发送给监听器
	    listener.onBedSound("Bedsound_1");
	  }
	
}
