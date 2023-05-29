package krtice;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rupa extends Canvas {
	protected Basta owner;
	private Zivotinja zivotinja;
	private Thread threadRupe;
	int brojac_koraka = 0;
	
	
	public int getBrojac_Koraka() {
		return brojac_koraka;
	}

	public void setBroj_Koraka(int broj_Koraka) {
		this.brojac_koraka = broj_Koraka;
	}

	public Rupa(Basta basta) {
		owner = basta;
		addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (zivotinja != null) {
				zivotinja.Hit();
				zivotinja=null;
				repaint();
				}
			}
		});
		this.setBackground(Color.decode("#864500"));;
		this.threadRupe = null;
		this.zivotinja = null;
		
	}
	
	void createThread() {
		threadRupe = new Thread(() -> {
			try {
				synchronized (this) {
				while (true) {
				
				brojac_koraka = brojac_koraka - 1;
				if (brojac_koraka == -1) {
				Thread.sleep(2000);
				zivotinja.Runaway();
				zivotinja=null;
				this.brojac_koraka = owner.getBroj_koraka();
				
				
				if (Igra.getIgra().basta.kolicina_povrca == 0) {
					Igra.getIgra().StopGame();
				} 
				Igra.getIgra().povrce.setText("Povrce: " + Igra.getIgra().basta.kolicina_povrca);
				stopThread();	
				}
				repaint();	
				Thread.sleep(100);
				
				}
				}
			} catch (InterruptedException e) {
				
			}
		});
	}
	void startThread() {
		threadRupe.start();
	}
	void stopThread() {
		threadRupe.interrupt();
	}
	
	boolean isAlive() {
		return threadRupe.isAlive();
	}
	
	
	public Zivotinja getZivotinja() {
		return zivotinja;
	}


	public void setZivotinja(Zivotinja zivotinja) {
		this.zivotinja = zivotinja;
	}
	public void paintEmpty(Graphics g) {
		g.setColor(Color.decode("#864500"));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	@Override
	public void paint(Graphics g) {	
		if (zivotinja != null) {
		zivotinja.paint(g);
		}
	}
		
//	if (this.zivotinja != null) {
	//	if (this.zivotinja instanceof Krtica) {
	//	zivotinja.paint(getGraphics());
	//	}
//	}
//	g.setColor(Color.decode("#864500"));
//	g.fillRect(0, 0, this.getWidth(), this.getHeight());
	
}
