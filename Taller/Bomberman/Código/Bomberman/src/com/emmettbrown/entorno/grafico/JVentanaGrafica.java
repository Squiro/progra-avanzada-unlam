package com.emmettbrown.entorno.grafico;

import java.awt.Color;

import javax.swing.JFrame;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.controles.Teclado;

public class JVentanaGrafica extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanelGrafico contentPane;
	private Teclado teclado;

	public JVentanaGrafica(int ancho, int alto, Cliente cliente) {
		super(DefConst.TITLE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, ancho, alto);
		setBackground(Color.RED);
		setLocationRelativeTo(null);

		contentPane = new JPanelGrafico(cliente);
		contentPane.setBackground(new Color(32,155,221));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		this.teclado = new Teclado(cliente);
		
		addKeyListener(teclado);
		
		//Refresca esta ventana constantemente, 30 fps
		RefreshThread thread = new RefreshThread(this, 30);
		thread.start();
	}

	public Teclado getTeclado() {
		return this.teclado;
	}
}
