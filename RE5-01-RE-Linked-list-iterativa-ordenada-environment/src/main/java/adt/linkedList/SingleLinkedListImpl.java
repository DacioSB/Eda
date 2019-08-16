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
		SingleLinkedListNode<T> aux = this.head;
		while(!aux.isNIL()) {
			size += 1;
			aux = aux.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		if(element == null) {
			return null;
		}else {
			SingleLinkedListNode<T> aux = this.head;
			while(!aux.isNIL() && !(aux.getData().equals(element))) {
				aux = aux.getNext();
			}
			return aux.getData();
		}
	}

	@Override
	public void insert(T element) {
		if(element == null) {
			return;
		}else {
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(element, new SingleLinkedListNode<T>());
			
			if(this.isEmpty()) {
				this.head = newNode;
			}else {
				SingleLinkedListNode<T> aux = this.head;
				while(!aux.getNext().isNIL()) {
					aux = aux.getNext();
				}
				newNode.setNext(aux.getNext());
				aux.setNext(newNode);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && !this.isEmpty()) {
			if(this.head.equals(element)) {
				//Importante lembrar que é head.getNext e nao um nó nil
				this.head = head.getNext();
			}else {
				SingleLinkedListNode<T> aux = this.head;
				SingleLinkedListNode<T> prev = this.head;
				
				while(!aux.isNIL() && !aux.getData().equals(element)) {
					prev = aux;
					aux = aux.getNext();
				}
				if(!aux.isNIL()) {
					prev.setNext(aux.getNext());
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		if(this.isEmpty()) {
			return (T[]) new Object[0];
		}
		int i = 0;
		
		T[] array = ((T[]) new Object[this.size()]);
		SingleLinkedListNode<T> aux = this.head;
		
		while(!aux.isNIL()) {
			array[i] = aux.getData();
			i++;
			aux = aux.getNext();
		}
		return array;
		
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
