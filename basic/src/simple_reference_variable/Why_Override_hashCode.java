package simple_reference_variable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author Euron
 * 
 * 通过具体的操作说明为何需要在自定义的实体类中重写 equals 和 hashCode 方法
 */
public class Why_Override_hashCode {

	
	public static void main(String[] args) throws Exception {
		testHashMap();
	}
	
	
	
	
	
	
	/* 
	 * 如果 Fruits 类中没有重写 hashCode 与 equals 方法，则打印的结果是：
	 * 
	 * [Fruits [num=7, name=apple], Fruits [num=6, name=apple], Fruits [num=7, name=apple], Fruits [num=6, name=apple]]
	 * 
	 * 在Fruits 类重写了hashCode ，但未重写equals方法时; 或者 重写了equals, 但未重写hashCode方法时，结果均如上。
	 * 
	 * 只有当Fruits 类将hashCode 与 equals方法都进行合理的重写时，结果才会显示为：
	 * [Fruits [num=7, name=apple], Fruits [num=6, name=apple]]
	 * 
	 * 
	 * 
	 * 
	 * 众所周知，Set中不能存放重复的元素，而Hashset是如何判断两个元素是不是重复呢？
	 * 		1.为了提高效率，首先判断hashCode方法的返回值是否相同，当hashCode的返回值不同时，则认为这两个元素不同。当hashCode相同时，再进行第2步。
	 * 		2.用equals方法比较两个元素，如果返回值为true，则认为相同，如果为false,则认为不同。
	 * 
	 * 事实上Hashset是根据该对象的特征使用特定的算法将其定义到一个地址上，那么在后面定义进来的数据只要看对应的hashcode地址上是否有值，如果有则
	 * 用equals比较， 如果没有则直接插入，这就减少了equals的使用次数，执行效率就大大提高了。
	 * 
	 * 
	 */
	public static void testHashset() throws Exception {
		
		Set<Fruits> set = new HashSet<>();
		
		Fruits apple = new Fruits(7,"apple");
		Fruits anotherApple = new Fruits(7, "apple");
		
		set.add(new Fruits(6,"apple"));
		set.add(new Fruits(6,"apple"));
		
		set.add(apple);
		set.add(anotherApple);
		
		System.out.println(set);
		
	}
	
	
	
	/*
	 * 
	 * 当使用Fruits类作为HashMap的key时，判断key是否重复的原则与HashSet判断元素是否重复时原理一样
	 * 所以再使用HashMap时，重写hashCode也是必要的。
	 */
	public static void testHashMap() throws Exception {
		
		Map<Fruits,Fruits> map = new HashMap<>();
		
		map.put(new Fruits(3, "keyFruit"), new Fruits(3, "apple"));
		map.put(new Fruits(3, "keyFruit"), new Fruits(3, "apple"));
		
		System.out.println("map的长度是："+map.size());
		
		Set<Fruits> keySet = map.keySet();
		
		for (Fruits theKey : keySet) {
			
			System.out.print(theKey +"—————————>");
			
			System.out.println(map.get(theKey));
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
