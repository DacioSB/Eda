package doubleLL;

public class DoubleLL {
	private Node header;
	private Node trailer;
	private int size;
	
	public DoubleLL() {
		this.header = new Node(0, null, null);
		this.trailer = new Node(0, this.header, null);
		this.header.setNext(trailer);
	}
	public int getSize() {
		return this.size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public int first() {
		if(size == 0) {
			throw new IllegalArgumentException("Lista vazia!");
			
		}
		return this.header.getNext().getElement();
	}
	public int last() {
		if(size == 0) {
			throw new IllegalArgumentException("Lista vazia!");
			
		}
		return this.trailer.getPrev().getElement();
	}
	public void addFirst(int element) {
		addBetween(element, this.header, this.header.getNext());
	}
	public void addLast(int element) {
		addBetween(element, this.trailer.getPrev(), this.trailer);
	}
	public int removeFirst() {
		if(isEmpty()) {
			throw new IllegalArgumentException("Lista vazia!");
		}
		return this.remove(this.header.getNext());
	}
	public int removeLast() {
		if(isEmpty()) {
			throw new IllegalArgumentException("Lista vazia!");
		}
		return this.remove(this.trailer.getPrev());
	}
	private void addBetween(int element, Node prev, Node next) {
		Node newest = new Node(element, prev, next);
		prev.setNext(newest);
		next.setPrev(newest);
		this.size++;
	}
	private int remove(Node node) {
		Node prev = node.getPrev();
		Node next = node.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		this.size--;
		return node.getElement();
	}
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		DoubleLL other = (DoubleLL) obj;
		if(this.getSize() != other.getSize()) {
			return false;
		}
		Node walkA = this.header;
		Node walkB = other.header;
		
		while(!walkA.equals(trailer)) {
			if((walkA.getElement() != walkB.getElement())) {
				return false;
			}
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true;
	}
}
