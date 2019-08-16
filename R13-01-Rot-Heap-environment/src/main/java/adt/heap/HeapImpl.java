package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		if(position >= 0 && !this.isEmpty()) {
			//Left e right do element na position... Largest vamos decidir depois
			int left = this.left(position);
			int right = this.right(position);
			int largest;
			
			//Se left eh maior que o tamanho do heap, e left for maior que o position entao left eh o maior
			if (left < this.size() && this.getComparator().compare(this.heap[left], this.heap[position]) > 0) {
				largest = left;
			//Se nao, o position eh o maior ate agora
			} else {
				largest = position;
			}
			//Mesmo sistema do anterior, mas eh agora se ele eh maior que largest, se for, ele eh o maior
			if (right < this.size() && this.getComparator().compare(this.heap[right], this.heap[largest]) > 0) {
				largest = right;
			}
			//Se largest for diferente de position, eh porque tem algum filho que eh maior que ele
			//Entao faz o swap entre largest e position e faz a chamada recursiva em largest
			if (largest != position) {
				Util.swap(this.heap, largest, position);
				this.heapify(largest);
			}
		}
	}
	public static void main(String[] args) {
		Heap<Integer> heap = new HeapImpl<Integer>((object1, object2) -> object1.compareTo(object2));
		
//		heap.buildHeap(new Integer[] { 82, 6, 99, 12, 34});
//		heap.insert(8);
//		heap.extractRootElement();
//		heap.insert(12);
//		heap.insert(-2);
//		heap.insert(7);
//		heap.insert(8);
//		heap.insert(-5);
//		heap.insert(14);
//		heap.insert(3);
//		heap.insert(-10);
//		heap.insert(0);
//		System.out.println(heap.toString());
				
	}
	@Override
	public String toString() {
		String nhau = "[";
		for(int i = 0; i < this.heap.length; i++) {
			if(heap[i]!= null) {
				nhau += heap[i] + ",";
			}
		}
		nhau += "]";
		return nhau;
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		if (element == null)
			return;
		//Faz index +=1 coloca o element na heap (index come�a com -1) no final da heap
		this.index++;
		this.heap[this.index] = element;
		//faz i igual o index
		int i = this.index;
		//Enquanto i > 0 e o filho for maior que o pai, faz o swap na heap do pai com o filho
		//Depois deixa i como o indice do pai
		while (i > 0 && this.getComparator().compare(this.heap[i], this.heap[this.parent(i)]) > 0) {
			Util.swap(this.heap, i, parent(i));
			i = parent(i);

		}
	}

	@Override
	public void buildHeap(T[] array) {
		//Faz a heap ser o array passado
		this.heap = array;
		// O index eh o array.length -1, como de praxe
		this.index = (array.length - 1);
		//Ate o meio do array, faz o heapify... Na verdade desce la do meio pra o i = 0
		for(int i = this.size()/2; i >= 0; i--) {
			heapify(i);
		}
		
	}
	@Override
	public T extractRootElement() {
		if (this.isEmpty())
			return null;

		else {
			//Removed eh igual a root(heap[0]), transforma o root em null
			//Faz o swap entre ele e o ultimo elemento (heap,0, index)
			//Decrementa o index
			//Faz o heapify no novo root
			//Por fim retorna o element removido
			T removed = heap[0];
			this.heap[0] = null;
			Util.swap(heap, 0, this.index);
			this.index--;

			heapify(0);

			return removed;
		}
	}

	@Override
	public T rootElement() {
		if (this.isEmpty())
			return null;
		else
			return this.heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		//Muda momentaneamente o comparator que foi passado ao construir a heap
		//Pois queremos ordenar de forma crescente
		Comparator<T> newComparator = getComparator();

		setComparator((o1, o2) -> o1.compareTo(o2));
		//Faz o build heap com o array passado e cria um novo array sorted de Comparables
		//E do tamanho do array passado no parametro
		buildHeap(array);
		T[] sorted = (T[]) new Comparable[array.length];
		//(Approach 2) De forma decrescente, tira o root do heap e o acrescenta no final de sorted
		for (int i = this.index; i >= 0; i--) {
			sorted[i] = extractRootElement();
		}
		//Retorna com o comparator utilizado na construcao da heap e retorna o array ordenado
		setComparator(newComparator);
		return sorted;
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
