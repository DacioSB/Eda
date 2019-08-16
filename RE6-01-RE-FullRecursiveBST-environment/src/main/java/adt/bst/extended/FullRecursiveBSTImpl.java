package adt.bst.extended;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class FullRecursiveBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements FullRecursiveBST<T> {

	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> maximum() {
		if(this.isEmpty()) {
			return null;
		}else {
			return maximum(this.root);
		}
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if(node.getRight().getData() == null) {
			return node;
		}else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}
	
	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> minimum() {
		if(this.isEmpty()) {
			return null;
		}else {
			return minimum(this.root);
		}
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if(node.getLeft().isEmpty()) {
			return node;
		}else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}
	
	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a direita
	 * entao o sucessor sera o minimum do filho a direita. Caso contrario
	 * o sucessor sera o primeiro ascendente a ter um valor maior.
	 */
	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if(this.isEmpty()) {
			return null;
		}else {
			if(!node.getRight().isEmpty()) {
				return minimum((BSTNode<T>) node.getRight());
			}else {
				return sucessor(node);
			}
		}
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		if(node.getParent().isEmpty()) {
			return null;
		}else {
			if(node.getParent().getRight().equals(node)) {
				return sucessor((BSTNode<T>)node.getParent());
			}else {
				return (BSTNode<T>) node.getParent();
			}
		}
		
	}

	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a esquerda
	 * entao o predecessor sera o maximum do filho a esquerda. Caso contrario
	 * o predecessor sera o primeiro ascendente a ter um valor menor.
	 */
	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if(this.isEmpty()) {
			return null;
		}else {
			if(!node.getLeft().isEmpty()) {
				return maximum((BSTNode<T>) node.getLeft());
			}else {
				return predecessor(node);
			}
		}
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		if(node.getParent() == null) {
			return null;
		}else {
			if(node.getParent().getLeft().equals(node)) {
				return predecessor((BSTNode<T>)node.getParent());
			}else {
				return (BSTNode<T>) node.getParent();
			}
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] elementsAtDistance(int k) {
		//Primeiro se faz um arrayList
		List<T> result = new ArrayList<T>();
		//Se o k for valido
		if(k < 0 || k > this.height()) {
			return null;
		}else {
			//Chama o metodo auxiliar passando o arrayList, o k e o root pra fazer o passo recursivo
			elementsAtDistance(result, k, this.root);
			//Return util
			return (T[]) result.toArray(new Comparable[0]);
		}
	}

	private void elementsAtDistance(List<T> result, int k, BSTNode<T> node) {
		if(node.isEmpty()) {
			return;
		}
		if(k == 0) {
			result.add(node.getData());
		}else {
			elementsAtDistance(result, k-1, (BSTNode<T>) node.getLeft());
			elementsAtDistance(result, k-1, (BSTNode<T>) node.getRight());
		}
	}
	public static void main(String[] args) {
		FullRecursiveBST<Integer> tree = new FullRecursiveBSTImpl<>();
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
		System.out.println(Arrays.toString(tree.elementsAtDistance(0)));
	}
}
