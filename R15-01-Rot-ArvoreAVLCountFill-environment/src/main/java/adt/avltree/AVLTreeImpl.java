package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	public void insert(T element) {
		if (element == null)
			return;
		else {
			super.insert(element);
			BSTNode<T> node = search(element);
			//Apos a insercao balanceia
			rebalanceUp(node);
		}
	}
	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				//Apos a remocao rebalanceia
				rebalanceUp(node);

			} else if (node.getLeft().isEmpty() || node.getRight().isEmpty()) {
				if (node.getParent() != null) {
					if (!node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}

					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
				//Apos a remocao balanceia
				rebalanceUp(node);
			} else {
				T sucessorNode = sucessor(node.getData()).getData();
				remove(sucessorNode);
				node.setData(sucessorNode);
			}
		}
	}
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node == null)
			return -1;

		if (!node.isEmpty())
			//tamanho esquerda menos tamanho direita, tanto faz
			return height((BSTNode<T>) node.getLeft()) - super.height((BSTNode<T>) node.getRight());

		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		
		int balance = calculateBalance(node);
		//Se for menor que -1 esta pesando pra direita, faz rotacao para esquerda
		if (balance < -1) {
			//Analogo ao balance > 1
			if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
				rightRotation((BSTNode<T>) node.getRight());
			}
			leftRotation(node);
		//Se for maior que 1 esta pesando pra esquerda(faz rotacao pra direita)
		//Aqui decide se vai fazer rotacao dupla ou nao, se for preciso,
		//Como este node esta pesando pra esquerda e o node filho a esquerda pesando para a direita
		//fazendo um joelho, entao rotaciona o no filho a esquerda e depois o node para direita
		} else if (balance > 1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
				leftRotation((BSTNode<T>) node.getLeft());
			}
			rightRotation(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
	
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
		

	}


	protected void leftRotation(BSTNode<T> node) {
		//Se o no for o root, faz a rotacao nele e nada mais
		if (node.equals(this.getRoot()))
			root = Util.leftRotation(node);
	//Se o node nao for o root, pega o node proveniente da rotacao a esquerda
	//E compara com seu pai, se o node for menor que seu pai, seta a esquerda do pai
	//Se for maior, seta a direita do pai
		else {

			BSTNode<T> aux = Util.leftRotation(node);

			if (aux.getData().compareTo(aux.getParent().getData()) < 0)
				aux.getParent().setLeft(aux);
			else
				aux.getParent().setRight(aux);
		}
	}

	
	protected void rightRotation(BSTNode<T> node) {
		//Similar ao leftRotation
		if (node.equals(this.getRoot())) {
			root = Util.rightRotation(node);
		} else {

			BSTNode<T> aux = Util.rightRotation(node);

			if (aux.getData().compareTo(aux.getParent().getData()) > 0)
				aux.getParent().setRight(aux);
			else
				aux.getParent().setLeft(aux);
		}
	}
}
