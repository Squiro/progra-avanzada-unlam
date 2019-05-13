package com.emmettbrown.entorno.grafico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.entidades.Entidad;
import com.emmettbrown.entidades.Muro;
import com.emmettbrown.entidades.Obstaculo;
import com.emmettbrown.mapa.Mapa;
import com.emmettbrown.mapa.Ubicacion;
import com.sun.javafx.geom.RoundRectangle2D;

public class JPanelGrafico extends JPanel {

	Mapa miMapa;
	Map<Ubicacion, Entidad> conjuntoEntidades;
	List<Bomberman> listaBomberman;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPanelGrafico(Mapa miMapa) {
		this.miMapa = miMapa;
		conjuntoEntidades = miMapa.obtenerListaEntidades();
		listaBomberman = miMapa.obtenerListaBomberman();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Iterator<Ubicacion> iterEnt = conjuntoEntidades.keySet().iterator();
		Iterator<Bomberman> iterBomb = listaBomberman.iterator();
		Bomberman mostBomb;
		Ubicacion ubic;
		Entidad mostEnt;
		while (iterEnt.hasNext()) {
			ubic = iterEnt.next();
			mostEnt = conjuntoEntidades.get(ubic);
			if (mostEnt instanceof Muro) {
				g.setColor(Color.RED);
				g.fillRect((int) ubic.getPosX() * 75, (int) ubic.getPosY() * 75, 75, 75);
			}
			if (mostEnt instanceof Obstaculo) {
				g.setColor(Color.BLUE);
				g.fillRect((int) ubic.getPosX() * 75, (int) ubic.getPosY() * 75, 75, 75);
			}

			
		}
		g.setColor(Color.GREEN);
		while (iterBomb.hasNext()) {
			mostBomb = iterBomb.next();
			ubic = mostBomb.obtenerUbicacion();
			g.fillOval((int) ubic.getPosX() * 75, (int) ubic.getPosY() * 75, 75, 75);
		}

	}
}
