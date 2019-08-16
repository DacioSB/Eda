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
		if (node.isEmpty())
			return -1;

		if (!node.isEmpty())
			//tamanho esquerda menos tamanho direita, tanto faz
			return height((BSTNode<T>) node.getLeft()) - super.height((BSTNode<T>) node.getRight());

		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node == null || node.isEmpty())
			return;

		
		int balance = calculateBalance(node);
		//Se for maior que 1 esta pesando pra esquerda(faz rotacao pra direita)
		if (balance > 1) {
			//Aqui decide se vai fazer rotacao dupla ou nao, se for preciso,
			//Como este node esta pesando pra esquerda e o node filho a esquerda pesando para a direita
			//fazendo um joelho, entao rotaciona o no filho a esquerda e depois o node para direita
			if (calculateBalance((BSTNode<T>) node.getLeft()) < 0)
				this.leftRotation((BSTNode<T>) node.getLeft());

			this.rightRotation(node);
		}
		//Se for menor que -1 esta pesando pra direita, faz rotacao para esquerda
		else if (balance < -1){
			//Analogo ao balance > 1 (mas aqui rotaciona o filho para a direita e o pai para a )
			if (calculateBalance((BSTNode<T>) node.getRight()) > 0)
				this.rightRotation((BSTNode<T>) node.getRight());

			this.leftRotation(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		//Pega o parent e enquanto ele nao for null vai fazer o rebalance
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
//		if(node.getParent() != null) {
//			rebalance((BSTNode<T>) node.getParent());
//		}
	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
		//Se o node a ser rotacionado for o root, entao o pivot eh setado nele
		if (node.equals(this.getRoot()))
			root = Util.leftRotation(node);
		//Se nao for root, entao faz um aux, que vai receber a rotação, o pivot
		else {
			
			BSTNode<T> aux = Util.leftRotation(node);
			//E por fim reposiciona ele no lugar correto
			//Se for menor que o pai, seta o left do pai pra ele
			//Se for maior que o pai, seta o right do pai pra ele
			if (aux.getData().compareTo(aux.getParent().getData()) < 0)
				aux.getParent().setLeft(aux);
			else
				aux.getParent().setRight(aux);
		}
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
		//Similar
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
