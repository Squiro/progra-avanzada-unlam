package com.emmettbrown.entorno.grafico;

import java.awt.Color;
import javax.swing.JFrame;
import com.emmettbrown.controles.Teclado;
import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.mapa.Mapa;

public class JVentanaGrafica extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanelGrafico contentPane;
	private Teclado teclado;
	private Bomberman miBomber;
	private Mapa miMapa;
	
	
	public JVentanaGrafica(Mapa miMapa, Bomberman miB, int ancho, int alto) {
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
		this.miBomber = miB;
		this.miMapa = miMapa;
		this.teclado = new Teclado();
		addKeyListener(teclado);
	}
	
	
	public void refrescar() {
		if (miBomber.verSiEsVisible()) {						
			if (this.teclado.isArriba()) {
				miMapa.moverBombermanArriba(miBomber, Bomberman.VELOCIDAD);
			}	
			if (this.teclado.isIzq()) {
				miMapa.moverBombermanIzq(miBomber,  Bomberman.VELOCIDAD);
			}	
			if (this.teclado.isDer()) {
				miMapa.moverBombermanDer(miBomber, Bomberman.VELOCIDAD);
			}	
			if (this.teclado.isAbajo()) {
				miMapa.moverBombermanAbajo(miBomber,  Bomberman.VELOCIDAD);
			}	
			if(this.teclado.isPonerBomba()) {
				miMapa.agregarBomba(miBomber.getX(), miBomber.getY());			
			} 
		}
		repaint();
	}
}
