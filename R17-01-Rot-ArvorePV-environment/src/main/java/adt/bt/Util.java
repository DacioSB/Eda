package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		//Pivot eh o filho a direita de node (rotacao a esquerda)
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		//Pai de pivot eh o pai de node
		pivot.setParent(node.getParent());
		//pai de node eh pivot
		node.setParent(pivot);
		//Filho a direita de node agora eh o filho a esquerda do pivot
		node.setRight(pivot.getLeft());
		//O pai do filho a esquerda de pivot eh o node
		pivot.getLeft().setParent(node);
		//filho a esquerda de pivot eh node
		pivot.setLeft(node);
		//retorna o pivot
		return (BSTNode<T>) pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		pivot.setParent(node.getParent());
		node.setParent(pivot);
		node.setLeft(pivot.getRight());
		pivot.getRight().setParent(node);
		pivot.setRight(node);

		return (BSTNode<T>) pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
