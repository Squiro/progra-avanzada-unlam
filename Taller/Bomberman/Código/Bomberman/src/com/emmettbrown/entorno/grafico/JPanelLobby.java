
package com.emmettbrown.entorno.grafico;

import java.awt.Graphics;
import javax.swing.JPanel;

public class JPanelLobby extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JVentanaLobby ventana;

	public JPanelLobby(JVentanaLobby ventana) {
		this.ventana = ventana;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ventana.refrescarListaUsuarios();
	}

}