package com.emmettbrown.entorno.grafico;


import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.mensajes.servidor.MsgComenzarPartida;

public class JVentanaLobby extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanelLobby contentPane;
	private Cliente cliente;
	private JList<String> lstJugadores;
	private DefaultListModel<String> df;
	private RefreshThread refreshThread;
	private ArrayList<String> usuariosConectados = new ArrayList<String>();
	private Sala sala;

	public JVentanaLobby(Cliente cliente, Sala sala, boolean puedeCrearPartida) {
		setTitle("Sala: ");
		this.sala = sala;
		this.cliente = cliente;
		sala.agregarUsuario(cliente.getUsername());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanelLobby(this);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    lstJugadores = new JList<String>();
		lstJugadores.setBounds(10, 36, 414, 165);
		contentPane.add(lstJugadores);
		df = new DefaultListModel<String>();
		lstJugadores.setModel(df);
		
		JButton btnCrearPartida = new JButton("Comenzar partida");
		btnCrearPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				comenzarPartida();
			}
		});
		
		btnCrearPartida.setBounds(134, 212, 162, 23);
		contentPane.add(btnCrearPartida);
		
		JLabel lblNewLabel = new JLabel("Jugadores conectados en esta sala:");
		lblNewLabel.setBounds(10, 11, 196, 14);
		contentPane.add(lblNewLabel);
		
		refreshThread = new RefreshThread(this, 1);
		refreshThread.start();
		btnCrearPartida.setEnabled(puedeCrearPartida);
		cliente.setLobby(this);
		cliente.setSalaActual(sala);
		setLocationRelativeTo(null);
	}

	public void comenzarPartida() {
		cliente.enviarMsg(new MsgComenzarPartida());
	}
	
	public void refrescarListaUsuarios() {
		this.usuariosConectados = sala.getUsuarios();
		
		df.clear();		
		for (String usr : usuariosConectados) {
			df.addElement(usr);
		}
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public void eliminarVentana() {
		refreshThread.matarThread();
		dispose();
	}
}
