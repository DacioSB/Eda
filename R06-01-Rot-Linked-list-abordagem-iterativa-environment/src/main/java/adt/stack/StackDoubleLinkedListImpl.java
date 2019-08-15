package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element == null) return;
		
		if (this.isFull()) 
			throw new StackOverflowException();

		this.top.insertFirst(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty())
			throw new StackUnderflowException();

		T element = ((DoubleLinkedListImpl<T>) this.top).getLast().getData();
		this.top.removeFirst();
		this.size--;
		return element;
	}

	@Override
	public T top() {
		if (!this.isEmpty())
			return this.top.toArray()[0];
		
		return null;
	}

	@Override
	public boolean isEmpty() {
		return (this.top.size() == 0);
	}

	@Override
	public boolean isFull() {
		return (this.top.size() == this.size);
	}

}
