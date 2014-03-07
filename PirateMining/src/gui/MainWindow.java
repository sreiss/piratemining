package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3596652768909677468L;
	
	private JPanel jpMain = new JPanel();
	private JPanel jpBottom = new JPanel();
	
	public MainWindow() {
		this.setLayout(new BorderLayout());
		
		JLabel jlPir = new JLabel("Ces pirates sont-ils dans la classe ?");
		this.add(jlPir, BorderLayout.NORTH);
		
		jpMain.setLayout(new GridLayout(4,4));
		
		for (int i = 0; i<16; i++) {
			PirateButton pb = new PirateButton();
			jpMain.add(pb);
		}
		
		this.add(jpMain, BorderLayout.CENTER);
		
		jpBottom.setLayout(new FlowLayout());
		
		JButton jbValidate = new JButton("Valider");
		JButton jbClose = new JButton("Fermer");
		jbClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		jpBottom.add(jbValidate);
		jpBottom.add(jbClose);

		this.add(jpBottom, BorderLayout.SOUTH);
		
		
		
		this.setMinimumSize(new Dimension(750, 750));
		this.setSize(750, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		MainWindow m = new MainWindow();
		m.setVisible(true);
	}
}
