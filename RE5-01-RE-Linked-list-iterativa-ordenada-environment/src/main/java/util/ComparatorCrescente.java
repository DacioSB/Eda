package util;

import java.util.Comparator;

import adt.linkedList.SingleLinkedListNode;


public class ComparatorCrescente implements Comparator<SingleLinkedListNode<Integer>>{

	@Override
	public int compare(SingleLinkedListNode<Integer> o1, SingleLinkedListNode<Integer> o2) {
		if(o1.getData() < o2.getData()) {
			return -1;
		}else if(o1.getData() > o2.getData()) {
			return 1;
		}else {
			return 0;
		}
	}

	
	
}
