package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class PirateButton extends JCheckBox {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2239474966130365554L;

	public PirateButton() {
		ImageIcon icon = new ImageIcon("D:\\Utilisateurs\\Fred\\Mes documents\\Divers\\Chrome-extension\\newtab\\img\\iutbleu.png");
		Image img = icon.getImage();  
		Image newimg = img.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon newIcon = new ImageIcon(newimg);
		
		this.setIcon(newIcon);
		this.setBackground(Color.RED);
		this.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				changeBackground();
			}
		});
	}
	
	public void changeBackground() {
		if (this.isSelected() == true) {
			this.setBackground(Color.GREEN);
		} else {
			this.setBackground(Color.RED);
		}
	}
	
	
}
