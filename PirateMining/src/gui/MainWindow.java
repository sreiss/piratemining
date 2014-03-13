package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import mining.Pirate;

public class MainWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3596652768909677468L;
	
	private ArrayList<PirateButton> buttons = new ArrayList<PirateButton>();
	
	public MainWindow() {
		JPanel jpMain = new JPanel();
		JPanel jpBottom = new JPanel();
		
		this.setLayout(new BorderLayout());
		
		JLabel jlPir = new JLabel("Ces pirates sont-ils dans la classe ?");
		jlPir.setHorizontalAlignment(JLabel.CENTER);
		this.add(jlPir, BorderLayout.NORTH);
		
		jpMain.setLayout(new GridLayout(4,5));
		
		
		for (int i = 0; i<20; i++) {
			boolean bool = true;
			PirateButton pb = null;
			while (bool) {
				Pirate p = Pirate.randomPirate();
				pb = new PirateButton(p);
				if (!this.contains(pb)) {
					bool = false;
				}
			}
			buttons.add(pb);
			jpMain.add(pb);
		}
		
		this.add(jpMain, BorderLayout.CENTER);
		
		jpBottom.setLayout(new FlowLayout());
		
		JButton jbValidate = new JButton("Valider");
		JButton jbClose = new JButton("Fermer");
		jpBottom.add(jbValidate);
		jpBottom.add(jbClose);

		this.add(jpBottom, BorderLayout.SOUTH);
		
		this.setMinimumSize(new Dimension(1000, 750));
		this.setSize(1000, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jbClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	public boolean contains(PirateButton pb) {
		for (PirateButton pbt : buttons) {
			if (pbt.equals(pb)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		try {
	        UIManager.setLookAndFeel(
	        		UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) { }
	    catch (ClassNotFoundException e) { }
	    catch (InstantiationException e) { }
	    catch (IllegalAccessException e) { }
		
		MainWindow m = new MainWindow();
		m.setVisible(true);
	}
}
