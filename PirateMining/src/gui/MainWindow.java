package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import mining.Mining;
import mining.Pirate;

public class MainWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3596652768909677468L;
	
	private ArrayList<PirateButton> buttons = new ArrayList<PirateButton>();
	private JPanel jpMain = new JPanel();
	
	public MainWindow() {
		// Setting app icon 
		ArrayList<Image> appIcons = new ArrayList<Image>();
		appIcons.add(new ImageIcon("img/app_icon/16x16.png").getImage());
		appIcons.add(new ImageIcon("img/app_icon/32x32.png").getImage());
		appIcons.add(new ImageIcon("img/app_icon/64x64.png").getImage());
		appIcons.add(new ImageIcon("img/app_icon/128x128.png").getImage());
		appIcons.add(new ImageIcon("img/app_icon/256x256.png").getImage());
		this.setIconImages(appIcons);
		
		JPanel jpBottom = new JPanel();
		
		this.setLayout(new BorderLayout());
		
		JLabel jlPir = new JLabel("Ces pirates sont-ils dans la classe ?");
		jlPir.setHorizontalAlignment(JLabel.CENTER);
		this.add(jlPir, BorderLayout.NORTH);
		
		this.jpMain.setLayout(new GridLayout(4,5));
		
		this.randomPirateButtons(20);
		
		this.add(this.jpMain, BorderLayout.CENTER);
		
		jpBottom.setLayout(new FlowLayout());
		
		JButton jbValidate = new JButton("Valider");
		JButton jbReinit = new JButton("Réinitialiser");
		JButton jbRegen = new JButton("Régénérer");
		JButton jbClose = new JButton("Fermer");
		jpBottom.add(jbValidate);
		jpBottom.add(jbReinit);
		jpBottom.add(jbRegen);
		jpBottom.add(jbClose);

		this.add(jpBottom, BorderLayout.SOUTH);
		
		this.setMinimumSize(new Dimension(1000, 750));
		this.setSize(1000, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		jbClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		jbRegen.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.buttons.clear();
				MainWindow.this.jpMain.removeAll();
				MainWindow.this.randomPirateButtons(20);
			}
		});
		
		jbReinit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (PirateButton pb : buttons) {
					pb.setSelected(false);
				}
			}
		});
		
		jbValidate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Affichage d'un message d'erreur si aucun pirate n'est sélectionné
				ArrayList<Pirate> al = new ArrayList<Pirate>();
				int nbSelected = 0;
				for (PirateButton pb : buttons) {
					Pirate p = pb.getPirate();
					p.setOk(pb.isSelected());
					if(pb.isSelected())
						nbSelected++;
					al.add(p);
				}
				if (nbSelected == 0) {
					JOptionPane.showMessageDialog(
							MainWindow.this, 
							"Vous n'avez rien sélectionné, moussaillon !", 
							"Arrrrr !", 
							JOptionPane.WARNING_MESSAGE
					);
				} else {
					Mining m = new Mining();
					m.setPirates(al);
					m.evaluate();
					
					if (!m.isRepresentative()) {
						JOptionPane.showMessageDialog(
							MainWindow.this,
							"Les sélection n'est pas représentative.",
							"Aïe, Aïe, Aïe...",
							JOptionPane.WARNING_MESSAGE
						);
					}
				}
			}
		});
	}
	
	public void randomPirateButtons(int n) {
		for (int i = 0; i<n; i++) {
			boolean bool = true;
			PirateButton pb = null;
			while (bool) {
				Pirate p = Pirate.randomPirate();
				pb = new PirateButton(p);
				if (!this.contains(pb)) {
					bool = false;
				}
			}
			this.buttons.add(pb);
			this.jpMain.add(pb);
		}
		this.jpMain.updateUI();
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
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e)
		{
			try {
			
		        UIManager.setLookAndFeel(
		        		UIManager.getSystemLookAndFeelClassName());
			} catch(Exception e2)
			{
			
			}
	    } 

		
		MainWindow m = new MainWindow();
		m.setVisible(true);
	}
}
