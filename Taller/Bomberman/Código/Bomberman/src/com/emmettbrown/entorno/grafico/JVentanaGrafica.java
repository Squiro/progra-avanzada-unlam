package com.emmettbrown.entorno.grafico;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import com.emmettbrown.controles.Teclado;
import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.mapa.Mapa;

public class JVentanaGrafica extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanelGrafico contentPane;
	private Teclado teclado;
	private Bomberman[] bomberman;
	private Mapa miMapa;

	public JVentanaGrafica(Mapa miMapa, int ancho, int alto) {
		super("Bomberman");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, ancho, alto);
		setBackground(Color.RED);
		setLocationRelativeTo(null);
		contentPane = new JPanelGrafico(miMapa);
		setBackground(Color.WHITE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		List<Bomberman> miLista = miMapa.obtenerListaBomberman();
		bomberman = miLista.toArray(new Bomberman[miLista.size()]);
		this.miMapa = miMapa;
		this.teclado = new Teclado();
		addKeyListener(teclado);
	}

	public void refrescar() {
		if (bomberman[0].verSiEsVisible()) {

			if (this.teclado.isArriba()) {
				miMapa.moverBombermanArriba(bomberman[0], Bomberman.VELOCIDAD);
				this.teclado.setArriba(false);
			}
			if (this.teclado.isIzq()) {
				miMapa.moverBombermanIzq(bomberman[0], Bomberman.VELOCIDAD);
				this.teclado.setIzq(false);
			}
			if (this.teclado.isDer()) {
				miMapa.moverBombermanDer(bomberman[0], Bomberman.VELOCIDAD);
				this.teclado.setDer(false);
			}
			if (this.teclado.isAbajo()) {
				miMapa.moverBombermanAbajo(bomberman[0], Bomberman.VELOCIDAD);
				this.teclado.setAbajo(false);
			}
			if (this.teclado.isL()) {
				miMapa.agregarBomba(bomberman[0].getX(), bomberman[0].getY());
				this.teclado.setL(false);
			}

		}
		if (bomberman[1].verSiEsVisible()) {

			if (this.teclado.isW()) {
				miMapa.moverBombermanArriba(bomberman[1], Bomberman.VELOCIDAD);
				this.teclado.setW(false);
			}
			if (this.teclado.isA()) {
				miMapa.moverBombermanIzq(bomberman[1], Bomberman.VELOCIDAD);
				this.teclado.setA(false);
			}
			if (this.teclado.isD()) {
				miMapa.moverBombermanDer(bomberman[1], Bomberman.VELOCIDAD);
				this.teclado.setD(false);
			}
			if (this.teclado.isS()) {
				miMapa.moverBombermanAbajo(bomberman[1], Bomberman.VELOCIDAD);
				this.teclado.setS(false);
			}
			if (this.teclado.isF()) {
				miMapa.agregarBomba(bomberman[1].getX(), bomberman[1].getY());
				this.teclado.setF(false);
			}

		}
		repaint();
	}
}
