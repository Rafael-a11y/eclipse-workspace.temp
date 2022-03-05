package br.com.cod3r.calc.visao;
import java.awt.Color;
import br.com.cod3r.calc.modelo.MemoriaObservador;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
/*Implementar o tratamento da fonte dos d�gitos para estes reduzirem o tamanho.
 * Implementar o bot�o de porcentagem.
 * Implementar o bot�o de altern�ncia entre sinalm de positivos e negativos.*/
import javax.swing.JPanel;

import br.com.cod3r.calc.modelo.Memoria;
@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador{

	private final JLabel label;
	public Display() {
		Memoria.getInstancia().adicionarObservador(this);
		//Seta a cor.
		this.setBackground(new Color(46,49,50));
		this.label = new JLabel(Memoria.getInstancia().getTextoAtual())	;
		//Seta a cor da fonte.
		this.label.setForeground(Color.WHITE);
		///Seta a fonte. Os par�metros s�o: nome da fonte, estilo e tamanho.
		this.label.setFont(new Font("dialog", Font.ROMAN_BASELINE, 40));
		//Cria o FlowLayout para adicionar o JLabel a direita, com espa�amentos horizontais e
		//verticais.
		this.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		this.add(label);
		
	}
	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
	}
}
