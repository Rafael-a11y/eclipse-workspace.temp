package br.com.cod3r.cm.modelo;

import java.util.ArrayList;

import java.util.List;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	//vizinhos � private
	private List<Campo> vizinhos = new ArrayList<Campo>();
	private List<CampoObservador> observadores = new ArrayList<>();
	/*Constroe o objeto*/
	Campo(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public void registrarObservador(CampoObservador observador) {
		observadores.add(observador);
	}
	
	//Os campos observadores s�o notificados que o evento ocorreu e o campo em quest�o junto do
	//evento s�ao passados..
	public void notificarObservadores(CampoEvento evento) {
		observadores.stream().forEach(o -> o.eventoOcorreu(this, evento));
	}
	
	/*Adiciona o campo fornecido se este estiver a uma casa de dist�ncia, sendo na diagonal ou n�o*/
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
	/*Alterna a marca��o do campo, somente se este estiver fechado*/
	public void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
			if(marcado) {
				this.notificarObservadores(CampoEvento.MARCAR);
			}
			else {
				this.notificarObservadores(CampoEvento.DESMARCAR);
			}
		}
	}
	/*Abre o campo, se e somente se estiver fechado e n�o marcado, caso o campo esteja minado, lan�a uma ExplosaoException.
	 * Este m�todo � recursivo, pois para cada vizinho da lista que tenha a vizinhan�a segura, o m�todo abrir � chamado,
	 * ap�s a abertura de todos os campos seguros, retorna um true, deixando claro que a fun��o foi bem sucedida*/
	public boolean abrir() {
		if(!aberto && !marcado) {
			if(minado) {
				this.notificarObservadores(CampoEvento.EXPLODIR);
				return true;
			}
			this.setAberto(true);
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
	public boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	/*Mina o campo*/
	void minar() {
		minado = true;
	}
	/*Verifica se o campo est� marcado, retorna true caso sim, ou false caso contr�rio*/
	public boolean isMarcado() {
		return marcado;
	}
	/*Verifica se o campo est� minado*/
	public boolean isMinado() {
		return minado;
	}
	/*Notifica todos os Observadores que o evento correu, passando o tipo de evento e o campo, 
	 * que � o pr�prio campo em quest�o, isso se repete em todos os outros objetos campos do
	 * tabuleiro que est�o minados. */
	void setAberto(boolean aberto) {
		this.aberto = aberto;
		if(aberto) {
			this.notificarObservadores(CampoEvento.ABRIR);
		}
	}
	/*verifica se o campo est� aberto*/
	public boolean isAberto() {
		return aberto;
	}
	/*Verifica se o  campon est� fechado*/
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
	/*Retorna se o campo est� aberto e n�o minado ou se est� minado e marcado, ou seja, significa que o objetivo daquele
	 * campo foi alcan�ado*/
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	/*Conta o n�mero de vizinhos da lista que est�o minados, e este n�mero � retornado*/
	public int minasNaVizinhanca() {
		return (int) vizinhos.stream().filter(v -> v.minado).count();
	}
	/*Reinicia o campo atual*/
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
		this.notificarObservadores(CampoEvento.REINICIAR);
	}
	/*Retorna valores de texto de acordo com a condi��o atual do campo. Caso esteja marcado(e fechado, obviamente), retorna um x
	 * Caso esteja aberto e minado, retorna um * e logo depois lan�a uma exce��o, terminando o game.
	 * Caso esteja aberto a o n�mero de vizinhos minados seja maior de 0, retorna este valor.
	 * Caso esteja aberto, retorna espa�o (que tamb�m � um caracter para o computador).
	 * Caso contr�rio, retorna uma ?*/
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
