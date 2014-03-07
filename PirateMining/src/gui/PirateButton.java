package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PirateButton extends JButton {
	
	
	public PirateButton() {
		this.setBackground(Color.RED);
		//this.addActionListener(new ActionListener());
	}
	
	public void changeBackground() {
		if (this.getBackground() == Color.RED) {
			this.setBackground(Color.GREEN);
		} else if (this.getBackground() == Color.GREEN) {
			this.setBackground(Color.RED);
		}
	}
	
	
}
