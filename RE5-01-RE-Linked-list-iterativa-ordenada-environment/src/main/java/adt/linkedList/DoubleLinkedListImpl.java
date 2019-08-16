package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	//Lembrar de inicializar
	public DoubleLinkedListImpl() {
		super.setHead(new DoubleLinkedListNode<T>());
		//last começa como o head
		this.last = (DoubleLinkedListNode<T>) super.getHead();
	}
	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>(element, (DoubleLinkedListNode<T>) super.getHead(), new DoubleLinkedListNode<>());
		
		((DoubleLinkedListNode<T>)super.head).setPrevious(newHead);
		
		if(isEmpty()) {
			this.last = newHead;
		}
		this.setHead(newHead);
	}

	@Override
	public void removeFirst() {
		if(!this.isEmpty()) {
			this.setHead(this.head.getNext());
			
			if(this.isEmpty()) {
				this.last = ((DoubleLinkedListNode<T>) this.head);
			}
			((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<>());
		}
	}

	@Override
	public void removeLast() {
		if(!this.last.isNIL()) {
			this.last = this.last.getPrevious();
			if(this.last.isNIL()) {
				this.setHead(last);
			}
			this.last.setNext(new DoubleLinkedListNode<>());
		}
	}
	@Override
	public void remove(T element) {
		if(element != null) {
			if(this.getHead().equals(element)) {
				removeFirst();
			}else if(this.getLast().equals(element)) {
				removeLast();
			}else {
				SingleLinkedListNode<T> aux = super.getHead();
				while(!aux.isNIL() && !aux.getData().equals(element)) {
					aux = aux.getNext();
				}
				
				if(!aux.isNIL()) {
					((DoubleLinkedListNode<T>) aux).getPrevious().setNext(aux.getNext());
					((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(((DoubleLinkedListNode<T>) aux).getPrevious());
				}
			}
		}
	}
	@Override
	public void insert(T element) {
		if(!this.head.isNIL()) {
			DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<T>(), this.last);
			this.last.setNext(newLast);
			if(!isEmpty()) {
				this.setHead(newLast);
			}
			this.last = newLast;
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
