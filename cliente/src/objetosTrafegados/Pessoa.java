package objetosTrafegados;

import java.io.Serializable;

public class Pessoa implements Serializable{

	public enum EstadoCivil {CASADO, SOLTEIRO, DIVORCIADO, SEPARADO, VIUVO}
	private String nome;
	private int idade;
	private EstadoCivil estadoCivil;
	
	public Pessoa() {
		
	}

	public Pessoa(String nome, int idade, EstadoCivil estadoCivil) {
		this.nome = nome;
		this.idade = idade;
		this.estadoCivil = estadoCivil;
	}

	public final String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}

	public final int getIdade() {
		return idade;
	}

	public final void setIdade(int idade) {
		this.idade = idade;
	}
	
	@Override
	public String toString() {
		return "Pessoa {nome = " + nome + ", idade = " + idade + ", estado civil = " + estadoCivil + "}";
	}
}
