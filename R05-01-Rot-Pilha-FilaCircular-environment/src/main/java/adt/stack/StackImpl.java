package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if (this.isEmpty())
			return null;

		return this.array[this.top];
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == (this.array.length - 1);
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull())
			throw new StackOverflowException();

		if (!this.isFull() && element != null) {
			this.top++;
			this.array[this.top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) 
			throw new StackUnderflowException();

		T valueTop = this.top();
		this.top--;
		return valueTop;
	}
	//**Particular Methods**
	//StackUsingLinkedList() -> A gente acessa todos os nodes recursivamente, e o top la de boas sendo NIL
	//Na hora de fazer o push() faz um node novo chamado temp, faz as ligações necessarias
	//Coloca a data, seta o next do node no top temp.next = top e faz top = temp... (top vai subindo, obvio)
	//Na hora de fazer o pop, é so fazer top = top.next(). (sao ligados por setinhas de cima pra baixo)
	
//	public reverseString(String palavra) {
//		int i = 0;
//		while(!this.isEmpty()) {
//			push(palavra.charAt(i));
//			i++;
//		}
		//Outro while pra fazer pop e jogar no inicio da string
//	}
	//----------------------------//
//	public void sortStack(StackImpl<T> s) throws StackUnderflowException {
//		//Esse if com essa chamada recursiva no proprio metodo é pra ver se 
//		//A stack so possui o top, se nao, entao continua e chama o metodo auxiliar
//		//Tambem serve pra fazer o sorted insert em toda a stack ate fica vazia
//		if(!s.isEmpty()) {
//			T x = s.pop();
//			sortStack(s);
//			sortedInsert(s, x);
//		}
//	}
//
//	private void sortedInsert(StackImpl<T> s, T x) {
//		//Caso base se a stack é vazia ou o elemento que vou inserir é maior que o top
//		//Dai faço o push do elemento normal, ele fica em cima, pois eh maior x > s.top
//		if(s.isEmpty() || x > s.top) {
//			s.push(x);
//			return;
//		}
//		//Se nao for maior que o top, faz pop faz um elemento temporario e chama o metodo recursivo
//		T temp = s.pop();
//		sortedInsert(s, x);
//		//Coloca de volta o elemento top removido anteriormente
//		s.push(temp);
//	}
	//-------------------------//
	//Parentheses()
	//Começamos com um array de parenteses { ( ou [. (Com for) Vamos jogando fazendo push no nosso array da stack se for parenteses de abertura
	//se for parenteses de fechamento e se a nossa stack !isEmpty (se for empty retorna false), entao checa se sao iguais dando
	//com um metodo auxiliar que vai receber isMatchinPair(pop(), array[i](array de parenteses dado no parametro)) se o pop eh igual a um de abertura e se o array[i] igual a um de fechamento
	
	//Declare a character stack S.
	//Now traverse the expression string exp.
	//If the current character is a starting bracket (‘(‘ or ‘{‘ or ‘[‘) then push it to stack.
	//If the current character is a closing bracket (‘)’ or ‘}’ or ‘]’) then pop from stack and if the popped character is the matching starting bracket then fine else parenthesis are not balanced.
	//After complete traversal, if there is some starting bracket left in stack then “not balanced”
}
