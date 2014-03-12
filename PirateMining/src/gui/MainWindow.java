package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mining.Pirate;

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
		
		jpMain.setLayout(new GridLayout(4,5));
		
		for (int i = 0; i<20; i++) {
			PirateButton pb = new PirateButton(new Pirate());
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
		
		this.setMinimumSize(new Dimension(1250, 1000));
		this.setSize(1250, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		
		try {
	            // Set System L&F
	        UIManager.setLookAndFeel(
	        		UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	      
	    }
	    catch (ClassNotFoundException e) {
	      
	    }
	    catch (InstantiationException e) {
	      
	    }
	    catch (IllegalAccessException e) {
	      
	    }
		
		MainWindow m = new MainWindow();
		m.setVisible(true);
	}
}
