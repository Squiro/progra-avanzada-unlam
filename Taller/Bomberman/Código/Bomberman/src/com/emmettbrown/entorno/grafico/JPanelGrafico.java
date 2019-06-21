package com.emmettbrown.entorno.grafico;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.entidades.DefConst;
import com.emmettbrown.entidades.Entidad;
import com.emmettbrown.mapa.Mapa;
import com.emmettbrown.mapa.Ubicacion;

public class JPanelGrafico extends JPanel {

	Mapa miMapa;
	Map<Ubicacion, Entidad> conjuntoEntidades;
	List<Bomberman> listaBomberman;
	//private Bomberman miBomber;
	private Cliente cliente;
	private static final long serialVersionUID = 1L;

	public JPanelGrafico(Mapa miMapa, Cliente cliente) {
		this.miMapa = miMapa;
		//this.miBomber = b;
		this.cliente = cliente;
		conjuntoEntidades = miMapa.obtenerListaEntidades();
		listaBomberman = miMapa.obtenerListaBomberman();
	}

	public void paintComponent(Graphics g) {
		/*if (listaBomberman.size() < 1)
			return;*/
		
		super.paintComponent(g);
				
		Iterator<Ubicacion> iterEnt = conjuntoEntidades.keySet().iterator();
		
		while (iterEnt.hasNext()) {
			Ubicacion ubic = iterEnt.next();
			Entidad mostEnt = conjuntoEntidades.get(ubic);			
			g.drawImage(mostEnt.getImagen(), mostEnt.getX(), mostEnt.getY(), DefConst.TILESIZE, DefConst.TILESIZE, null);
		}
		
		g.setColor(Color.GREEN);
		
		//Iterator<Bomberman> iterBomb = listaBomberman.iterator();
		//Bomberman mostBomb = cliente.getBomber();
		
		//if (mostBomb.verSiEsVisible() == true) g.drawImage(mostBomb.getImagen(), mostBomb.getX(), mostBomb.getY(), DefConst.DEFAULTWIDTH, DefConst.DEFAULTHEIGHT, null);
		System.out.println("asd");
		/*while (iterBomb.hasNext()) {
			Bomberman bomber = iterBomb.next();
			if (bomber.verSiEsVisible() == true) {
				g.drawImage(bomber.getImagen(), bomber.getX(), bomber.getY(), DefConst.DEFAULTWIDTH, DefConst.DEFAULTHEIGHT, null);
			}
		}*/
	}

}
