package swing.layout.gridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Dimension;


/*Relembrando umm conceito básico: ao setar um layout manager, o painel irá adicionar o componente
 * da maneira que o layout preferir.*/
public class GridBagLayoutPanel extends JPanel{
	
	private GridBagLayout layout;
	int opcao = 4;
	public GridBagLayoutPanel() {
		if(opcao == 1) initialize();
		else if(opcao == 2) initialize2();
		else if(opcao == 3) initialize3();
		else if(opcao == 4) initialize4();
	}
	public void initialize() {
		layout = new GridBagLayout();
		this.setLayout(layout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new JButton("Primeiro"), gbc); //Coluna 0, linha 0;
		
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		this.add(new JButton("Segundo"), gbc2); //Coluna 1, linha 0;
		
		GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.gridx = 2;
		gbc3.gridy = 0;
		this.add(new JButton("Terceiro"), gbc3); //Coluna 2, liinha 0
	}
	
	public void initialize2() {
		layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new JButton("Primeiro"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(new JButton("Segundo"), gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(new JButton("Terceiro"), gbc);
	}
	
	
	public void initialize3() {
		layout = new GridBagLayout();
		setLayout(layout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = GridBagConstraints.RELATIVE;
		gbc.gridy = GridBagConstraints.RELATIVE;
		
		add(new JButton("Primeiro"), gbc);
		add(new JButton("Segundo"), gbc);
		add(new JButton("Terceiro"), gbc);
	}
	
	public void initialize4() {
		setPreferredSize(new Dimension(400, 150));
		layout = new GridBagLayout(); //Instancia o GridBagLayout.
		setLayout(layout); //Seta o layout.
		
		GridBagConstraints gbc = new GridBagConstraints(); //Instancia uma GridBagConstraints
		gbc.gridwidth = 1; //Especifica que o componente ocupará uma célula de largura.
		gbc.gridheight = 1; //Especifica que o componente ocupará uma célula de altura.
		
		add(new JButton("Primeiro"), gbc); //Ocupa uma cécula;
		
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridwidth = 1;
		gbc2.gridheight = 1;
		
		add(new JButton("Segundo"), gbc2); //Ocupa uma cécula;
		
		GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.gridwidth = 1;
		gbc3.gridheight = GridBagConstraints.REMAINDER;
		
		add(new JButton("Terceiro"), gbc3); //Ocupa uma cécula e é o ultimo componente da linha;
		
		GridBagConstraints gbc4 = new GridBagConstraints();
		gbc4.gridwidth = 2;
		gbc4.gridheight = 1;
		
		add(new JButton("Quarto"), gbc4); //Ocupa duas céculas na horizontal;
		
		GridBagConstraints gbc5 = new GridBagConstraints();
		gbc5.gridwidth = GridBagConstraints.REMAINDER;
		gbc5.gridheight = 1;
		
		add(new JButton("Quinto"), gbc5); //Ocupa uma cécula e é o último componente da linha;
		
	}
}
