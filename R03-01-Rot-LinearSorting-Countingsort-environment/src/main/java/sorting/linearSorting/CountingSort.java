package sorting.linearSorting;


import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array == null || leftIndex > rightIndex || leftIndex < 0
				|| rightIndex < 0 || array.length < rightIndex || array.length == 0)
			return;

		if (leftIndex < rightIndex) {

			int maior = max(array, leftIndex, rightIndex);

			int[] arrayC = new int[maior + 1];

			for (int i = leftIndex; i <= rightIndex; i++) {
				arrayC[array[i]]++;
			}

			for (int j = 1; j < arrayC.length; j++) {
				arrayC[j] += arrayC[j - 1];
			}

			Integer arrayB[] = new Integer[array.length];

			for (int k = rightIndex; k >= leftIndex; k--) {
				arrayB[arrayC[array[k]] - 1] = array[k];
				arrayC[array[k]]--;
			}

			for (int l = leftIndex; l <= rightIndex; l++) {
				array[l] = arrayB[l];
			}
		}
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
