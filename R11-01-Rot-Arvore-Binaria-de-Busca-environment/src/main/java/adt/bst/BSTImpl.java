package adt.bst;

import java.util.ArrayList;
import java.util.Arrays;
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
	private int height(BSTNode<T> node) {
		//VerFunfando
		if(node.isEmpty()) {
			return -1;
		}else {
			int left = height((BSTNode<T>) node.getLeft());
			int right = height((BSTNode<T>) node.getRight());
			return 1 + Math.max(left, right);
		}
	}
	public static void main(String[] args) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		BSTImpl<Integer> tree1 = new BSTImpl<>();
		//Integer[] array = { 6, 23, -34, 5, 9, -35, 26};
		Integer[] array = { 9,12,11,17,1,5,10,13,6,16};
		
		
		for (int i : array) {
			tree.insert(i);
			tree1.insert(i);
		}
		System.out.println(Arrays.toString(tree.postOrder()));
	}

	@Override
	public BSTNode<T> search(T element) {
		if(element == null) {
			return null;
		}else {
			return search(this.root, element);
		}
	}
	private BSTNode<T> search(BSTNode<T> node, T element){
		if(node.isEmpty()) {
			return new BSTNode<>();
		}else {
			if(node.getData().compareTo(element) > 0) {
				return search((BSTNode<T>)node.getLeft(), element);
			}else if(node.getData().compareTo(element) < 0) {
				return search((BSTNode<T>)node.getRight(), element);
			}else {
				return node;
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;
		else
			insert(this.root, element);
	}

	private void insert(BSTNode<T> node, T element) {
		//Se o node eh vazio, faz do zero
		if(node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		}else {
			if (node.getData().compareTo(element) > 0)
				insert((BSTNode<T>) node.getLeft(), element);
			else
				insert((BSTNode<T>) node.getRight(), element);
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
		if(node.getRight().isEmpty()) {
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
		
		if(node.isEmpty()) {
			return null;
		//Se o node tiver filho a direita, entao eh so pegar o min da subarvore a direita do node
		}else if(!(node.getRight().isEmpty())) {
			return this.minimum((BSTNode<T>) node.getRight());
		//Se nao tiver filho a direita, entao precisa chamar a funcao auxiliar recursiva
		//Pra pegar o menor maior que o node
		}else {
			return sucessor(node);
		}
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		if(node.getParent() == null) {
			return null;
		}else {
			//Se o filho a direita do pai do node for o proprio node faz a chamada recursiva subindo na arvore
			//Se nao, retorna o pai do node, isso significa que o no atual nao eh filho a direita do pai, e sim  esquerda
			//Possibilitando o retorno do node pai, que consequentemente eh o menor maior que o node que procuramos
			if(!(node.getParent().getRight().equals(node))) {
				return (BSTNode<T>) node.getParent();
			}else {
				return sucessor((BSTNode<T>)node.getParent());
			}
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty())
			return null;
		else if(!(node.getLeft().isEmpty())) {
			return this.maximum((BSTNode<T>) node.getLeft());
		}else {
			return predecessor(node);
		}
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		if(node.getParent() == null) {
			return null;
		}else if(!(node.getParent().getLeft().equals(node))) {
			return (BSTNode<T>) node.getParent();
		}else {
			return predecessor((BSTNode<T>)node.getParent());
		}
		
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		//No caso de ser folha
		if (node.isLeaf()) {
			node.setData(null);
		//No caso de ter apenas um filho
		}else if(node.getLeft().isEmpty() || node.getRight().isEmpty()) {
			//Primeira condicao: Se o node eh ou nao eh root da arvore
			if(node.getParent() != null) {
				//Se nao for root da arvore e o node for um filho a direita
				if(!node.getParent().getLeft().equals(node)) {
					//Se node for um filho a direita e o filho de node for a esquerda
					if(!node.getLeft().isEmpty()) {
						node.getParent().setRight(node.getLeft());
						node.getLeft().setParent(node.getParent());
					//Se node for um filho a direita e o filho de node for a direita
					}else {
						node.getParent().setRight(node.getRight());
						node.getRight().setParent(node.getParent());
					}
				//Se nao for root da arvore e o node for um filho a esquerda
				}else {
					//Se o no node for um filho a esquerda e o filho de node for a esquerda
					if(!node.getLeft().isEmpty()) {
						node.getParent().setLeft(node.getLeft());
						node.getLeft().setParent(node.getParent());
					//Se node for um filho a direita e o filho de node for a direita
					}else {
						node.getParent().setLeft(node.getRight());
						node.getRight().setParent(node.getParent());
					}
				}
			//No caso do node ter um filho apenas e ser root da arvore
			}else {
				//Se a raiz tiver seu filho a direita
				if(node.getLeft().isEmpty()) {
					this.root = (BSTNode<T>) node.getRight();
				//Se a raiz tiver seu filho a esquerda
				}else {
					this.root = (BSTNode<T>) node.getLeft();
				}
				this.root.setParent(null);
			}
		//No caso do node ter dois filhos
		}else {
			T successor = sucessor(node.getData()).getData();
			remove(successor);
			node.setData(successor);
		}
	}

	@Override
	public T[] preOrder() {
		@SuppressWarnings("unchecked")
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
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();
		
		if(!this.isEmpty()) {
			order(this.root, aux);
			aux.toArray(array);
		}
		return array;
	}

	private void order(BSTNode<T> node, List<T> aux) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), aux);
			aux.add(node.getData());
			order((BSTNode<T>) node.getRight(), aux);
		}
	}

	@Override
	public T[] postOrder() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();
		
		if(!this.isEmpty()) {
			postOrder(this.root, aux);
			aux.toArray(array);
		}
		return array;
	}
	private void postOrder(BSTNode<T> node, List<T> aux) {
		if (!node.isEmpty()) {
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
	
	//**Particular Methods**
	public Integer sum() {
		if(!this.isEmpty()) {
			return sum(this.root);
		}else {
			return null;
		}
	}

	private Integer sum(BSTNode<T> node) {
		Integer result = 0;
		if(!node.isEmpty()) {
			//Similar ao size, so que em vez de somar 1 a soma das duas subarvores
			//Nos somamos o valor do node a soma das duas subarvores
			result += (Integer)node.getData() + sum((BSTNode<T>) node.getLeft()) + sum((BSTNode<T>) node.getRight());
		}
		return result;
	}
	public int countLeaves() {
		if(!this.isEmpty()) {
			return countLeaves(this.root);
		}else {
			return 0;
		}
	}

	private int countLeaves(BSTNode<T> node) {
		//Se chegou no node e ele eh NIL, return 0
		if(node.isEmpty()) {
			return 0;
		}
		//Se o no eh uma folha, retorna 1
		if(node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			return 1;
		//Se o node nao eh folha, nem vazio (ta no meio da arvore)
		//faz a chamada recursiva da soma das duas subarvores
		}else {
			return countLeaves((BSTNode<T>) node.getLeft()) + countLeaves((BSTNode<T>) node.getRight());
		}
		
	}
	//Metodo sumLeaveNodes, utiliza a mesma logica de countLeaves so que em vez de retornar 
	//a quantidade dos nodes, retorna o getdata() do node
	
	public int countNonLeaves() {
		if(!this.isEmpty()) {
			return countNonLeaves(this.root);
		}else {
			return 0;
		}
	}

	private int countNonLeaves(BSTNode<T> node) {
		//Se o node que eu cheguei for null, retorna zero
		if(node.isEmpty()) {
			return 0;
		}
		//Se o node tiver pelo menos um filho, faz a recursao somando 1 a soma das duas subarvores
		if(!node.getLeft().isEmpty() || !node.getRight().isEmpty()) {
			return 1 + countNonLeaves((BSTNode<T>) node.getLeft()) + countNonLeaves((BSTNode<T>) node.getRight());
		//Se nao tiver filhos, entao eh folha, retorna 0;
		}else {
			return 0;
		}
	}
	//-------------------------//
	public void mirrorTree() {
		this.root = mirrorTree(this.root);
	}
	private BSTNode<T> mirrorTree(BSTNode<T> node) {
		//Se o node eh empty retorna o node
		if(node.isEmpty()) {
			return node;
		}
		//Percorre a direita e a esquerda
		BSTNode<T> left = mirrorTree((BSTNode<T>) node.getLeft());
		BSTNode<T> right = mirrorTree((BSTNode<T>) node.getRight());
		//Faz as trocas
		node.setLeft(right);
		node.setRight(left);
		//Retorna o node como root
		return node;
	}
	//MaxLevelSum, complexo
	
	//********Lembrar do elementsAtKDistance******
	
	public boolean fullBinaryTree() {
		return fullBinaryTree(this.root);
	}

	private boolean fullBinaryTree(BSTNode<T> node) {
		//Se a arvore eh vazia, ela eh valida
		if(node.isEmpty()) {
			return true;
		}
		//Arvore que eh apenas um node leaf
		//Serve tambem pra ao chegar num leaf, retornar true, pois ja passou por todos
		//sem alarmar falso
		if(node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			return true;
		}
		//Se nao eh nem vazia, nem leaf, entao o node tem dois filhos, faz a chamada
		//recursiva nas duas subarvores
		if(!node.getLeft().isEmpty() & !node.getRight().isEmpty()) {
			return fullBinaryTree((BSTNode<T>) node.getLeft()) && fullBinaryTree((BSTNode<T>) node.getRight());
		}
		//Se nao eh vazia, se nao alcancou folhas nem tem filhos com dois filhos (se nao entrou em nenhum dos returns)
		//entao ela nao eh full
		return false;
	}
	public int levelNode(T element) {
		
		if(this.isEmpty()) {
			return -1;
		}else {
			//Procura o elemento na arvore pra poder pegar o nivel dele
			BSTNode<T> node = search(element);
			return levelNode(this.root, node);
		}
	}
	//------------------//
	private int levelNode(BSTNode<T> root, BSTNode<T> node) {
		//Se node for empty, retorna -1
		if(node.isEmpty() || root.isEmpty()) {
			return -1;
		}else {
			//Similar ao search
			if(root.getData().compareTo(node.getData()) > 0) {
				return 1 + levelNode((BSTNode<T>) root.getLeft(), node);
			}else if(root.getData().compareTo(node.getData()) < 0) {
				return 1 + levelNode((BSTNode<T>) root.getRight(), node);
			}else {
				return 1;
			}
		}
	}
	public Integer oddEvenLevel() {
		if(this.isEmpty()) {
			return null;
		}else {
			return oddEvenLevel(this.root);
		}
	}

	private Integer oddEvenLevel(BSTNode<T> node) {
		if(node.isEmpty()) {
			return 0;
		}
		return (Integer)node.getData() - oddEvenLevel((BSTNode<T>) node.getLeft()) - oddEvenLevel((BSTNode<T>)node.getRight());
	}
	//------------------//
	public boolean identicalTrees(BSTImpl<T> tree2) {
		return identicalTrees(this.root, tree2.root);
	}

	private boolean identicalTrees(BSTNode<T> root1, BSTNode<T> root2) {
		if(root1.isEmpty() && root2.isEmpty()) {
			return true;
		}
		if(!root1.isEmpty() && !root2.isEmpty()) {
			if(root1.getData().compareTo(root2.getData()) == 0) {
				return identicalTrees((BSTNode<T>)root1.getLeft(), (BSTNode<T>)root2.getLeft()) && identicalTrees((BSTNode<T>)root1.getRight(), (BSTNode<T>)root2.getRight());
			}
		}
		return false;
	}
	//-------------------------//
	public void removeHalfNodes() {
		removeHalfNodes(this.root);
	}

	private void removeHalfNodes(BSTNode<T> node) {
		if(node.isEmpty()) {
			return;
		}else {
			if(node.getLeft().isEmpty() || node.getRight().isEmpty()) {
				remove(node.getData());
				
			}
		}
		removeHalfNodes((BSTNode<T>) node.getLeft());
		removeHalfNodes((BSTNode<T>) node.getRight());
		
	}
	public boolean perfectBinaryTree() {
		if(this.isEmpty()) {
			return true;
		}else {
			return perfectBinaryTree(this.root);
		}
	}

	private boolean perfectBinaryTree(BSTNode<T> node) {
		if(node.isEmpty()) {
			return true;
		}
		if(!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			return perfectBinaryTree((BSTNode<T>) node.getLeft()) && perfectBinaryTree((BSTNode<T>) node.getRight());
		}
		if(node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			return true;
		}
		return false;
		
	}
	//minimumDepth() e isMirror() [itself], soma dos de baixo
	public T lowestCommmonAncestor(T element1, T element2) {
		return lowestCommomAncestor(this.root, element1, element2);
	}

	private T lowestCommomAncestor(BSTNode<T> node, T element1, T element2) {
		//Admitindo que element1 e element2 existem
		if(node.isEmpty()) {
			return null;
		}
		if(node.getData().compareTo(element1) > 0 && node.getData().compareTo(element2) > 0) {
			return lowestCommomAncestor((BSTNode<T>) node.getLeft(), element1, element2);
		}
		if(node.getData().compareTo(element1) < 0 && node.getData().compareTo(element2) < 0) {
			return lowestCommomAncestor((BSTNode<T>) node.getRight(), element1, element2);
		}
		return node.getData();
	}
	

	

}
