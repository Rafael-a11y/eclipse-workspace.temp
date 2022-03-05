package servidorsocket;

import java.io.Serializable;

public class Carro implements Serializable{

	private String nome;
	private int potencia;
	
	public Carro() {
		
	}
	
	public Carro(String nome, int potencia) {
		this.nome = nome;
		this.potencia = potencia;
	}
	
	
	
	public final String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}

	public final int getPotencia() {
		return potencia;
	}

	public final void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	@Override
	public String toString() {
		return "Carro [nome=" + nome + ", potencia=" + potencia + "]";
	}
	
	
}
