package krtice;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja {

//	int x, y;
	
	public Krtica(Rupa r) {
		super(r);
//		x = r.getWidth();
//		y= r.getHeight();
	}
	
	@Override
	public void paint(Graphics g) {
//		g.setColor(Color.DARK_GRAY);
//		g.fillOval(x, y, rupa.getWidth(), rupa.getHeight());
		g.setColor(Color.DARK_GRAY);
	//	g.fillOval(0 + (this.getWidth() - this.getWidth() * brojac_koraka/owner.getBroj_koraka())/2, 
	//			0 + (this.getHeight() - this.getHeight() * brojac_koraka/owner.getBroj_koraka())/2, 
	//			this.getHeight() * brojac_koraka/owner.getBroj_koraka(), 
	//			this.getWidth() * brojac_koraka /owner.getBroj_koraka());
		if (rupa.brojac_koraka == -1) {
			g.setColor(Color.decode("#864500"));
			g.fillRect(0, 0, rupa.getWidth(), rupa.getHeight());
		return;	
		}
		if (rupa.owner.getBroj_koraka() != 0) {
		g.setColor(Color.DARK_GRAY);
		g.fillOval(0 + (rupa.getWidth() - rupa.getWidth() * (rupa.owner.getBroj_koraka() - rupa.brojac_koraka)/rupa.owner.getBroj_koraka())/2, 
				0 + (rupa.getHeight() - rupa.getHeight() * (rupa.owner.getBroj_koraka() - rupa.brojac_koraka)/rupa.owner.getBroj_koraka())/2, 
				rupa.getHeight() * (rupa.owner.getBroj_koraka() - rupa.brojac_koraka)/rupa.owner.getBroj_koraka(), 
				rupa.getWidth() * (rupa.owner.getBroj_koraka() - rupa.brojac_koraka)/rupa.owner.getBroj_koraka());
		}
	
	}

	@Override
	void Hit() {
			rupa.brojac_koraka = rupa.owner.getBroj_koraka();
			rupa.stopThread();
			rupa.paintEmpty(rupa.getGraphics());
			rupa.repaint();
	}

	@Override
	void Runaway() {
		rupa.owner.decrement_kolicina_povrca();
		// TODO Auto-generated method stub
		
	}

}
