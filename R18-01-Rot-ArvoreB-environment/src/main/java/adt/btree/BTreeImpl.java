package adt.btree;

import java.util.LinkedList;
import java.util.List;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {
		int result = -1;

		if (!node.isEmpty()) {
			if (node.isLeaf())
				return 0;
			else
				return 1 + height(node.getChildren().get(0));
		}
		return result;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		List<BNode<T>> list = new LinkedList<BNode<T>>();

		addListDepthLeftOrder(this.root, list);

		BNode<T>[] result = toArray(list);
		return result;
	}
	
	private BNode<T>[] toArray(List<BNode<T>> list) {
		BNode<T>[] array = new BNode[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}
	
	private void addListDepthLeftOrder(BNode<T> node, List<BNode<T>> list) {
		if (!node.isEmpty()) {
			list.add(node);
			for (int i = 0; i < node.getChildren().size(); i++) {
				addListDepthLeftOrder(node.getChildren().get(i), list);
			}
		}
	}

	@Override
	public int size() {
		if (this.isEmpty())
			return 0;
		else
			return size(this.root);
	}
	
	private int size(BNode<T> node) {
		if (node == null || node.isEmpty())
			return 0;

		else {
			int size = node.size();
			for (BNode<T> child : node.getChildren())
				size += size(child);

			return size;
		}
	}

	@Override
	public BNodePosition<T> search(T element) {
		if (element != null)
			return search(this.getRoot(), element);
		else
			return new BNodePosition<T>();
	}
	
	private BNodePosition<T> search(BNode<T> node, T element) {
		if (node == null || element == null)
			return new BNodePosition<T>();

		else {
			int index = 0;
			
			while (index < node.size() && node.getElementAt(index).compareTo(element) < 0)
				index++;
			
			if (index < node.size() && node.getElementAt(index).equals(element))
				return new BNodePosition<>(node, index);
			else if (node.isLeaf())
				return new BNodePosition<T>();
			else
				return search(node.getChildren().get(index), element);
		}
	}

	@Override
	public void insert(T element) {
		if (element != null)
			insert(getRoot(), element);

	}
	
	private void insert(BNode<T> node, T element) {
		int index = 0;

		while (index < node.size() && node.getElementAt(index).compareTo(element) < 0)
			index++;

		if (index >= node.size() || !node.getElementAt(index).equals(element)) {
			if (node.isLeaf())
				node.addElement(element);
			else
				insert(node.getChildren().get(index), element);
		}
		
		if (node.size() > node.getMaxKeys())
			split(node);
	}

	private void split(BNode<T> node) {
int midIndex = (node.getElements().size() / 2);
		
		BNode<T> left = new BNode<>(this.order);
		BNode<T> right = new BNode<>(this.order);
		
				
		for (int i = 0; i < node.size(); i++) {
			if (i < midIndex)
				left.getElements().add(node.getElementAt(i));
			else if (i > midIndex)
				right.getElements().add(node.getElementAt(i));
		}
		T midElement = node.getElementAt(midIndex);
		
		if (!node.isLeaf()) {
			if (node.getChildren().size() > 0) {
				for (int i = 0; i <= midIndex; i++) {
					left.addChild(i, node.getChildren().get(i));
				}
				int index = 0;
				for (int i = midIndex + 1; i < node.getChildren().size(); i++) {
					right.addChild(index, node.getChildren().get(i));
					index += 1;
				}
			}
		}
		
		if (node.equals(this.getRoot())) {
			BNode<T> newRoot = new BNode<>(this.order);
			newRoot.addElement(midElement);
			node.setParent(newRoot);
			this.root = newRoot;
			newRoot.addChild(0, left);
			newRoot.addChild(1, right);
			newRoot.getChildren().get(0).setParent(newRoot);
			newRoot.getChildren().get(1).setParent(newRoot);
		} else {
			node.addChild(0, left);
			node.addChild(1, right);
			promote(node);
		}
	}

	private void promote(BNode<T> node) {
		int midIndex = (node.getElements().size() / 2);
		
		T midElement = node.getElementAt(midIndex);
		
		node.getElements().clear();
		node.addElement(midElement);
		
		BNode<T> parent = node.getParent();
		
		if (parent != null) {
			node.getChildren().get(0).setParent(parent);
			node.getChildren().get(1).setParent(parent);
			
			int index = parent.getChildren().indexOf(node);
			
			parent.addElement(midElement);
			parent.addChild(index, node.getChildren().get(0));
			parent.addChild(index + 1, node.getChildren().get(1));
			node.getChildren().get(0).setParent(parent);
			node.getChildren().get(1).setParent(parent);
			parent.getChildren().remove(node);
		}
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
