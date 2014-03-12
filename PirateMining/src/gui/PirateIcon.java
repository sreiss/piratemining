package gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mining.Pirate;

public class PirateIcon {

	private ImageIcon ii;
	
	public PirateIcon(Pirate p) {
		
		HashMap<String, Boolean> h = p.getTable();
		BufferedImage imgBase = null;
		try {
			imgBase = ImageIO.read(new File("img\\base.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		BufferedImage bi = null;
		for (Map.Entry<String, Boolean> entry : h.entrySet()) {
			if (entry.getValue()) {
				BufferedImage img2 = null;
				try {
					img2 = ImageIO.read(new File("img\\"+entry.getKey()+".png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				bi = PirateIcon.addImage(imgBase, img2);
			}
		}
		
		
		/**/
		
//		BufferedImage img = null;
//		BufferedImage img2 = null;
//		try {
//			img = ImageIO.read(new File("img\\iutblanc.png"));
//			img2 = ImageIO.read(new File("img\\ss4s.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		BufferedImage bi = PirateIcon.addImage(img, img2);


		Image newimg = bi.getScaledInstance(160, 160,  java.awt.Image.SCALE_SMOOTH);  
		this.ii = new ImageIcon(newimg);
	}
	
	public ImageIcon getImageIcon() {
		return this.ii;
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
	
	public String randomImage() {
		Random rnd = new Random();
		String s = "";
		for (int i = 0; i<5; i++) {
			s += rnd.nextInt(1);
		}
		s += ".png";
		return s;
	}
}
