package com.emmettbrown.entorno.grafico;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JPanel;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.entidades.Entidad;
import com.emmettbrown.mapa.Ubicacion;

public class JPanelGrafico extends JPanel {

	private Map<Ubicacion, Entidad> conjuntoEntidades;
	private List<Bomberman> listaBomberman;
	private Cliente cliente;
	private Sala sala;
	private static final long serialVersionUID = 1L;

	public JPanelGrafico(Cliente cliente) {
		this.cliente = cliente;
		conjuntoEntidades = this.cliente.getMapa().getListaEntidades();
		listaBomberman = this.cliente.getMapa().obtenerListaBomberman();
		this.sala = cliente.getSalaActual();
		cliente.setPanelGrafico(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Dibujamos las entidades del mapa
		Entidad entidades[] = conjuntoEntidades.values().toArray(new Entidad[0]);			
		for (int i = 0; i < entidades.length; i++) {
			g.drawImage(entidades[i].getImagen(), entidades[i].getX(), entidades[i].getY(), DefConst.TILESIZE, DefConst.TILESIZE, null);
		}
		
		g.setColor(Color.BLACK);
		//g.setColor(Color.GREEN);
		
		//Dibujamos los bombermans con sus nombres
		Bomberman bombermans[] = listaBomberman.toArray(new Bomberman[0]);
		for (int i = 0; i < bombermans.length; i++) {
			if (bombermans[i].verSiEsVisible()) {		
				g.drawString(bombermans[i].getNombre(), bombermans[i].getX()+DefConst.DXNAME, bombermans[i].getY()+DefConst.DYNAME);
				g.drawImage(bombermans[i].getImagen(), bombermans[i].getX(), bombermans[i].getY(), DefConst.DEFAULTWIDTH, DefConst.DEFAULTHEIGHT, null);
			}
		}
		int dy = 0;
				
		//Dibujamos el texto de las rondas
        g.setFont(new Font("Monospaced", Font.BOLD, 36));
		g.drawString(DefConst.TITLETAB, 750, 30);
		g.drawString("Ronda: "+ sala.getRondaActual(), 750, 55);
		
		//Dibujamos el tablero con puntajes
		for (Entry<String, Integer> entry : sala.getTableroPuntos().getPuntajes().entrySet()) {
			g.drawString(entry.getKey()+": "+ entry.getValue(), 680, 85+dy);
			dy+=30;
		}
		
		//Dibujamos el reloj que muestra el tiempo de ronda
		g.drawString(sala.getReloj().toString(), 760, 9*75-75);
	}
	
	public void actualizarListas() {
		this.conjuntoEntidades = this.cliente.getMapa().getListaEntidades();
		this.listaBomberman = this.cliente.getMapa().obtenerListaBomberman();
	}
}