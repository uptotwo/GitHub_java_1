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
	 * ѡ��
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
	 * ð�ݷ�
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
	 * ���뷨
	 */
	private static void insert_sort(int[] intArray ) {
		long begin = System.currentTimeMillis();
		//����һ��Ԫ����Ϊһ�����飬��������Ԫ����Ϊ����һ�����顣�ӵڶ��������а�λ��˳��ȡ��Ԫ�ز����һ��������
		for (int i = 1; i < intArray.length; i++) {
			//���ڶ�������ĵ�һ��Ԫ��ȡ������ Ҫ�������ʱ����temp��
			int temp = intArray[i];
			
			int j = i - 1;
			
			//�����һ��������jָ���Ԫ��  ���� Ҫ����ı���
			while( intArray[j] > temp ){
				//�õ�һ������ģ����һ��Ԫ�أ�j+1 ָ���Ԫ�� ���� ��ǰһ��λ�� �� Ҫ�����Ԫ�� �������е�ԭ��λ�ã�
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
	 * ��������
	 * 
	 * �ݹ�
	 * 
	 * ���Ļ���˼·�ǽ����鰴���ڣ�С��ĳ������Ϊ���飬һֱ�ֵ��������󳤶�Ϊ2
	 */
	private static void quick_sort(int[] intArray ) {
		long begin = System.currentTimeMillis();
		doSort(intArray,0,intArray.length-1);
		long end = System.currentTimeMillis();
		System.out.println("sort by quick_sort():"+(end-begin));
	}
	
	private static void doSort(int[] arr,int left,int right){
		//([2,1],0,1)
		//System.out.println("�������飺"+Arrays.toString(arr)+"\n left = "+left+" right = "+right);
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
				//����Ѱ�Ҵ��� s ��Ԫ�ص��������±꣩ 
				//1<2&&1<2  2<2  i = 1;  
				while( i+1 < arr.length && arr[++i] < s);
				//����Ѱ��С�� s ��Ԫ�ص��������±꣩ 
				//1>-1 && 1>2    j=1
				while( j-1 > -1 && arr[--j] > s);
				
				/*
				 * ����±�Ϊ0��i֮���Ԫ�ض� <= s��ͬʱ���±�Ϊ j �� arr.length ֮���Ԫ�ض� >= s
				 * �����С�� s ��Ԫ�ض��� ���� s ��Ԫ�ص����
				 */
				if(i >= j){
					
					break;
				/*
				 * ��� �������ҵ� ��һ������s�� Ԫ�� �� ��������ĵ�һ��С��s��Ԫ�ص� ���
				 * �� ������ ����С�� s ��Ԫ�ض��� ���� s ��Ԫ�ص����
				 * �� �������ҵ� ��һ������s�� Ԫ�� ��   ��������ĵ�һ��С��s��Ԫ�� ��ֵ���л���
				 */
				}else{
					
					int t = arr[i] ;
					
					arr[i] = arr[j] ;
					
					arr[j] = t ;
					
				}
			}
			/* 
			 * 
			 * ������ߵ�Ԫ�� ��  ���һ�� <= s ��Ԫ�� ��ֵ���л�����
			 * ʹ����ĽṹΪ{ <= s ����,s, >= s ����}
			 * 
			 */
			arr[left] = arr[j];
			
			arr[j] = s;
			
			//�� <= s ������������
			doSort(arr,left,j-1);
			//�� >= s ������������
			doSort(arr,j+1,right);
			
		}
	}
	
	/*
	 * JAVA �Դ�����������
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
