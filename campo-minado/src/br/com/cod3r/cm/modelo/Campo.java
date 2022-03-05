package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	//vizinhos é private
	private List<Campo> vizinhos = new ArrayList<Campo>();
	/*Constroe o objeto*/
	Campo(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}
	/*Adiciona o campo fornecido se este estiver a uma casa de distância, sendo na diagonal ou não*/
	boolean adicionarVizinho (Campo vizinho) {
		boolean linhaDiferente = this.linha != vizinho.linha;
		boolean colunaDiferente = this.coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(this.linha - vizinho.linha);
		int deltaColuna = Math.abs(this.coluna - vizinho.coluna);
		int deltaGeral = deltaLinha + deltaColuna;
		
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		}
		
		else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}
		
		return false;
	}
	/*Alterna a marcação do campo, somente se este estiver fechado*/
	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
		}
	}
	/*Abre o campo, se e somente se estiver fechado e não marcado, caso o campo esteja minado, lança uma ExplosaoException.
	 * Este método é recursivo, pois para cada vizinho da lista que tenha a vizinhança segura, o método abrir é chamado,
	 * após a abertura de todos os campos seguros, retorna um true, deixando claro que a função foi bem sucedida*/
	boolean abrir() {
		if(!aberto && !marcado) {
			aberto = true;
			
			if(minado) {
				throw new ExplosaoException();
			}
			
			if(vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
		}
		
		else {
			return false;
		}
	}
	/*Verifica a lista de vizinhos e retorna true caso nenhum dos vizinhos esteja minado*/
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	/*Mina o campo*/
	void minar() {
		minado = true;
	}
	/*Verifica se o campo está marcado, retorna true caso sim, ou false caso contrário*/
	public boolean isMarcado() {
		return marcado;
	}
	/*Verifica se o campo está minado*/
	public boolean isMinado() {
		return minado;
	}
	void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
	/*verifica se o campo está aberto*/
	public boolean isAberto() {
		return aberto;
	}
	/*Verifica se o  campon está fechado*/
	public boolean isFechado() {
		//nega isAberto
		return !isAberto();
	}
	/*Retorna a linha*/
	public int getLinha() {
		return linha;
	}
	/*Retorna a coluna*/
	public int getColuna() {
		return coluna;
	}
	/*Retorna se o campo está aberto e não minado ou se está minado e marcado, ou seja, significa que o objetivo daquele
	 * campo foi alcançado*/
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	/*Conta o número de vizinhos da lista que estão minados, e este número é retornado*/
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}
	/*Reinicia o campo atual*/
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	/*Retorna valores de texto de acordo com a condição atual do campo. Caso esteja marcado(e fechado, obviamente), retorna um x
	 * Caso esteja aberto e minado, retorna um * e logo depois lança uma exceção, terminando o game.
	 * Caso esteja aberto a o número de vizinhos minados seja maior de 0, retorna este valor.
	 * Caso esteja aberto, retorna espaço (que também é um caracter para o computador).
	 * Caso contrário, retorna uma ?*/
	public String toString() {
		if(marcado) {
			return "x";
		}
		else if(aberto && minado) {
			return "*";
		}
		else if(aberto && minasNaVizinhanca() > 0) {
			return Long.toString(minasNaVizinhanca());
		}
		else if(aberto) {
			return " ";
		}
		else {
			return "?";
		}
	}	
}
