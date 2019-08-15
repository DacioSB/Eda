package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		if(this.head.isNIL()) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int size() {
		int tamanho = 0;
		
		SingleLinkedListNode<T> aux = this.head;
		
		while(!aux.isNIL()) {
			tamanho += 1;
			aux = aux.getNext();
		}
		return tamanho;
	}

	@Override
	public T search(T element) {
		
		if(element == null) {
			return null;
		}else{
			SingleLinkedListNode<T> aux = this.head;
			while(!aux.isNIL() && !aux.getData().equals(element)) {
				aux = aux.getNext();
			}
			return aux.getData();
		}
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(element, new SingleLinkedListNode<T>());
			//No caso de ser vazio, considera a cabeça como o newNode...
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
		if(element != null && !this.head.isNIL()) {
			if(this.head.getData().equals(element)) {
				this.head = this.head.getNext();
			}else {
				SingleLinkedListNode<T> aux = this.getHead();
				SingleLinkedListNode<T> previous = this.getHead();
				
				while(!aux.isNIL() && !aux.getData().equals(element)) {
					previous = aux;
					aux = aux.getNext();
				}
				if(!aux.isNIL()) {
					previous.setNext(aux.getNext());
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		//Implementação recursiva
		int i = 0;
		
		T[] array = (T[]) new Object[this.size()];
		if(this.isEmpty()) {
			return array;
		}
		this.toArray(array, this.head, i);
		return array;
	}
	private void toArray(T[] array, SingleLinkedListNode<T> node, int i) {
		if(!node.isNIL()) {
			array[i] = node.getData();
			toArray(array, node.getNext(), ++i);
		}
	}
	
	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
