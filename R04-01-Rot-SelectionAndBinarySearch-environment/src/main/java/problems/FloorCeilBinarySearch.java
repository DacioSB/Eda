package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		return binarySearchFloor(array, 0, array.length -1, x);
	}
	private Integer binarySearchFloor(Integer[] array, int left, int right, Integer x) {
		//Se x for menor que o menor do array, não existe
		if(x < array[left]) {
			return null;
		}
		//Se x for maior que o maior do array ou igual, o floor no array é o proprio maior do array
		if(x >= array[right]) {
			return array[right];
		}
		int mid = (left + right)/2;
		//Segurar alguns stackOverFlow
		if(mid == left) {
			return array[left];
		}
		if(x.compareTo(array[mid]) == 0 ) {
	   		return array[mid];
	   	}else if(x.compareTo(array[mid]) > 0) {
	   		return binarySearchFloor(array, mid, right, x);
	   	}else {
	   		return binarySearchFloor(array, left, mid - 1, x);
	   	}
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		return binarySearchCeil(array, 0, array.length - 1, x);
	}
	private Integer binarySearchCeil(Integer[] array, int left, int right, Integer x) {
		//Se x for menor que o menor do array, o ceil é o menor elemento do array
		if(x <= array[left]) {
			return array[left];
		}
		//Se x for maior que o maior do array, o ceil para o array nao existe
		if(x > array[right]) {
			return null;
		}
		int mid = (left + right)/2;
		if(x.compareTo(array[mid]) == 0 ) {
			return array[mid];
		}else if(x.compareTo(array[mid]) > 0) {
			return binarySearchCeil(array, mid + 1, right, x);
		}else {
			return binarySearchCeil(array, left, mid, x);
			   	}
	}

}
