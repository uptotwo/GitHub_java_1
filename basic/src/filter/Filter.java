package filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Filter {
	
	
	public static void main(String[] args) {
		
		List<String> array = new ArrayList<String>();
		Random r = new Random();
		String[] balls = new String[]{"A","B","C"};
		for(int i = 0; i < 100; i++)
		array.add(balls[r.nextInt(3)]);
		
		//	array = array.stream().filter(ball -> ball.equals("B")).collect(Collectors.toList());
		//	-> ��Java8��lambda���ʽ����������������
	}
}
