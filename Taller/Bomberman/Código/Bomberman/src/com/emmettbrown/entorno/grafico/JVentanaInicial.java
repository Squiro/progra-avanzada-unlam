package com.emmettbrown.entorno.grafico;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.mensajes.servidor.MsgConectarseASala;
import com.emmettbrown.mensajes.servidor.MsgCrearSala;
import com.emmettbrown.mensajes.servidor.MsgObtenerSalas;

public class JVentanaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanelInicial contentPane;
	private JList<Sala> lstSalas;
	private Cliente cliente;
	private ArrayList<Sala> salasCreadas;
	private DefaultListModel<Sala> df;
	
	/**
	 * Create the frame.
	 */
	public JVentanaInicial(Cliente cliente) {
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 447);
		contentPane = new JPanelInicial(this);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.cliente = cliente;
		
		lstSalas = new JList<Sala>();
		lstSalas.setBounds(20, 141, 391, 222);
		contentPane.add(lstSalas);
		df = new DefaultListModel<>();
		lstSalas.setModel(df);
		
		JButton btnUnirseALa = new JButton("Unirse a la Sala");
		
		btnUnirseALa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				unirseASala();
			}
		});
		btnUnirseALa.setBounds(304, 374, 107, 23);
		contentPane.add(btnUnirseALa);
		
		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearSala();
			}
		});
		btnCrearSala.setBounds(20, 374, 108, 23);
		contentPane.add(btnCrearSala);
		
		JLabel lblTextoSalas = new JLabel("Salas actuales en el servidor:");
		lblTextoSalas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTextoSalas.setBounds(20, 116, 181, 14);
		contentPane.add(lblTextoSalas);
		
		JTextArea txtrBienvenidoAlBomberman = new JTextArea();
		txtrBienvenidoAlBomberman.setEditable(false);
		txtrBienvenidoAlBomberman.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrBienvenidoAlBomberman.setBackground(SystemColor.menu);
		txtrBienvenidoAlBomberman.setLineWrap(true);
		txtrBienvenidoAlBomberman.setText("Bienvenido al Bomberman desarrollado por Emmett-Brown. \r\n\r\nPara continuar, por favor cree una sala o seleccione una de las ya creadas y \u00FAnase a la misma.");
		txtrBienvenidoAlBomberman.setBounds(20, 11, 391, 94);
		contentPane.add(txtrBienvenidoAlBomberman);
		
		obtenerSalas();
		//Refresh rate = 1 frame per second
		RefreshThread thread = new RefreshThread(this, 1);
		thread.start();				
	}
		
	public void crearSala() {
		cliente.enviarMsg(new MsgCrearSala(cliente.getIdCliente()));
		refrescarListaSalas();
		//dispose();
	}

	public void refrescarListaSalas() {
		this.salasCreadas = cliente.getListaSalas();
		
		df.clear();
		
		for (Sala sala : salasCreadas) {
			df.addElement(sala);
		}
	}
	
	public void unirseASala() {
		Sala seleccionada = lstSalas.getSelectedValue();
		
		cliente.enviarMsg(new MsgConectarseASala(seleccionada.getId(), cliente.getIdCliente()));		
	}
	
	public void obtenerSalas() {
		cliente.enviarMsg(new MsgObtenerSalas());
	}
	
	public JList<Sala> getLstSalas() {
		return this.lstSalas;
	}
}
