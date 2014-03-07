package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class MainWindow extends JFrame {
	
	private JPanel jpMain = new JPanel();
	private JPanel jpBottom = new JPanel();
//	private JButton jbValidate = new JButton("Valider");
//	private JButton jbClose = new JButton("Fermer");
	
	public MainWindow() {
		this.setLayout(new BorderLayout());
		
		JLabel jlPir = new JLabel("Ces pirates sont-ils dans la classe ?");
		this.add(jlPir, BorderLayout.NORTH);
		
		jpMain.setLayout(new GridLayout());
		
		PirateButton pb = new PirateButton();
		jpMain.add(pb);
		
		this.add(jpMain, BorderLayout.CENTER);
		
		this.setMinimumSize(new Dimension(300, 300));
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		MainWindow m = new MainWindow();
		m.setVisible(true);
	}
}
