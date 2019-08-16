package adt.bst;

import java.util.ArrayList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	protected int height(BSTNode<T> node) {
		//Se ligar que é se o node é empty
		if(node.isEmpty()) {
			return -1;
		}else {
			int left = height((BSTNode<T>) node.getLeft());
			int right = height((BSTNode<T>) node.getRight());
			return 1 + Math.max(left, right);
		}
		
	}

	@Override
	public BSTNode<T> search(T element) {
		if(element == null) {
			return null;
		}else {
			return search(this.root, element);
		}
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if(node.isEmpty()) {
			return new BSTNode<>();
		}else {
			if(node.getData().compareTo(element) > 0) {
				return search((BSTNode<T>) node.getLeft(), element);
			}else if(node.getData().compareTo(element) < 0) {
				return search((BSTNode<T>) node.getRight(), element);
			}else {
				return node;
			}
		}
	}

	@Override
	public void insert(T element) {
		if(element != null) 
			insert(this.root, element);
	}

	private void insert(BSTNode<T> node, T element) {
		if(node.isEmpty()) {
			node.setData(element);
			node.setRight(new BSTNode<T>());
			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		}else {
			if(node.getData().compareTo(element) > 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			}else {
				insert((BSTNode<T>) node.getRight(), element);
			}
		}
		
	}

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

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if(node.isLeaf()) {
			node.setData(null);
		}else if(node.getLeft().isEmpty() || node.getRight().isEmpty()) {
			if(!node.getParent().isEmpty()) {
				if(node.getParent().getRight().equals(node)) {
					if(node.getLeft().isEmpty()) {
						node.getParent().setRight(node.getRight());
						node.getRight().setParent(node.getParent());
					}else {
						node.getParent().setRight(node.getLeft());
						node.getLeft().setParent(node.getParent());
					}
				}else {
					if(node.getLeft().isEmpty()) {
						node.getParent().setLeft(node.getRight());
						node.getRight().setParent(node.getParent());
					}else {
						node.getParent().setLeft(node.getLeft());
						node.getLeft().setParent(node.getParent());
					}
				}
			}else {
				if(node.getRight().isEmpty()) {
					this.root = (BSTNode<T>) node.getRight();
				}else {
					this.root = (BSTNode<T>) node.getLeft();
				}
			}
		}else {
			T sucessor = sucessor(node.getData()).getData();
			remove(sucessor);
			node.setData(sucessor);
		}
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();
		
		if(!this.isEmpty()) {
			preOrder(this.root, aux);
			aux.toArray(array);
		}
		return array;
	}

	private void preOrder(BSTNode<T> node, List<T> aux) {
		if(!node.isEmpty()) {
			aux.add(node.getData());
			preOrder((BSTNode<T>) node.getLeft(), aux);
			preOrder((BSTNode<T>) node.getRight(), aux);
		}
		
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();
		
		if(!this.isEmpty()) {
			order(this.root, aux);
			aux.toArray(array);
		}
		return array;
	}

	private void order(BSTNode<T> node, List<T> aux) {
		if(!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), aux);
			aux.add(node.getData());
			order((BSTNode<T>) node.getRight(), aux);
		}
		
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();
		
		if(!this.isEmpty()) {
			postOrder(this.root, aux);
			aux.toArray(array);
		}
		return array;
	}

	private void postOrder(BSTNode<T> node, List<T> aux) {
		if(!node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), aux);
			postOrder((BSTNode<T>) node.getRight(), aux);
			aux.add(node.getData());
		}
		
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
