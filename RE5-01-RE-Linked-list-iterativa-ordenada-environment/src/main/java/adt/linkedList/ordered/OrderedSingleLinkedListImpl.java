package adt.linkedList.ordered;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;
import util.ComparatorCrescente;

/**
 * Para testar essa classe voce deve implementar seu comparador. Primeiro
 * implemente todos os métodos requeridos. Depois implemente dois comparadores
 * (com idéias opostas) e teste sua classe com eles. Dependendo do comparador
 * que você utilizar a lista funcionar como ascendente ou descendente, mas a
 * implemntação dos métodos é a mesma.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class OrderedSingleLinkedListImpl<T extends Comparable<T>> extends SingleLinkedListImpl<T> implements
		OrderedLinkedList<T> {

	private Comparator<T> comparator;

	public OrderedSingleLinkedListImpl(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public OrderedSingleLinkedListImpl(ComparatorCrescente comparatorCrescente) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public T minimum() {
		T[] array = this.toArray();
		List<T> arrayL = new ArrayList<>();
		for(T a : array) {
			arrayL.add(a);
		}
		Collections.sort(arrayL, comparator);
		return arrayL.get(0);
	}

	@Override
	public T maximum() {
		if(!this.isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			while(!aux.getNext().isNIL()) {
				aux = aux.getNext();
			}
			if(!aux.isNIL()) {
				return aux.getData();
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
}
