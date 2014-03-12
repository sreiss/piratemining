package gui;

import javax.swing.JToggleButton;

import mining.Pirate;

public class PirateButton extends JToggleButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2881459107854859789L;
	
	private Pirate pir;

	public PirateButton(Pirate p) {
		PirateIcon pi = new PirateIcon(p);
		this.setIcon(pi.getImageIcon());
		pir = p;
	}
	
	public Pirate getPirate() {
		return pir;
	}
	
	@Override
	public boolean equals (Object obj) {
		if (obj instanceof PirateButton) {
			PirateButton pb = (PirateButton)obj;
			if (!pb.getPirate().equals(this.getPirate())) {
				return false;
			}
			return true;
		}
		return false;
	}

}
