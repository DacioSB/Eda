package doubleLL;

public class Node {
	private int element;
	private Node next;
	private Node prev;
	  
	public Node(int element, Node prev, Node next){
		this.element = element;
	    this.prev = prev;
	    this.next = next;
	 }
	
	 public int getElement(){
		 return this.element;
	 }
	
	 public Node getPrev(){
		 return this.prev;
	 }
	
	 public Node getNext(){
		 return this.next;
	 }
	
	 public void setPrev(Node prev){
		 this.prev = prev;
	 }
	  
	 public void setNext(Node next){
		 this.next = next;
	 }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + element;
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		result = prime * result + ((prev == null) ? 0 : prev.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (element != other.element)
			return false;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		if (prev == null) {
			if (other.prev != null)
				return false;
		} else if (!prev.equals(other.prev))
			return false;
		return true;
	}
	 
}
