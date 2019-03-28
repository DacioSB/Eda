package recursion;

public class SomaSequencia {
	public static void main(String[] args) {
		System.out.print(somatorio(5));

	}
	public static int somatorio(int n) {
		int soma = 0;
		if(n == 1) {
			soma += 1; 
		}else {
			soma = n + somatorio(n - 1);
		}
		return soma;
	}

}
