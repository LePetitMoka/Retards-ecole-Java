package vue;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class P_Principal extends JPanel {
	public P_Principal(Color uneCouleur) {
		this.setBounds(0, 40, 780, 430);
		this.setBackground(uneCouleur);
		this.setLayout(null);
		this.setVisible(false);
	}
}
