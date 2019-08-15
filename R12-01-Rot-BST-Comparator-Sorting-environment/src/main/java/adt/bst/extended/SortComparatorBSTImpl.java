package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	//Comparator definido pelo usuario (esta la nos testes)
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T[] sort(T[] array) {
		//Se nao for vazia, esvazia.
		if(!super.isEmpty()) {
			super.root = new BSTNode.Builder().data(null).left(null).right(null).parent(null).build();
		}
		//Pra cada elemento do array, vou inserindo na arvore, esse insert chama
		//o metodo public insert que por sua vez chama o private insert, similar ao
		//Da bstimpl
		for(int i = 0; i < array.length; i++) {
			insert(array[i]);
		}
		//E entao retorna o array dos elementos da arvore em ordem
		return super.order();
	}
	@Override
	public void insert(T element) {
		//Se element nao for null, chama o insert passando super.root e o element
		if(element != null) {
			insert(super.root, element);
		}
	}
	private void insert(BSTNode<T> node, T element) {
		//Similar ao insert da classe mae
		if(node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		}else {
			//A diferenca reside aqui, chamamos o comparator pra comparar
			//Chama o unico metodo compare, passando o node.getData() e o element
			//Antes era node.getData().compareTo(element) > 0 
			if(this.comparator.compare(node.getData(), element) > 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			}else {
				insert((BSTNode<T>) node.getRight(), element);
			}
		}
	}
	
	@Override
	public BSTNode<T> search(T element) {
		if (element == null)
			return null;

		return search(super.root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty())
			return new BSTNode<>();

		else {
			//Tambem mudamos no search, chama o compare pra comparar node.getData() e element
			//Antes era node.getData().compareTo(element)>0
			if (this.comparator.compare(node.getData(), element) > 0)
				return search((BSTNode<T>) node.getLeft(), element);
			else if (this.comparator.compare(node.getData(), element) < 0)
				return search((BSTNode<T>) node.getRight(), element);
			else
				return node;
		}
	}

	@Override
	public T[] reverseOrder() {
		@SuppressWarnings("unchecked")
		//Construo novo array de comparables
		T[] array = (T[]) new Comparable[this.size()];
		//Poderia tambem ter construido um arraylist pra auxiliar
		if (!this.isEmpty())
			//Chama metodo privado passando root e array
			reverseOrder(this.root, array);

		return array;
	}

	private void reverseOrder(BSTNode<T> node, T[] array) {
		if (!node.isEmpty()) {
			//Era o que eu estava pensando
			//Faz o mesmo que o order so que sem aux
			//Chama o metodo recursivamente pela direita
			reverseOrder((BSTNode<T>) node.getRight(), array);
			//Depois quando achar posicao nula, insere o data do cara la
			int i = 0;
			while (array[i] != null)
				i++;

			array[i] = node.getData();
			//Percorre subarvore a esquerda
			reverseOrder((BSTNode<T>) node.getLeft(), array);
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
