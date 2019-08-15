package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
		
	}
	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}


	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if (this.isEmpty())
			return 0;
		
		else
			return 1 + this.getNext().size();
	}

	@Override
	public T search(T element) {
		if (element == null || this.isEmpty()) 
			return null;
		
		if (this.getData().equals(element))
			return element;
		else
			return this.getNext().search(element);
	}

	@Override
	public void insert(T element) {
		if (element == null) 
			return;
		
		if (this.isEmpty()) {
			this.data = element;
			this.next = new RecursiveSingleLinkedListImpl<T>();
		}
		else
			this.next.insert(element);
	}

	@Override
	public void remove(T element) {
		if (element == null || this.isEmpty()) 
			return;
		
		if (this.data.equals(element)) {
			this.setData(this.next.getData());
			this.setNext(this.next.getNext());
		}
		else
			this.next.remove(element);
	}

	@Override
	public T[] toArray() {
		int i = 0;
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[this.size()];
		
		if (this.isEmpty())
			return array;
		
		this.toArray(array, this, i);
		return array;
	}
	private void toArray(T[] array, RecursiveSingleLinkedListImpl<T> node, int i) {
		if (!node.isEmpty()) {
			array[i] = node.getData();
			this.toArray(array, node.getNext(), ++i);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
