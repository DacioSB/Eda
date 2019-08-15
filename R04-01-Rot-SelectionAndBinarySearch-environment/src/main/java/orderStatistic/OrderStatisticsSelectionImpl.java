package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no 
	 *   array original
	 * - Nenhum array auxiliar deve ser criado e utilizado.
	 * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   como o selectionsort mas sem modificar nenhuma posicao do array).
	 * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null. 
	 * - Considerar que k varia de 1 a N 
	 * - Sugestao: o uso de recursao ajudara sua codificacao.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
	   	 int left = 0;
	   	 int right = array.length - 1;
	   	 if(k < left || k > right) {
	   		 return null;
	   	 }
	   return array[selection(array, left, right, k)];
	}
	private int selection(T[] array, int left, int right, int k) {
		int pos = k;
		for(int j = left; j < right; j++) {
			int min = left;
			for(int i = left + 1; i < right + 1; i++) {
				if(array[i].compareTo((array[min])) < 0) {
					min = i;
	   			 
	   		 	}
	   	 	}
			if(array[min] == array[k]) {
				pos = min;
				break;
	   		 }
	   	 }
	   	 return pos;
	}

}
