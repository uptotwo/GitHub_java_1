package array_sort;

import java.util.Arrays;
import java.util.Random;

public class Main {
	
	@SuppressWarnings("unused")
	private static int[] intArray = {25,8,21,65,6,2,5,5,1,5,58,6516,15};
	
	
	
	public static void main(String[] args) {
		int[] myarry1;
		myarry1 = getRandomArray();
		choose_sort(myarry1);
		
		myarry1 = getRandomArray();
		bubble_sort(myarry1);
		
		myarry1 = getRandomArray();
		insert_sort(myarry1);
		
		myarry1 = getRandomArray();
		quick_sort(myarry1);
		
		 myarry1 = getRandomArray();
		JAVA_quick_sort(myarry1);
	
	}
	
	/*
	 * 选择法
	 */
	private static void choose_sort(int[] intArray) {
		long begin = System.currentTimeMillis();
		
		for (int i = 0; i < intArray.length; i++) {
			for (int j = i+1; j < intArray.length ; j++) {
					if( intArray[i] > intArray[j] ){
						int temp = intArray[i];
						intArray[i] = intArray[j];
						intArray[j] = temp;
					}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("Sort By choose_sort() :" +(end-begin));
	}
	
	/*
	 * 冒泡法
	 */
	private static void bubble_sort(int[] intArray ) {
		long begin = System.currentTimeMillis();
		
		for (int i = 0; i < intArray.length; i++) {
			for (int j = 0; j < intArray.length - 1 - i; j++) {
				if(intArray[j] > intArray[j+1] ){
					int temp = intArray[j];
					intArray[j] = intArray[j + 1];
					intArray[j + 1] = temp;
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("Sort By bubble_sort() :"+(end-begin));
	}
	
	
	/*
	 * 插入法
	 */
	private static void insert_sort(int[] intArray ) {
		long begin = System.currentTimeMillis();
		//将第一个元素视为一个数组，将其他的元素视为另外一个数组。从第二个数组中按位置顺序取出元素插入第一个数组中
		for (int i = 1; i < intArray.length; i++) {
			//将第二个数组的第一个元素取出放在 要插入的临时变量temp中
			int temp = intArray[i];
			
			int j = i - 1;
			
			//如果第一个数组中j指向的元素  大于 要插入的变量
			while( intArray[j] > temp ){
				//用第一个数组的（最后一个元素）j+1 指向的元素 覆盖 它前一个位置 （ 要插入的元素 在数组中的原来位置）
				intArray[j+1] = intArray[j];
				j--;
				if(j ==-1)break;
			}
			//
			intArray[j+1] = temp;
			
		}
		long end = System.currentTimeMillis();
		System.out.println("Sort By insert_sort() :"+(end-begin));
		
	}
	
	
	/*
	 * 快速排序法
	 * 
	 * 递归
	 * 
	 * 它的基本思路是将数组按大于，小于某个数分为两组，一直分到数组的最大长度为2
	 */
	private static void quick_sort(int[] intArray ) {
		long begin = System.currentTimeMillis();
		doSort(intArray,0,intArray.length-1);
		long end = System.currentTimeMillis();
		System.out.println("sort by quick_sort():"+(end-begin));
	}
	
	private static void doSort(int[] arr,int left,int right){
		//([2,1],0,1)
		//System.out.println("待排数组："+Arrays.toString(arr)+"\n left = "+left+" right = "+right);
		//(0<1)
		if(left < right){
			//2
			int s = arr[left];
			
			//0 
			int i = left;
			//2
			int j = right + 1 ;
			
			/*
			 * 
			 */
			while (true) {
				//向右寻找大于 s 的元素的索引（下标） 
				//1<2&&1<2  2<2  i = 1;  
				while( i+1 < arr.length && arr[++i] < s);
				//向左寻找小于 s 的元素的索引（下标） 
				//1>-1 && 1>2    j=1
				while( j-1 > -1 && arr[--j] > s);
				
				/*
				 * 如果下标为0到i之间的元素都 <= s，同时下下表为 j 到 arr.length 之间的元素都 >= s
				 * 即如果小于 s 的元素都在 大于 s 的元素的左边
				 */
				if(i >= j){
					
					break;
				/*
				 * 如果 从左向右的 第一个大于s的 元素 在 从右向左的第一个小于s的元素的 左边
				 * 即 并不是 所有小于 s 的元素都在 大于 s 的元素的左边
				 * 则将 从左向右的 第一个大于s的 元素 与   从右向左的第一个小于s的元素 的值进行互换
				 */
				}else{
					
					int t = arr[i] ;
					
					arr[i] = arr[j] ;
					
					arr[j] = t ;
					
				}
			}
			/* 
			 * 
			 * 将最左边的元素 与  最后一个 <= s 的元素 的值进行互换，
			 * 使数组的结构为{ <= s 的数,s, >= s 的数}
			 * 
			 */
			arr[left] = arr[j];
			
			arr[j] = s;
			
			//对 <= s 的数进行排序
			doSort(arr,left,j-1);
			//对 >= s 的数进行排序
			doSort(arr,j+1,right);
			
		}
	}
	
	/*
	 * JAVA 自带的数组排序
	 */
	
	private static void JAVA_quick_sort(int[] intArray ){
		
		long begin = System.currentTimeMillis();
		Arrays.sort(intArray);
		long end = System.currentTimeMillis();
		System.out.println("sort by JAVA_quick_sort():"+(end - begin));
		
	}
	
	
	private static int[] getRandomArray() {
		// TODO Auto-generated method stub
		long begin = System.currentTimeMillis();
		int[] array = new int[20000];
		Random rd = new Random();
		for (int i = 0; i < array.length; i++) {
			
			array[i] = rd.nextInt(1000);
			
		}
		long end = System.currentTimeMillis();
//		System.out.println("getRandomArray():"+(end - begin));
		return array;
	}
	
}
