package swing.layout.test;
import javax.swing.JFrame;

import swing.layout.gridBagLayout.GridBagLayoutPanel;
public class GridBagLayoutTest extends JFrame{

	public GridBagLayoutTest() {
		super("GridBagLayoutTest");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(new GridBagLayoutPanel());
		pack();
	}
	public static void main(String[] args) {
		new GridBagLayoutTest().setVisible(true);
	}
}
