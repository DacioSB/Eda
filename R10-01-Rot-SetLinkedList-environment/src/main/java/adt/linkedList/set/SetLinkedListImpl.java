package adt.linkedList.set;


import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		SingleLinkedListNode<T> node1 = this.head;
		SingleLinkedListNode<T> node2 = null;
		SingleLinkedListNode<T> aux = null;
		
		while(!node1.isNIL() && !node1.getNext().isNIL()) {
			node2 = node1;
			while(!node2.getNext().isNIL()) {
				if(node1.getData().equals(node2.getNext().getData())) {
					aux = node2.getNext();
					node2.setNext(node2.getNext().getNext());
					System.gc();
				}else {
					node2 = node2.getNext();
				}
			}
			node1 = node1.getNext();
		}
	}

	@SuppressWarnings("unused")
	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		SingleLinkedListNode<T> aux = this.head;
		SingleLinkedListNode<T> aux2 = ((SetLinkedListImpl<T>) otherSet).getHead();
		SetLinkedList<T> newList = new SetLinkedListImpl<T>();
		
		while(!aux.isNIL()) {
			newList.insert(aux.getData());
			aux = aux.getNext();
		}
		//return newList;
		while(!aux2.isNIL()) {
			if(!this.isPresent(aux2.getData())) {
				newList.insert(aux2.getData());
			}
			aux2 = aux2.getNext();
		}
		return newList;
	}
	private boolean isPresent(T element) {
		if(this.search(element) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		SingleLinkedListNode<T> aux2 = ((SetLinkedListImpl<T>) otherSet).getHead();
		SetLinkedList<T> newList = new SetLinkedListImpl<T>();
		
		while(!aux2.isNIL()) {
			if(this.isPresent(aux2.getData())) {
				newList.insert(aux2.getData());
			}
			aux2 = aux2.getNext();
		}
		return newList;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
