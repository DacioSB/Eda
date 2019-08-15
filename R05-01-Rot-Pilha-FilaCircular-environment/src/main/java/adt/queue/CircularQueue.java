package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	@SuppressWarnings("unchecked")
	public CircularQueue(int size) {
		array = ((T[]) new Object[size]);
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()) {
			throw new QueueOverflowException();
		}
		this.tail = (this.tail + 1) % array.length;
        array[this.tail] = element;
        elements++;
        if (this.head == -1) {
			this.head = this.tail;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T element;
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}
		element = array[this.head];
        array[this.head] = null;
        this.head = (this.head + 1) % array.length;
        this.elements--;
        return element;
	}

	@Override
	public T head() {
		if (this.isEmpty())
			return null;

		return array[this.head];
	}

	@Override
	public boolean isEmpty() {
		return (this.elements == 0);
	}

	@Override
	public boolean isFull() {
		return (this.elements == array.length);
	}

}
