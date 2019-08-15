package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;

		SingleLinkedListNode<T> aux = this.getHead();

		while (!aux.isNIL()) {
			size++;
			aux = aux.getNext();
		}
		return size;

	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = this.getHead();

		while (!aux.isNIL() && !aux.getData().equals(element)) {
			aux = aux.getNext();
		}
		return aux.getData();
	}

	@Override
	public void insert(T element) {
		if (element == null) 
			return;

		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(element, new SingleLinkedListNode<T>());

		if (this.isEmpty())
			this.head = newNode;

		else {
			SingleLinkedListNode<T> aux = this.getHead();

			while (!aux.getNext().isNIL())
				aux = aux.getNext();

			newNode.next = aux.getNext();
			aux.next = newNode;
		}
	}

	@Override
	public void remove(T element) {
		 if (element == null) return;

		if (this.isEmpty())
			return;

		if (this.getHead() == element)
			this.head = this.head.getNext();

		else {
			SingleLinkedListNode<T> aux = this.getHead();
			SingleLinkedListNode<T> previous = new SingleLinkedListNode<T>();

			while (!aux.isNIL() && !aux.getData().equals(element)) {
				previous = aux;
				aux = aux.getNext();
			}

			if (!aux.isNIL())
				previous.next = aux.getNext();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		if (this.isEmpty())
			return (T[]) new Object[0];
		
		T[] result = (T[]) new Object[this.size()];

		SingleLinkedListNode<T> auxNode = this.head;
		//Pra que esse counter? kkkk enfim
		int counter = 0;

		while (!auxNode.isNIL()) {
			result[counter] = auxNode.getData();
			auxNode = auxNode.getNext();
			counter++;
		}

		return result;

	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
