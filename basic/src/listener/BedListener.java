package listener;
/*
 * 监听器的设计原则是不能干扰原事件的进行
 * 
 * 
 * 监听器在参数中收到了某个事件，而这个事件往往是只读的
 *  
 */
interface BedListener {
	  // 监听器的方法名通常以"on"开头
	  void onBedSound (String sound);
	  
}
