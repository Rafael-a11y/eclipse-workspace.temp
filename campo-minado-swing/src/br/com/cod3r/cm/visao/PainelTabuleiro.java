package br.com.cod3r.cm.visao;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import br.com.cod3r.cm.modelo.Tabuleiro;
import br.com.cod3r.cm.visao.BotaoCampo;
public class PainelTabuleiro extends JPanel{

	public PainelTabuleiro(Tabuleiro tabuleiro) {
		
		/*JPanel que tem setado o LayoutManager GridLayout que recebe de par�metro as mesmas
		 * quantidades de linhas e colunas, ou seja, a quantidadede linhas e colunas desta
		 * grid ser� de acordo com as linhas e colunas do tabuleiro.*/
		this.setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));
		
		/*o this aponta para o pr�prio objeto painel que adiciona um BotaoCampo que recebe de par�metro
		 * um Campo. */
		tabuleiro.paraCadaCampo(c -> this.add(new BotaoCampo(c)));
		tabuleiro.registrarObservador(e -> {
			//TODO mostrar resultado para o usu�rio.
			/*A PRINC�PIO N�O VI NENHUMA DIFEREN�A AO ASSOCIAR A CAIXA DE DI�LOGO AO JPANEL. A princ�pio executa a lambda
			 * somente ap�s o m�todo aplicarEstiloExplodir() ser executado, por isso invokeLater, recebe um Runnable que �
			 * uma interface funcional, sobrescreve o run() por uma lambda, sempre em uma lambda o objeto retratado � o mais
			 * interno, neste caso, o m�todo invokeLater(Runnable doRun) recebe um Runnbale an�nimo que tem o run() sobrescrito
			 * por lambda.*/
				SwingUtilities.invokeLater(() -> {
					if(e.isGanhou()) {
						JOptionPane.showMessageDialog(this, "Campos Minados marcados e N�o Minados Abertos", "Vit�ria", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(this, "O Campo Expoldiu", "Derrota :(", JOptionPane.WARNING_MESSAGE);
					}
					tabuleiro.reiniciar();
				});
				
		});
	}
}
