package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo> campos = new ArrayList<>();
	/*Construtor do tabuleiro, constrói um tabuleiro com quantidades de campos e minas de acordo com os dados passados*/
	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associarOsVizinhos();
		sortearMinas();
	}
	
	public void abrir(int linha, int coluna) {
		try {
			campos.parallelStream().filter(c -> c.getLinha() == linha
					&& c.getColuna() == coluna).
			findFirst().ifPresent(c -> c.abrir());
		}
		catch(ExplosaoException e ){
			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}
	
	public void alternarMarcacao(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLinha() == linha
				&& c.getColuna() == coluna).
		findFirst().ifPresent(c -> c.alternarMarcacao());
	}
	/*Adiciona um campo para cada linha e coluna do tabuleiro*/
	private void gerarCampos() {
		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				campos.add(new Campo(linha, coluna));
			}
		}
	}
	/*Percorre a lista de campos e cada campo da lista percorre os outros campos, verificando se estes estão de acordo
	 * com as condições estabelecidas no método adicionarVizinho*/
	private void associarOsVizinhos() {
		for(Campo c1 : campos) {
			for(Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}
	/*Sorteia as minas de forma randônica*/
	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();
		do {
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
			minasArmadas = campos.stream().filter(minado).count();
		}while(minasArmadas < minas);
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	/*Reinicia todos os campos*/
	public void reiniciar() {
		campos.stream().forEach( c -> c.reiniciar());
		sortearMinas();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("  ");
		for(int c = 0; c < colunas; c++) {
			sb.append(" ");
			sb.append(c);
			sb.append(" ");
		}
		sb.append("\n");
		
		int i = 0;
		for (int l = 0; l < this.linhas; l++) {
			sb.append(l);
			sb.append(" ");
			for (int c = 0; c < this.colunas; c++) {
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
