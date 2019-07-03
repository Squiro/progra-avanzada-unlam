package com.emmettbrown.entorno.grafico;

import java.awt.Color;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.entidades.Entidad;
import com.emmettbrown.mapa.Ubicacion;

public class JPanelGrafico extends JPanel {

	//private Mapa miMapa;
	private Map<Ubicacion, Entidad> conjuntoEntidades;
	private List<Bomberman> listaBomberman;
	private Cliente cliente;
	private static final long serialVersionUID = 1L;

	public JPanelGrafico(Cliente cliente) {
		this.cliente = cliente;
		conjuntoEntidades = this.cliente.getMapa().getListaEntidades();
		listaBomberman = this.cliente.getMapa().obtenerListaBomberman();
		/*for (Bomberman bomberman : listaBomberman) {
			System.out.println("los ID"+bomberman.getIdBomberman());
		}*/
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
				
		Entidad entidades[] = conjuntoEntidades.values().toArray(new Entidad[0]);
			
		for (int i = 0; i < entidades.length; i++) {
			g.drawImage(entidades[i].getImagen(), entidades[i].getX(), entidades[i].getY(), DefConst.TILESIZE, DefConst.TILESIZE, null);
		}
		
		g.setColor(Color.GREEN);
		
		Bomberman bombermans[] = listaBomberman.toArray(new Bomberman[0]);
		
		for (int i = 0; i < bombermans.length; i++) {
			if (bombermans[i].verSiEsVisible())
			g.drawImage(bombermans[i].getImagen(), bombermans[i].getX(), bombermans[i].getY(), DefConst.DEFAULTWIDTH, DefConst.DEFAULTHEIGHT, null);
		}

	}
}