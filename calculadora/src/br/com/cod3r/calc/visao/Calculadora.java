package br.com.cod3r.calc.visao;
/*Implementar a pr�pria barra com as opera��es de fechar, maximizar e minimizar. */
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
@SuppressWarnings("serial")
public class Calculadora extends JFrame{

	Calculadora() {
		
		super("Calculadora");
		this.organizarLayout();
//		this.setUndecorated(true);//Remove a barra, o que eu n�o acho recomend�vel fazer na janela principal, pois remove os
		//bot�es de minimiza��o, maximiza��o e encerramento do programa. Chama setResizable(true) na sua implementa��o.
		this.setSize(232, 322);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.ORANGE);
		this.setResizable(true);
		this.setVisible(true);
		
	}
	
	private void organizarLayout() {
		this.setLayout(new BorderLayout());
		Display display = new Display();
		Teclado teclado = new Teclado();
		
		display.setPreferredSize(new Dimension(232, 60));
		
		this.getContentPane().add(display, BorderLayout.NORTH);
		this.getContentPane().add(teclado,BorderLayout.CENTER);
		
	}

	public static void main(String[] args) {
		new Calculadora();
	}
	
}
