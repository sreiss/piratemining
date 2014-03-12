package gui;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import mining.Pirate;

public class PirateButton extends JToggleButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2239474966130365554L;
	
	private String attributes;

	public PirateButton(Pirate p) {
		PirateIcon pi = new PirateIcon(p);
		this.setIcon(pi.getImageIcon());
	}

}
