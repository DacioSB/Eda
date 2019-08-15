package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array == null || leftIndex > rightIndex || leftIndex < 0
				|| rightIndex < 0 || array.length < rightIndex || array.length == 0)
			return;

		if (leftIndex < rightIndex) {

			int maior = max(array, leftIndex, rightIndex);
			int menor = min(array, leftIndex, rightIndex);
			int range = (maior - menor) + 1;
			
			int[] arrayC = new int[range];

			for (int i = leftIndex; i <= rightIndex; i++) {
				arrayC[array[i] - menor]++;
			}

			for (int j = 1; j < arrayC.length; j++) {
				arrayC[j] += arrayC[j - 1];
			}

			Integer arrayB[] = new Integer[array.length];

			for (int k = rightIndex; k >= leftIndex; k--) {
				arrayB[arrayC[array[k] - menor] - 1] = array[k];
				arrayC[array[k] - menor]--;
			}

			for (int l = leftIndex; l <= rightIndex; l++) {
				array[l] = arrayB[l];
			}
		}
	}

	private int min(Integer[] array, int leftIndex, int rightIndex) {
		Integer menor = array[leftIndex];
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(menor) < 0) {
				menor = array[i];
			}
		}
		
		return menor.intValue();
	}

	public int max(Integer[] array, int leftIndex, int rightIndex) {

		Integer maior = array[leftIndex];
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(maior) > 0) {
				maior = array[i];
			}
		}
		
		return maior.intValue();
	}
}


