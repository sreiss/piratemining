package gui;

//import java.awt.Color;
import java.awt.Image;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class PirateButton extends JToggleButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2239474966130365554L;
	
	private String name;

	public PirateButton() {
		ImageIcon icon = new ImageIcon("img\\iutblanc.png");
		Image img = icon.getImage();  
		Image newimg = img.getScaledInstance(160, 160,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon newIcon = new ImageIcon(newimg);
		
		this.setIcon(newIcon);
//		this.setBackground(Color.RED);
//		this.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent arg0) {
//				changeBackground();
//			}
//		});
	}
	
	public String randomImage() {
		Random rnd = new Random();
		String s = "";
		for (int i = 0; i<5; i++) {
			s += rnd.nextInt(1);
		}
		s += ".png";
		return s;
	}
	
//	public void changeBackground() {
//		if (this.isSelected() == true) {
//			this.setBackground(Color.GREEN);
//		} else {
//			this.setBackground(Color.RED);
//		}
//	}
	
	
}
