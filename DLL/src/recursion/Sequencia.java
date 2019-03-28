package recursion;

public class Sequencia {
	//Se o retorno vier depois, ele faz as chamadas e depois executa o retorno... Se vier antes
	//Ele executa o retorno e depois faz as chamadas recursivas
	private static String dacs = " ";
	public static void main(String[] args) {
		imprimeSequencia(5);
		System.out.print(dacs);

	}
	public static void imprimeSequencia(int n) {
		if(n == 0) {
			dacs += n + " ";
		} else {
			imprimeSequencia(n - 1);
			dacs += n + " ";
			
		}
	}

}
