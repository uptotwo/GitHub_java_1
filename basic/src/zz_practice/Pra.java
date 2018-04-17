package zz_practice;

import java.util.Arrays;

public class Pra {
	
	private static int[] array = {25,8,21,65,6,2,5,5,1,5,58,6516,15};
	
	public static void main(String[] args) {
		
//		insertsort();
		devideSort(array,0,array.length-1);
	}
	
	private static void insertsort() {
		
		for (int i = 1; i < array.length; i++) {
			
			int temp = array[i];
			
			int j = i - 1;
			
			while(array[j] > temp){
				
				array[j+1] = array[j];
				
				j--;
				
				if(j == -1) break;
				
			}
			array[j+1] = temp;
		}
		System.out.println(Arrays.toString(array));
		
	}
	
	
	private static void devideSort(int[] arr,int left,int right){
		
		if(left < right){
			int s = arr[left];
			
			int i = left;
			int j = right+1;
			while(left < right){
				
				while(i+1 < array.length && array[i+1] < s){
					i++;
				}
				while( j-1 > -1 && arr[j-1] >s){
					j--;
				}
				
				if( i >= j){
					break;
				} else{
					int t = array[i];
					array[i] = array[j];
					array[j] = t;
				};
				
			}
			
			arr[left] = arr[j];
			arr[j] = s;
			
			devideSort(arr,left,j-1);
			devideSort(arr, j+1, right);
			
		}
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
