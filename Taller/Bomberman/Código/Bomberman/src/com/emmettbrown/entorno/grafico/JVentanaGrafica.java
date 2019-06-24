package com.emmettbrown.entorno.grafico;

import java.awt.Color;

import javax.swing.JFrame;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.cliente.ListenerThread;
import com.emmettbrown.controles.Teclado;
import com.emmettbrown.entidades.DefConst;

public class JVentanaGrafica extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanelGrafico contentPane;
	private Teclado teclado;
	private Cliente cliente;

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
		
		
		this.cliente = cliente;
		this.teclado = new Teclado(cliente);
		
		addKeyListener(teclado);
		
		RefreshThread thread = new RefreshThread(this,cliente);
		thread.start();
		ListenerThread listener = new ListenerThread(this.cliente);
		listener.start();
	}

	/*public void refrescar() {
		
		if (this.teclado.isArriba()) {
			
			//miMapa.moverBombermanArriba(miBomber, DefConst.VELOCIDAD,cliente);
		}
		else if (this.teclado.isIzq()) {
			//miMapa.moverBombermanIzq(miBomber, DefConst.VELOCIDAD,cliente);
		}
		else if (this.teclado.isDer()) {
			
			//miMapa.moverBombermanDer(miBomber, DefConst.VELOCIDAD,cliente);
		}
		else if (this.teclado.isAbajo()) {
			//miMapa.moverBombermanAbajo(miBomber, DefConst.VELOCIDAD,cliente);
		}
		else if (this.teclado.isL()) {
			//miMapa.agregarBomba(miBomber.getX(), miBomber.getY(), miBomber);
		}		
		
//		if (bomberman[0].verSiEsVisible()) {
//
//			if (this.teclado.isArriba()) {
//				miMapa.moverBombermanArriba(bomberman[0], DefConst.VELOCIDAD);
//			}
//			else if (this.teclado.isIzq()) {
//				miMapa.moverBombermanIzq(bomberman[0], DefConst.VELOCIDAD);
//			}
//			else if (this.teclado.isDer()) {
//				miMapa.moverBombermanDer(bomberman[0], DefConst.VELOCIDAD);
//			}
//			else if (this.teclado.isAbajo()) {
//				miMapa.moverBombermanAbajo(bomberman[0], DefConst.VELOCIDAD);
//			}
//			else if (this.teclado.isL()) {
//				miMapa.agregarBomba(bomberman[0].getX(), bomberman[0].getY(), bomberman[0]);
//			}
//
//		}

		repaint();
	}*/
	
	public Teclado getTeclado() {
		return this.teclado;
	}
}
