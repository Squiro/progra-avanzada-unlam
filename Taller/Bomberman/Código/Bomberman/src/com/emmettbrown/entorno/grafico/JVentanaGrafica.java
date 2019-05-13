package com.emmettbrown.entorno.grafico;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.mapa.Mapa;

public class JVentanaGrafica extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanelGrafico contentPane;
	
	public JVentanaGrafica(Mapa miMapa) {
		super("Bomberman");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, 1000, 1000);
		setBackground(Color.RED);
		setLocationRelativeTo(null);
		contentPane = new JPanelGrafico(miMapa);
		setBackground(Color.WHITE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		Mapa miMapa = new Mapa();
		miMapa.generarMapa();
		miMapa.agregarBomberman(new Bomberman(0,0));
		miMapa.agregarBomberman(new Bomberman(0,8));
		miMapa.agregarBomberman(new Bomberman(8,0));
		miMapa.agregarBomberman(new Bomberman(8,8));
		JVentanaGrafica ventGraf = new JVentanaGrafica(miMapa);
		ventGraf.setVisible(true);
	}
	public void refrescarMapa() {
		repaint();
	}

}
