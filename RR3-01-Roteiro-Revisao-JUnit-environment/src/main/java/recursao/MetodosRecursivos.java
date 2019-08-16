package recursao;

public class MetodosRecursivos {
	public static void main(String[] args) {
		//Object[] array = {2, 4, 5, 1, null, null, 7};
		System.out.println(potenciaDe2(4));
	}
	public int calcularSomaArray(int[] array){
		int result = 0;
		// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR A SOMA
		// DOS EMENTOS DE UM ARRAY
		return result;
	}
	public static long calcularFatorial(int n) {
		if(n == 0) {
			return 1;
		}
		return (n * calcularFatorial(n - 1));
	}

	public static int calcularFibonacci(int n) {
		// ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR E IMPRIMIR
		// O N-ESIMO TERMO
		// DA SEQUENCIA DE FIBONACCI, QUE TEM A SEGUINTE LEI DE FORMACAO: O
		// PRIMEIRO E SEGUNDO NUMEROS
		// DA SEQUENCIA SÃO 1. A PARTIR DO TERCEIRO TERMO, CADA TERMO DA
		// SEQUENCIA É DADO
		// PELA SOMA DOS OUTROS DOIS ANTERIORES. OBSERVE QUE SENDO O METODO
		// RECURSIVO, O FIBONACCI DOS NUMEROS ANTERIORES TAMBEM VAO SER
		// IMPRESSOS
		
		if(n == 1 || n == 2) {
			return 1;
		}
		return (calcularFibonacci(n - 1) + calcularFibonacci(n - 2));
		
	}

	public static int countNotNull(Object[] array, int i) {
		int soma = 0;
		
		if(array[i] != null) {
			soma += 1;
		}
		if(i > 0) {
			soma = soma + countNotNull(array, i - 1);
		}
		return soma;
		
	}

	public static long potenciaDe2(int expoente) {
		if(expoente == 1) {
			return 2;
		}
		return 2 * potenciaDe2(expoente - 1);
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = 0;
		if(n == 1) {
			
		}else {
			result = progressaoAritmetica(termoInicial, razao, n - 1) + razao;
		}
		
		return result;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = 1;
		if(n == 1) {
			
		}else {
			result = progressaoGeometrica(termoInicial, razao, n -1) * razao;
		}
		return result;
	}
	
	
}
