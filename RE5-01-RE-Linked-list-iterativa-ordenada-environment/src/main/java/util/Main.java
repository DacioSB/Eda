package util;

import java.util.Arrays;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.ordered.OrderedSingleLinkedListImpl;

public class Main {

	public static void main(String[] args) {
		SingleLinkedListImpl<Integer> sll = new OrderedSingleLinkedListImpl<>(new ComparatorCrescente());
		sll.insert(2);
		sll.insert(7);
		sll.insert(1);
		sll.insert(10);
		sll.insert(8);
		System.out.println(((OrderedSingleLinkedListImpl<Integer>) sll).minimum());
		System.out.println(Arrays.toString(sll.toArray()));
		
	}

}
