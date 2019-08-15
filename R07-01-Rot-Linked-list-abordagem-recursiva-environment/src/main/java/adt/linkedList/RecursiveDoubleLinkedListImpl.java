package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}
	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<T>());
				this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());

			} else {
				if (this.next.isEmpty()) {
					this.next.setData(element);
					this.next.setNext(new RecursiveDoubleLinkedListImpl<T>());
					this.getNext().setPrevious(this);
				} else {
					this.getNext().insert(element);
				}
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			RecursiveDoubleLinkedListImpl<T> segundoElemento = new RecursiveDoubleLinkedListImpl<T>(this.data, this.next, this.previous);
			this.setData(element);
			this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
			this.setNext(segundoElemento);
			segundoElemento.setPrevious(this);

		}
	}
	
	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (this.data.equals(element)) {
				removeFirst();
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			this.setData(this.next.getData());
			this.setNext(this.next.getNext());
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.getNext().isEmpty()) {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			} else {
				this.getNext().removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
	@Override
	public RecursiveDoubleLinkedListImpl<T> getNext() {
		return (RecursiveDoubleLinkedListImpl<T>) this.next;
	}
	//Para retornar o node do meio, faz dois ponteiros, um que anda next.next
	//E outro que anda apenas next. Quando o mais rapido chegar no final, o mais devagar
	//Vai estar na metade
//	
//	 void printMiddle() 
//	    { 
//	        Node slow_ptr = head; 
//	        Node fast_ptr = head; 
//	        if (head != null) 
//	        { 
//	            while (fast_ptr != null && fast_ptr.next != null) 
//	            { 
//	                fast_ptr = fast_ptr.next.next; 
//	                slow_ptr = slow_ptr.next; 
//	            } 
//	            System.out.println("The middle element is [" + 
//	                                slow_ptr.data + "] \n"); 
//	        } 
//	    } 
	
	//reverseDLL(){
	
	//	node temp = newDLL<>();
	//  node aux = head;
//	while (aux != null) { 
//        temp = aux.prev; 
//        aux.prev = aux.next; 
//        aux.next = temp; 
//        aux = aux.prev; 
//    } 
	
	//IdenticalSLLs()
//	boolean areIdenticalRecur(Node a, Node b) 
//	{ 
//	    // If both lists are empty 
//	    if (a == null && b == null) 
//	        return true; 
//	  
//	    if (a != null && b != null) 
//	        return (a.data == b.data) &&  areIdenticalRecur(a.next, b.next); 
//	  
//	    return false; 
//	} 
	
	//MergeSortedSLLs
//	public Node SortedMerge(Node A, Node B)  
//    { 
//      
//        if(A == null) return B; 
//        if(B == null) return A; 
//          
//        if(A.data < B.data)  
//        { 
//            A.next = SortedMerge(A.next, B); 
//            return A; 
//        } 
//        else 
//        { 
//            B.next = SortedMerge(A, B.next); 
//            return B; 
//        } 
//          
//    } 
	//reverseSLL()
//	Node reverse(Node node) { 
//        Node prev = null; 
//        Node current = node; 
//        Node next = null; 
//        while (current != null) { 
//            next = current.next; 
//            current.next = prev; 
//            prev = current; 
//            current = next; 
//        } 
//        node = prev; 
//        return node; 
//    } 
	
	//Program for n’th node from the end of a Linked List
	//N = 2
	// nodes = 1 2 3 4 5 6 7 8 9
	//return 8
	//Method 1 (Use length of linked list)
//	1) Calculate the length of Linked List. Let the length be len.
//	2) Print the (len – n + 1)th node from the begining of the Linked List.	
	
//	//removeDuplicatesSortedSLL()
//	void removeDuplicates() 
//    { 
//        Node curr = head; 
//  
//        
//        while (curr != null) { 
//             Node temp = curr; 
//           
//            while(temp!=null && temp.data==curr.data) { 
//                temp = temp.next; 
//            } 
//            
//            curr.next = temp; 
//            curr = curr.next; 
//        } 
//    } 
	
	//PairWiseSwap()
//	void pairWiseSwap() 
//    { 
//        Node temp = head; 
//  
//        
//        while (temp != null && temp.next != null) { 
//  
//            /* Swap the data */
//            int k = temp.data; 
//            temp.data = temp.next.data; 
//            temp.next.data = k; 
//            temp = temp.next.next; 
}
