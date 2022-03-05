package fluxos;

import java.util.List;

public class Calculadora implements Runnable {

	private List<Integer> dadosEntrada;
	
	public Calculadora(List<Integer> dadosEntrada) {
		this.dadosEntrada = dadosEntrada;
	}

	@Override public void run() {
		String nome = Thread.currentThread().getName();
		System.out.println(nome + " iniciando o processamento.");
		for(Integer num : this.dadosEntrada) {
			System.out.println(nome + " calculou " + num * num);
		}
		System.out.println(nome + " encerrando o processamento.");
	}
}
