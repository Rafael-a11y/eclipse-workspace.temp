package fluxos;

import java.util.Arrays;

public class ProgramaClaculadora {

	public static void main(String[] args) {
		Integer[] numerosCalcular = {1, 2, 3, 4, 5};
		Calculadora calc1 = new Calculadora(Arrays.asList(numerosCalcular));
		Calculadora calc2 = new Calculadora(Arrays.asList(numerosCalcular));
		new Thread(calc1).run();
		new Thread(calc2).start();
		
	}
}
