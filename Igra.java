package krtice;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {

	protected Basta basta;
	protected Panel controlPanel = new Panel(new GridLayout(2, 1));
	protected Label tezina = new Label("Tezina:", Label.CENTER);
	protected Button Play = new Button("Kreni");
	protected Label povrce = new Label("Povrce: ", Label.CENTER);
	private int rezimPlay = 1; // 1 - Natpis je Kreni, 2 - Natpis je Stani
// Label brojPovrca = new Label("2", Label.CENTER);
	
	public void populateWindow() {
		povrce.setText("Povrce: " + basta.kolicina_povrca);
		Panel top = new Panel(new GridLayout(5, 1));
		Panel bot = new Panel(new GridLayout(1,1));
		tezina.setFont(new Font("Arial", Font.BOLD, 16));
		povrce.setFont(new Font("Arial", Font.BOLD, 24));
	//	brojPovrca.setFont(new Font("Arial", Font.BOLD, 24));
		CheckboxGroup tezinaGroup = new CheckboxGroup();
		Checkbox lakoCB = new Checkbox("Lako", true, tezinaGroup);
		Checkbox srednjeCB = new Checkbox("Srednje", false, tezinaGroup);
		Checkbox teskoCB = new Checkbox("Tesko", false, tezinaGroup);
		top.add(tezina);
		top.add(lakoCB);
		top.add(srednjeCB);
		top.add(teskoCB);
		top.add(Play);
		bot.add(povrce, BorderLayout.CENTER);
	//	bot.add(brojPovrca);
		controlPanel.add(top);
		controlPanel.add(bot);
		add(controlPanel, BorderLayout.EAST);
		add(basta, BorderLayout.CENTER);
		Play.addActionListener((ae) ->{
			if (rezimPlay == 1) {
				
				if (lakoCB.getState()) {
					basta.setInterval_cekanja(1000);
					basta.setBroj_koraka(10);
					basta.startBasta();
				}
				if (srednjeCB.getState()) {
					basta.setInterval_cekanja(750);
					basta.setBroj_koraka(8);
					basta.startBasta();
				}
				if (teskoCB.getState()) {
					basta.setInterval_cekanja(500);
					basta.setBroj_koraka(6);
					basta.startBasta();
				}
			rezimPlay = 2;
			Play.setLabel("Stani");
			lakoCB.setEnabled(false);
			srednjeCB.setEnabled(false);
			teskoCB.setEnabled(false);
			} else if (rezimPlay == 2) {
				for(int i=0; i<basta.row*basta.col; i++) {
					if (basta.rupe.get(i).getZivotinja() != null) {
						basta.rupe.get(i).setZivotinja(null);
						basta.rupe.get(i).brojac_koraka = basta.rupe.get(i).owner.getBroj_koraka();
						basta.rupe.get(i).stopThread();
					basta.rupe.get(i).paintEmpty(basta.rupe.get(i).getGraphics());
					}
				}
				basta.stopBasta();
				rezimPlay = 1;
				Play.setLabel("Kreni");
				lakoCB.setEnabled(true);
				srednjeCB.setEnabled(true);
				teskoCB.setEnabled(true);
			} 
		});
		
		
	}
	public Igra(Basta basta) {
		this.basta = basta;
	
		setVisible(true);
		setResizable(false);
		setSize(620, 500);
		setTitle("Igra");
		populateWindow();
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
			}
		});
		
	}
	
	private static Igra igra = new Igra(new Basta(4, 4));
	public static Igra getIgra() {
		return igra;
	}
	
	public void StopGame() {
		for(int i=0; i<basta.row*basta.col; i++) {
			if (basta.rupe.get(i).getZivotinja() != null) {
				basta.rupe.get(i).setZivotinja(null);
				basta.rupe.get(i).brojac_koraka = basta.rupe.get(i).owner.getBroj_koraka();
				basta.rupe.get(i).stopThread();
				basta.rupe.get(i).paintEmpty(basta.rupe.get(i).getGraphics());
			}
		}
		basta.stopBasta();
		Play.setEnabled(false);
	}
	
	public static void main(String[] args) {
	}
}
