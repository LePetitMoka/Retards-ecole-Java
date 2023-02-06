package vue;

import java.awt.Color;

import javax.swing.JPanel;

public class P_Principal extends JPanel {
	public P_Principal(Color uneCouleur) {
		this.setBounds(30, 80, 830, 300);
		this.setBackground(uneCouleur);
		this.setLayout(null);
		this.setVisible(false);
	}
}
