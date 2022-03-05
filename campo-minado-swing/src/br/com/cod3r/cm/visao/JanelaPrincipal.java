package br.com.cod3r.cm.visao;
import javax.swing.JFrame;

import br.com.cod3r.cm.modelo.Tabuleiro;
public class JanelaPrincipal extends JFrame{

	public JanelaPrincipal() {
		Tabuleiro tabuleiro = new Tabuleiro(16, 30, 30); //Passando a quantidade de linhas, colunas e minas.
		add(new PainelTabuleiro(tabuleiro)); //Passando um JPanel PainelTabuleiro que o tabuleiro.
		setTitle("Campo Minado"); //Setando o t�tulo.
		setSize(690, 438); //Setando as dimens�es de largura e altura da Janela.
		setLocationRelativeTo(null); //Setando a posi��o da janela para o meio da tela.
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//O processo do aplicativo encerra ao fechar a �ltima janela aberta
		setVisible(true);//Setando a visibilidade como verdadeira.
		
	}
	public static void main(String[] args) {
		
		new JanelaPrincipal();
	}
}
