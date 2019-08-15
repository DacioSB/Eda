package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<T>();
		this.last = (DoubleLinkedListNode<T>) head;
	}
	
	@Override
	public void insertFirst(T element) {
		if (element == null) return;

		
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) this.head,
				new DoubleLinkedListNode<T>());

		((DoubleLinkedListNode<T>) this.head).previous = newHead;

		if (this.isEmpty())
			this.last = newHead;

		this.head = newHead;
	}
	

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			this.head = this.head.getNext();

			
			if (this.isEmpty())
				this.last = new DoubleLinkedListNode<T>();

			
			((DoubleLinkedListNode<T>) this.head).previous = new DoubleLinkedListNode<T>();
		}
	}

	@Override
	public void removeLast() {
		if (!this.last.isNIL()) {
			this.last = this.last.getPrevious();

			if (last.isNIL())
				this.head = this.last;

			this.last.next = new DoubleLinkedListNode<T>();
		}
	}
	@Override
	public void insert(T element) {
		if (element == null) return;
		
		
		DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(),
				this.last);

		this.last.next = newLast;

		if (last.isNIL()) // A lista estah vazia.
			this.head = newLast;

		this.last = newLast;

	}
	
	@Override
	public void remove(T element) {
		if (element == null) return; 
		
		if (head.getData() == (element))
			removeFirst();

		else if (last.getData() == (element))
			removeLast();

		else {
			SingleLinkedListNode<T> aux = this.head;

			while (!aux.isNIL() && !aux.data.equals(element))
				aux = aux.next;

			if (!aux.isNIL()) {
				((DoubleLinkedListNode<T>) aux).previous.next = aux.getNext();
				((DoubleLinkedListNode<T>) aux.next).previous = ((DoubleLinkedListNode<T>) aux).getPrevious();
			}
		}
	}
	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
