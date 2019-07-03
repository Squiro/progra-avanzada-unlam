package com.emmettbrown.entorno.grafico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entidades.DefConst;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class JVentanaSala extends JFrame {

	private JPanel contentPane;
	private Cliente cliente;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JVentanaSala frame = new JVentanaSala();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JVentanaSala() {
		setTitle("Sala: ");
		cliente = new Cliente(DefConst.IP, DefConst.PORT, "Usuario1");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList lstJugadores = new JList();
		lstJugadores.setBounds(10, 36, 414, 165);
		contentPane.add(lstJugadores);
		DefaultListModel<String> df = new DefaultListModel<>();
		lstJugadores.setModel(df);
//		cliente.enviarMsg(new MsgActualizarLista(df));
		JButton btnCrearPartida = new JButton("Comenzar partida");
		btnCrearPartida.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {			
			}
		});
		btnCrearPartida.setBounds(134, 212, 162, 23);
		contentPane.add(btnCrearPartida);
		
		JLabel lblNewLabel = new JLabel("Jugadores conectados en esta sala:");
		lblNewLabel.setBounds(10, 11, 196, 14);
		contentPane.add(lblNewLabel);
	}
}
