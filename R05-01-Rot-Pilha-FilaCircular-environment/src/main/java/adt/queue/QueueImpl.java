package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if (this.isEmpty())
			return null;

		return this.array[0];
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == (this.array.length - 1);
	}

	private void shiftLeft() {
		for (int i = 0; i < this.tail; i++) {
			this.array[i] = this.array[i + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull())
			throw new QueueOverflowException();

		if (element != null) {
			this.tail++;
			this.array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty())
			throw new QueueUnderflowException();

		T removed = this.array[0];
		this.array[0] = null;

		this.shiftLeft();

		array[this.tail] = null;
		--this.tail;

		return removed;
	}
	//Particular Methods
	
	//ReverseQueue() -> Para reverter uma fila, cria uma pilha, esvazia nossa fila (ate this.isEmpty())e coloca os
	//Elementos na pilha, depois faz o caminho contrario, esvazia a pilha e joga na fila (faz dois whiles)
	
	//ReverseFirstKElements(), outro metodo simples, faz um for ate k fazendo o mesmo muido do reverseQueue
	//Depois faz um while pra inserir na queue enquanto a stack !isEmpty()
	//Por fim faz um for dando dequeue e depois enqueue nos elementos ate queue.size - k
	
	//QueueSLL(), faz uma queue com um node header e um tail. Na hora de fazer o enqueue() faz um novo node com o element passado
	//Se header e tail forem nulos (lista vazia), ambos vao ser iguais ao no que criei, return
	//Se nao tail.next = node e tail = node
	//Para o dequeue se for vazia (header == null) retorna erro,
	//Se nao for, guarda o node removido (o atual header), passa header como sendo header.next() e pronto.
	//Por fim se header = null entao faz tail tambem igual a null, e entao retorna o removed
}
