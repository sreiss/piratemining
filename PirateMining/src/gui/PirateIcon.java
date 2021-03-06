package gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mining.Pirate;

public class PirateIcon {

	private ImageIcon icon;
	
	public PirateIcon(Pirate p) {
		
		HashMap<String, Boolean> h = p.getTable();
		BufferedImage imgBase = null;
		try {
			imgBase = ImageIO.read(getClass().getResource("/img/base.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		for (Map.Entry<String, Boolean> entry : h.entrySet()) {
			if (entry.getValue() && !entry.getKey().equals("chapeau")) {
				BufferedImage img2 = null;
				try {
					img2 = ImageIO.read(getClass().getResource("/img/"+entry.getKey()+".png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				imgBase = PirateIcon.addImage(imgBase, img2);
			}
			if (entry.getKey().equals("chapeau")) {
				BufferedImage img2 = null;
				if (entry.getValue()) {
					try {
						img2 = ImageIO.read(getClass().getResource("/img/chapeau_noir.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						img2 = ImageIO.read(getClass().getResource("/img/chapeau_rouge.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				imgBase = PirateIcon.addImage(imgBase, img2);
			}
		}

		Image newimg = imgBase.getScaledInstance(190, 190, Image.SCALE_SMOOTH);  
		this.icon = new ImageIcon(newimg);
	}
	
	public ImageIcon getImageIcon() {
		return this.icon;
	}
	
	public static BufferedImage addImage(BufferedImage image1, BufferedImage image2){
		Graphics2D g2d = image1.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
		                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	 
		g2d.drawImage(image2, 0, 0, null);
	 
		g2d.dispose();
	 
		return image1 ;
	}
}
