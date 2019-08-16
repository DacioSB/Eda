package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		if (height < 0 || newValue == null)
			return;
		//Lista de nodes update
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		//node auxiliar que começa sendo a raiz
		SkipListNode<T> aux = this.root;
		//Mesmo sistema do search, so que a cada vez que abaixa um nivel (for) eh adicionado
		//aquele elemento ao array de updates
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (aux.forward[i] != null && aux.forward[i].getKey() < key)
				aux = aux.forward[i];
			update[i] = aux; 
		}
		aux = aux.forward[0];
		//Se o proximo da linha[0] for igual a key do que se quer inserir, apenas atualizamos o valor do node
		if (aux.getKey() == key)
			aux.setValue(newValue);
		//Se nao for igual, entao eh um elemento novo.
		else {
			//Se a height for invalida
			if (height > this.maxHeight) {
				//Pra cada altura de baixo pra cima a partir de maxHeight ate height (que nesse caso eh maior) os updates se tornam o root e a maxHeight
				//Eh atualizada para ser a nova height passada
				for (int i = this.maxHeight; i < height; i++)
					update[i] = this.root;

				this.maxHeight = height;
			}
			//aux agora eh o novo node que queremos inserir
			aux = new SkipListNode<T>(key, height, newValue);

			//For final, e mais importante, ele vai atualizar a lista.
			//Pra cada linha de baixo pra cima, o forward do aux vai ser o forward do update
			//E o forward do update, vai ser o proprio aux;
			for (int i = 0; i < height; i++) {
				aux.forward[i] = update[i].forward[i];
				update[i].forward[i] = aux;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> aux = this.root;

		// Algoritmo de pesquisa, com diferenca de guardar o aux nos updates quando se desce(for)
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (aux.forward[i] != null && aux.forward[i].getKey() < key)
				aux = aux.forward[i];
			update[i] = aux; 
		}
		//aux eh o proximo de onde parou na linha
		aux = aux.forward[0];
		//Se aux for igual a key, entao o achamos
		if (aux.getKey() == key) {
			//Andando pelas linhas de baixo pra cima, se o proximo do update, for diferente do node
			//nao tem porque atualizar mais, a altura ja passou da altura do aux
			for (int i = 0; i < this.maxHeight; i++) {
				if (update[i].forward[i] != aux)
					break;
				update[i].forward[i] = aux.forward[i];
			}
		}
	}

	@Override
	public int height() {
		if (size() == 0)
			return 0;

		else {
			//Pega o primeiro node na linha de baixo depois do root (header)
			SkipListNode<T> aux = this.root.forward[0];
			
			int height = 0;
			//Enquanto nao chegar no final da skip
			while (!aux.equals(this.NIL)) {
				//Se a altura do aux for maior que a maior altura atual (comeca com 0)
				//Entao height (valor retornado) eh a altura do aux
				//Obs: sempre andando por baixo. Na primeira linha
				if (height < aux.height())
					height = aux.height();
				aux = aux.forward[0];
			}
			return height;
		}
	}

	@Override
	public SkipListNode<T> search(int key) {
		//Uma lista de skipNodes que sao updates, que eh inutil
		//SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		//node inicial
		SkipListNode<T> aux = this.root;

		//Se a key for menor que a procurada, anda pra frente (while) se for maior, anda pra
		//baixo (for)
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (aux.forward[i] != null && aux.forward[i].getKey() < key)
				aux = aux.forward[i];
			//update[i] = aux; 
		}
		//aux eh o proximo do primeiro nivel
		aux = aux.forward[0];
		//retorna o aux se a key for igual
		if (aux.getKey() == key)
			return aux;
		else
			return null;
	}

	@Override
	public int size() {
		//Trivial
		int level = 0;
		int size = 0;

		SkipListNode<T> aux = this.root;

		while (aux.forward[level] != this.NIL) {
			aux = aux.forward[level];
			size++;
		}
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		//O size do array vai ser o size() mais 2, por conta do node
		//root e node NIL
		int size = this.size() + 2; // Incluindo nos sentinelas.
		//Array
		SkipListNode<T>[] result = new SkipListNode[size];
		//Node de partida (header)
		SkipListNode<T> aux = this.root;
		//Andando sempre na linha 0, vai adicionando o bixo no array
		for (int i = 0; i < size; i++) {
			result[i] = aux;
			aux = aux.forward[0];
		}
		return result;
	}

}
