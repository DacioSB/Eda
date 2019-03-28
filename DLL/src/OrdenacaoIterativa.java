package ordenacao;

import java.util.Arrays;

public class OrdenacaoIterativa {

	public static void main(String[] args) {
		int[] array = {9,5,7,2,1,8,3};
		System.out.print(Arrays.asList(bubbleSort(array, 0, array.length - 1)));

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
	public static int[] simultaneousBubble(int[] array, int lindex, int rindex) {
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

}
