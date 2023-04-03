package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class V_Conf extends JFrame implements ActionListener, KeyListener {
	private JButton Connexion = new JButton("Se Connecter");
	private JTextField txtHost = new JTextField();
	private JTextField txtBdd = new JTextField();
	private JTextField txtUser = new JTextField();
	private JTextField txtMdp = new JTextField();
	private JPanel panel = new JPanel();
	
	V_Conf(){
		this.setTitle("Configuration de la Base de Données");
		this.setBounds(100, 50, 600, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(GREI.color1);
		this.setLayout(null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
