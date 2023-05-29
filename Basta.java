package krtice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Basta extends Panel {
	protected ArrayList<Rupa> rupe = new ArrayList<>();
	protected int kolicina_povrca = 100;
	private int interval_cekanja;
	private int broj_koraka;
	private Thread thread;
	int row;
	int col;
	
	void startBasta() {		
		thread = new Thread(() -> {
			try {
				while (true) {
					int rand; 
					while (true) {
						rand = new Random().nextInt(row*col);
						if (rupe.get(rand).getZivotinja() == null)
							break;
					}
					rupe.get(rand).setZivotinja(new Krtica(rupe.get(rand)));
					rupe.get(rand).createThread();
					rupe.get(rand).startThread();
					interval_cekanja = (int)(interval_cekanja - 0.01*interval_cekanja);
					Thread.sleep(interval_cekanja);
				
				}
			} catch (InterruptedException e) {
				
			}
		});	
		thread.start();
	}
	void stopBasta() {
		thread.interrupt();
	}
	
	public void decrement_kolicina_povrca() {
		kolicina_povrca = kolicina_povrca - 1;
	}
	
	public int getInterval_cekanja() {
		return interval_cekanja;
	}
	public void setInterval_cekanja(int interval_cekanja) {
		this.interval_cekanja = interval_cekanja;
	}
	public int getBroj_koraka() {
		return broj_koraka;
	}
	public void setBroj_koraka(int broj_koraka) {
		this.broj_koraka = broj_koraka;
		for(int i=0; i<row*col; i++) {
			rupe.get(i).setBroj_Koraka(broj_koraka);

		}
	}
	public Basta(int row, int col) {
	//	setSize(500, 500);
		this.row = row;
		this.col = col;
		this.setBackground(Color.GREEN);
		this.setLayout(new GridLayout(row, col, 20, 20));
		for(int i=0; i<row*col; i++) {
			Rupa rupa = new Rupa(this);
			add(rupa);
			rupe.add(rupa);
		}
		
	/*	for(int i=0; i<row*col; i++) {
			rupe.get(i).addMouseListener(new MouseAdapter(){
				
				@Override
				public void mousePressed(MouseEvent e) {
					//to-do
				}
			});
		} */
		
		
	}
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(500, 500);
		Basta b = new Basta(4,4);
		f.add(b);
		f.setVisible(true);
	}
}
