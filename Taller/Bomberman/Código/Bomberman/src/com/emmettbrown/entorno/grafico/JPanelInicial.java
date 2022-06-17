package com.emmettbrown.entorno.grafico;

import java.awt.Graphics;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;


public class JPanelInicial extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<Sala> lstSalas;
	private DefaultListModel<Sala> df;
	private ConcurrentLinkedQueue<Sala> salasCreadas;
	
	public JPanelInicial(ConcurrentLinkedQueue<Sala> salasCreadas) {
		this.salasCreadas = salasCreadas;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int index = lstSalas.getSelectedIndex();
		super.paintComponent(g);
		df.clear();
		for (Sala sala : salasCreadas) {
			df.addElement(sala);
		}
		lstSalas.setSelectedIndex(index);
	}
	
	public void setDefaultModel(DefaultListModel<Sala> df) {
		this.df = df;
	}
	
	public void setLstSalas(JList<Sala> lstSalas) {
		this.lstSalas = lstSalas;
	}

	/*public void refrescarListaSalas() {		
		df.clear();
		for (Sala sala : cliente.getListaSalas()) {
			df.addElement(sala);
		}
	}*/

}
