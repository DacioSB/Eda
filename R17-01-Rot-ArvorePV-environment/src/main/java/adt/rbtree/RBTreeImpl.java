package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight((RBNode<T>) this.getRoot());
	}
	private int blackHeight(RBNode<T> node) {
		if (node.isEmpty()) return 0;
		//Se a cor do node for preta, soma += 1 no passo recursivo, do contrario so anda um node no passo recursivo
		//Obs: pode se andar pela esquerda ou pela direita pra achar a altura preta
		//Ja que esta eh igual em todos os caminhos possiveis
		if (node.getColour() == Colour.BLACK)
			return 1 + blackHeight((RBNode<T>) node.getLeft());
		else
			return blackHeight((RBNode<T>) node.getLeft());
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes((RBNode<T>) this.getRoot());
	}
	
	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		if (node.isEmpty())
			return true;
		//Verifica se eh vermelho pra poder fazer a verificacao
		if (node.getColour() == Colour.RED) {
			//Se o node red tiver algum node red, ja retorna false, porque ja esta ferindo o invariante
			if (((RBNode<T>) node.getLeft()).getColour() == Colour.RED || ((RBNode<T>) node.getRight()).getColour() == Colour.RED)
				return false;
			else 
				//Se o filho do node red for preto, entao continua se a verificacao pelo resto da arvore pela esquerda
				//e pela direita, em chamadas recursivas e verificando se os lefts estao dentro do invariante e os da direita tambem
				//Se nao retorna false, se sim, retorna true
				return verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())	&& verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
		}
		else 
			return verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())	&& verifyChildrenOfRedNodes((RBNode<T>) node.getRight());

	}

	/**
	 * Verifies the black-height property from the root.
	 */
	private boolean verifyBlackHeight() {
		return verifyBlackHeight((RBNode<T>) this.getRoot());
	}
	
	private boolean verifyBlackHeight(RBNode<T> node) {
		if (node.isEmpty()) return true;
		
		int blackHeightRight = this.blackHeight((RBNode<T>) node.getRight());
		int blackHeightLeft = this.blackHeight((RBNode<T>) node.getLeft());
		
		if (blackHeightLeft == blackHeightRight)
			return true;
		else
			return false;
	}

	@Override
	public void insert(T value) {
		BSTNode<T> nodeBST = search(value);
		
		if (!nodeBST.isEmpty())
			return;
		else
			insert(value, nodeBST);
	}

	private void insert(T value, BSTNode<T> nodeBST) {
		if (nodeBST.isEmpty()) {
			
			super.insert(value);

			RBNode<T> nodeRB = (RBNode<T>) search(value);

			RBNode<T> leftNil = new RBNode<>();
			RBNode<T> rightNil = new RBNode<>();

			leftNil.setParent(nodeRB);
			nodeRB.setLeft(leftNil);

			rightNil.setParent(nodeRB);
			nodeRB.setRight(rightNil);

			nodeRB.setColour(Colour.RED);

			fixUpCase1(nodeRB);
		}
		
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		@SuppressWarnings("unchecked")
		RBNode<T>[] array = new RBNode[super.size()];

		if (!super.isEmpty())
			rbPreOrder((RBNode<T>) super.root, array);

		return array;
	}
	private void rbPreOrder(RBNode<T> node, RBNode<T>[] array) {
		if (!node.isEmpty()) {

			int i = 0;
			while (array[i] != null)
				i++;

			array[i] = node;

			if (!node.getLeft().isEmpty())
				rbPreOrder((RBNode<T>) node.getLeft(), array);
			if (!node.getRight().isEmpty())
				rbPreOrder((RBNode<T>) node.getRight(), array);
		}
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(super.getRoot()))
			node.setColour(Colour.BLACK);
		else
			fixUpCase2(node);
	}

	protected void fixUpCase2(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();

		if (parent.getColour() != Colour.BLACK)
			fixUpCase3(node);
		else
			return;
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();
		RBNode<T> uncle;

		
		if (grandParent.getLeft().equals(parent))
			uncle = (RBNode<T>) grandParent.getRight();

		else
			uncle = (RBNode<T>) grandParent.getLeft();

		if (uncle.getColour() == Colour.RED) {
			parent.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			grandParent.setColour(Colour.RED);

			fixUpCase1(grandParent);
		} else
			fixUpCase4(node);
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();

		//joelho para a esquerda.
		if (parent.getRight().equals(node) && grandParent.getLeft().equals(parent)) {
			Util.leftRotation(parent);
			next = (RBNode<T>) next.getLeft();
		}
		//joelho para a direita.
		else if (parent.getLeft().equals(node) && grandParent.getRight().equals(parent)) {
			Util.rightRotation(parent);
			next = (RBNode<T>) next.getRight();
		}

		fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();

		parent.setColour(Colour.BLACK);
		grandParent.setColour(Colour.RED);

		if (parent.getLeft().equals(node))
			Util.rightRotation(grandParent);
		else
			Util.leftRotation(grandParent);
	}
}
