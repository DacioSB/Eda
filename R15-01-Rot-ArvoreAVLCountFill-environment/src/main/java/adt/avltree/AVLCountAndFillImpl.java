package adt.avltree;

import java.util.Arrays;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}
	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		boolean doubleRotation = false;
		if (balance < -1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
				rightRotation((BSTNode<T>) node.getRight());
				doubleRotation = true;
			}
			leftRotation(node);
			if (doubleRotation)
				this.RLcounter++;
			else
				this.RRcounter++;

		} else if (balance > 1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
				leftRotation((BSTNode<T>) node.getLeft());
				doubleRotation = true;
			}
			rightRotation(node);
			if (doubleRotation)
				this.LRcounter++;
			else
				this.LLcounter++;
		}
	}

	@Override
	public void fillWithoutRebalance(T[] array) {

		Arrays.sort(array);
		quickInsert(array, 0, array.length - 1);
	}

	private void quickInsert(T[] array, int leftIndex, int rightIndex) {
		//Eh bem mais simples... Era so inserir com um padrao que nunca permitisse
		//Que o no ficasse desbalanceado, da forma que a arvore sempre
		//Ficasse parecida com uma arvore completa
		if (leftIndex <= rightIndex) {

			int middle = (rightIndex + leftIndex) / 2;
			super.insert(array[middle]);
			//Processamento paralelo... Vamos aos testes
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					quickInsert(array, leftIndex, middle - 1);
				}
			});

			Thread thread2 = new Thread(new Runnable() {
				@Override
				public void run() {
					quickInsert(array, middle + 1, rightIndex);
				}
			});

			thread.start();
			thread2.start();
		}
	}
	public static void main(String[] args) {
		AVLCountAndFill<Integer> tree1 = new AVLCountAndFillImpl<Integer>();
		Integer[] data = { 8, 4, 6, 12, 10 };
//		for (Integer integer : data) {
//			tree1.insert(integer);
//		}
//		String counts = tree1.LLcount() + " " + tree1.LRcount() + " " + tree1.RLcount() + " "+ tree1.RRcount();
//		System.out.println(counts);
		
		tree1.fillWithoutRebalance(data);
		System.out.println(tree1.getRoot().getRight());
	}


}
