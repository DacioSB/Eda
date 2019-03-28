package ordenacao;

import java.util.Arrays;

public class OrdenacaoIterativa {

	public static void main(String[] args) {
		int[] array = {8,4,7,1,9,2,3,4,0};
		System.out.printf("%s", Arrays.asList(selectionSort(array, 0, array.length - 1)));

	}
	public static int[] simultaneousbubbleSort(int[] array, int lindex, int rindex) {
		for(int j = lindex; j < rindex; j++) {
			for(int i = 0; i < array.length - 1; i++) {
				if(array[i] > array[i + 1]) {
					//swap
					int aux = array[i];
					array[i] = array[i+1];
					array[i+1] = aux;
				}
			}
			for(int k = array.length - 2; k > 0; k--) {
				if(array[k] < array[k - 1]) {
					//swap
					int aux = array[k];
					array[k] = array[k-1];
					array[k-1] = aux;
				}
			}
			
		lindex += 1;
		rindex -= 1;
		if(lindex >= rindex) {
			break;
		}
		}
		return array;
	}
	public static int[] bubbleSort(int[] array, int lindex, int rindex) {
		for(int j = lindex; j < rindex; j++) {
			for(int i = 0; i < array.length - 1; i++) {
				if(array[i] > array[i + 1]) {
					//swap
					int aux = array[i];
					array[i] = array[i+1];
					array[i+1] = aux;
				}
			}
		}
		return array;
	}
	public static int[] selectionSort(int[] array, int lindex, int rindex) {
		int min;
		for(int i = lindex; i < rindex; i++) {
			min = i;
			for(int j = i + 1; j < rindex + 1; j++) {
				if(array[j] < array[min]) {
					min = j;
				}
			}
			int aux = array[i];
			array[i] = array[min];
			array[min] = aux;
		}
		return array;
	}

}
