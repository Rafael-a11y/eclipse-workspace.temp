package br.com.cod3r.cm.visao;
// E se eu trocar o MouseListener p�r ActionListener? Lembrar de verificar se o bot�o direito � o de valor 2
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import br.com.cod3r.cm.modelo.Campo;
import br.com.cod3r.cm.modelo.CampoEvento;
import br.com.cod3r.cm.modelo.CampoObservador;
import jdk.jfr.Experimental;
public class BotaoCampo extends JButton implements CampoObservador, MouseListener{

	private static final long serialVersionUID = 1L;
	
	/*Objeeto Color do pacote java.awt, serve para definir as cores dos componentes, os valores
	 * inteiros servem para indicar o tom de Red, Green e Blue, os valores v�o de 0 at� 255,
	 * caso o inteiro seja 0, significa aus�ncia daquela cor, quanto maior o n�mero, mais 
	 * clara � a tonalidade, e o inverso significa um tom mais escuro. Lembrando que estas 3 cores
	 * s�o as cores prim�rias, ou seja, todas as outras cores nascem da mistura de tonalidades 
	 * destas 3, a constante TEXTO_VERDE ser� de cor verde com um tom mais voltado para o escuro,
	 * n�o ter� a presen�a ddo vermelho ou do azul, caso o inteiro seja 255, seria um verde bem
	 * claro.*/
	private final Color BG_PADRAO = new Color(184, 184, 184);
	private final Color BG_MARCAR = new Color(8, 179, 247);
	private final Color BG_EXPLODIR = new Color(189, 66, 68);
	private final Color TEXTO_VERDE = new Color(0, 100, 0);
	
	@Experimental
	private final ImageIcon imagemExplosao = new ImageIcon("recursos/icones/explosao5.jpg");
	
	private Campo campo;

	/*Usamos o m�todo da inst�ncia campo registrarObservador(CampoObservador observador) para  
	 * registrar esta classe como um ouvinte na lista de observadores do campo.*/
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		/*setBorder � herdado de JComponent, o BorderFactory � uma classe que possui o m�todo
		 * compartilhado createBevelBorder(int type) que retorna um Border de acordo com o 
		 * n�mero passado, aparentemente s� processa 0, 1 e 2*/
		this.setBackground(BG_PADRAO);
		this.setOpaque(true);
		this.setBorder(BorderFactory.createBevelBorder(0));
		this.addMouseListener(this);
		campo.registrarObservador(this);
	}
	/*Por implementar CampoObservador, precisa sobrescrever o m�todo eventoOcorreu(Campo campo,
	 * CampoEvento evento), nesta vers�o do m�todo, chamamos os m�todos privados da classe de
	 * acordo com a enum passada.
	 * O campo e o evento s�o recebidos e de acordo com o evento passado, � aplicado sobre o
	 * campo o devido efeito visual.
	 * Lembra de quando o campo minado chama o m�todo eventoOcorreu para todos os Campos
	 * Observadores que ele possui, passando ele mesmo de par�metro e o tipo do evento ?
	 * Ent�o, agora os devidos efeitos visuais s�o aplicados.*/
	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch(evento) {
			case ABRIR : 
				this.aplicarEstiloAbrir();
				break;
			case MARCAR :
				this.aplicarEstiloMarcar();
				break;
			case EXPLODIR :
				this.aplicarEstiloExplodir();
				break;
			default : aplicarEstiloPadrao();
			break;
		}
		/*Serve para redesenhar o componente na tela.*/
		SwingUtilities.invokeLater(() -> {
			this.repaint(); // De Component
			this.validate(); //De Container
		});
	}

	private void aplicarEstiloPadrao() {
		// TODO Auto-generated method stub
		this.setBackground(BG_PADRAO);
		this.setBorder(BorderFactory.createBevelBorder(0));
		this.setText("");
	}

	private void aplicarEstiloExplodir() {
		// TODO Auto-generated method stub
		ImageIcon imagemExplosao = new ImageIcon("recursos/icones/explosao.png");
		this.setBackground(BG_EXPLODIR);
		this.setForeground(Color.WHITE);
		this.setText("X");
	}

	private void aplicarEstiloMarcar() {
		// TODO Auto-generated method stub
		this.setBackground(BG_MARCAR);
		this.setForeground(Color.BLACK);
		this.setText("M");
	}

	private void aplicarEstiloAbrir() {
		// TODO Auto-generated method stub
		//Deixa a borda cinza.
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		if(campo.isMinado()) {
			this.setBackground(BG_EXPLODIR);
			return;
		}
		this.setBackground(BG_PADRAO);
		
		switch(campo.minasNaVizinhanca()) {
		
		case 1: 
			/*Seta a cor do texto do campo visual, caso fosse setBackground(), setaria a 
			 * cor do bot�o. Caso seja 1, ser� verde, caso 2 azul, caso 3 amarelo, caso 4, 5 e 6 ser� vermelho, se nenhum
			 * ser� rosa.*/
			this.setForeground(TEXTO_VERDE);
			break;
		case 2:
			this.setForeground(Color.BLUE);
			break;
		case 3:
			this.setForeground(Color.YELLOW);
			break;
		case 4:
		case 5:
		case 6:
			this.setForeground(Color.RED);
			break;
		default:
			this.setForeground(Color.PINK);
		}
		/*Como o m�todo camposNaVizinhanca() retorna um n�mero, concatena-se com uma String
		vazia para haver uma convers�o impl�cita.*/
		String valor = !this.campo.vizinhancaSegura() ? this.campo.minasNaVizinhanca() + "" :
			"";
		//O valor retornado � usado para servir de texto do bot�o.
		this.setText(valor);
		
	}
	
	//Interface dos eventos do mouse. A diferen�a para o ActionListener, � que o bot�o seta
	//um ActionListener que sobrescreve actionPerformed, j� aqui o bot�o � quem implementa a
	//interface MouseListner do pacote java.awt.event.MouseListener.
	
	@Override
	public void mousePressed(MouseEvent e) {
		//0 = nenhum bot�o, 1 = bot�o esquerdo, 2 = bot�o direito.
		if(e.getButton() == 1) this.campo.abrir();
		else if(e.getButton() == 3) campo.alternarMarcacao();
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
