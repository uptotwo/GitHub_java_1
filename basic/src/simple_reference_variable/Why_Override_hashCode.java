package simple_reference_variable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author Euron
 * 
 * ͨ������Ĳ���˵��Ϊ����Ҫ���Զ����ʵ��������д equals �� hashCode ����
 */
public class Why_Override_hashCode {

	
	public static void main(String[] args) throws Exception {
		testHashMap();
	}
	
	
	
	
	
	
	/* 
	 * ��� Fruits ����û����д hashCode �� equals ���������ӡ�Ľ���ǣ�
	 * 
	 * [Fruits [num=7, name=apple], Fruits [num=6, name=apple], Fruits [num=7, name=apple], Fruits [num=6, name=apple]]
	 * 
	 * ��Fruits ����д��hashCode ����δ��дequals����ʱ; ���� ��д��equals, ��δ��дhashCode����ʱ����������ϡ�
	 * 
	 * ֻ�е�Fruits �ཫhashCode �� equals���������к������дʱ������Ż���ʾΪ��
	 * [Fruits [num=7, name=apple], Fruits [num=6, name=apple]]
	 * 
	 * 
	 * 
	 * 
	 * ������֪��Set�в��ܴ���ظ���Ԫ�أ���Hashset������ж�����Ԫ���ǲ����ظ��أ�
	 * 		1.Ϊ�����Ч�ʣ������ж�hashCode�����ķ���ֵ�Ƿ���ͬ����hashCode�ķ���ֵ��ͬʱ������Ϊ������Ԫ�ز�ͬ����hashCode��ͬʱ���ٽ��е�2����
	 * 		2.��equals�����Ƚ�����Ԫ�أ��������ֵΪtrue������Ϊ��ͬ�����Ϊfalse,����Ϊ��ͬ��
	 * 
	 * ��ʵ��Hashset�Ǹ��ݸö��������ʹ���ض����㷨���䶨�嵽һ����ַ�ϣ���ô�ں��涨�����������ֻҪ����Ӧ��hashcode��ַ���Ƿ���ֵ���������
	 * ��equals�Ƚϣ� ���û����ֱ�Ӳ��룬��ͼ�����equals��ʹ�ô�����ִ��Ч�ʾʹ������ˡ�
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
	 * ��ʹ��Fruits����ΪHashMap��keyʱ���ж�key�Ƿ��ظ���ԭ����HashSet�ж�Ԫ���Ƿ��ظ�ʱԭ��һ��
	 * ������ʹ��HashMapʱ����дhashCodeҲ�Ǳ�Ҫ�ġ�
	 */
	public static void testHashMap() throws Exception {
		
		Map<Fruits,Fruits> map = new HashMap<>();
		
		map.put(new Fruits(3, "keyFruit"), new Fruits(3, "apple"));
		map.put(new Fruits(3, "keyFruit"), new Fruits(3, "apple"));
		
		System.out.println("map�ĳ����ǣ�"+map.size());
		
		Set<Fruits> keySet = map.keySet();
		
		for (Fruits theKey : keySet) {
			
			System.out.print(theKey +"������������������>");
			
			System.out.println(map.get(theKey));
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
