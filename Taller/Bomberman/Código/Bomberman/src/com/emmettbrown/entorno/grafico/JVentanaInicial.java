package com.emmettbrown.entorno.grafico;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentLinkedQueue;

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
import com.emmettbrown.mensajes.servidor.MsgVerificarContraseñaSala;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class JVentanaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanelInicial contentPane;
	private Cliente cliente;
	private JList<Sala> lstSalas;
	private ConcurrentLinkedQueue<Sala> salasCreadas;
	private DefaultListModel<Sala> df;
	private RefreshThread thread;
	private String password;

	/**
	 * Create the frame.
	 */
	public JVentanaInicial(Cliente cliente) {
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 500);
		
		this.cliente = cliente;
		cliente.setVentanaInicial(this);
		this.salasCreadas = cliente.getListaSalas();
		contentPane = new JPanelInicial(salasCreadas);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	//	this.getContentPane().add(contentPane);
		
		lstSalas = new JList<Sala>();
		contentPane.setLstSalas(lstSalas);
		df = new DefaultListModel<>();
		lstSalas.setModel(df);
		contentPane.setDefaultModel(df);

		this.password = new String();
		
		JButton btnUnirseALa = new JButton("Unirse a la Sala");

		btnUnirseALa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				unirseASala();
			}
		});

		JButton btnCrearSalaPublica = new JButton("Crear Sala Publica");
		btnCrearSalaPublica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearSala();
				crearLobbyCreador();
			}
		});

		JLabel lblTextoSalas = new JLabel("Salas actuales en el servidor:");
		lblTextoSalas.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JTextArea txtrBienvenidoAlBomberman = new JTextArea();
		txtrBienvenidoAlBomberman.setEditable(false);
		txtrBienvenidoAlBomberman.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrBienvenidoAlBomberman.setBackground(SystemColor.menu);
		txtrBienvenidoAlBomberman.setLineWrap(true);
		txtrBienvenidoAlBomberman.setText(
				"Bienvenido al Bomberman desarrollado por Emmett-Brown. \r\n\r\nPara continuar, por favor cree una sala o seleccione una de las ya creadas y \u00FAnase a la misma.");
		
		JButton btnSalaPrivada = new JButton("Crear Sala Privada");
		btnSalaPrivada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearSalaPrivada();				
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCrearSalaPublica, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addGap(43)
							.addComponent(btnSalaPrivada, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
							.addGap(34)
							.addComponent(btnUnirseALa, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lstSalas, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTextoSalas, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
							.addGap(308))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtrBienvenidoAlBomberman, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(21, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(txtrBienvenidoAlBomberman, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTextoSalas, GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(lstSalas, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCrearSalaPublica, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalaPrivada, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUnirseALa, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		setLocationRelativeTo(null);

		obtenerSalas();
		// Refresh rate = 1 frame per second
		thread = new RefreshThread(this, 1);
		thread.start();
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public synchronized void refrescarListaSalas() {
		df.clear();
		for (Sala sala : salasCreadas) {
			df.addElement(sala);
		}
	}
	
	public void crearJDialogPassword() {
		new JDialogPassword(this);		
	}
	
	private void crearSalaPrivada() {
		crearJDialogPassword();		
		crearSala();		
		crearLobbyCreador();
	}

	public void crearSala() {
		cliente.enviarMsg(new MsgCrearSala(cliente.getIdCliente(), password));
		refrescarListaSalas();
	}

	public void unirseASala() {		
		Sala seleccionada = lstSalas.getSelectedValue();	
		
		if (seleccionada != null) {			
			if (seleccionada.esPrivada()) {
				crearJDialogPassword();
				cliente.enviarMsg(new MsgVerificarContraseñaSala(password, seleccionada.getId()));				
			} else {
				crearLobbyInvitado();
			}			
		}		
	}
	
	public void crearLobbyInvitado() {
		Sala seleccionada = lstSalas.getSelectedValue();
		JVentanaLobby sala = new JVentanaLobby(cliente, seleccionada, false);
		sala.setVisible(true);
		thread.matarThread();
		dispose();	
		cliente.enviarMsg(new MsgConectarseASala(seleccionada.getId()));
	}
	
	public void crearLobbyCreador() {
		Sala salaCreador = null;
		
		while (salaCreador == null) {
			salaCreador = getSalaCreador();
		}
		
		JVentanaLobby sala = new JVentanaLobby(cliente, salaCreador, true);				
		thread.matarThread();
		sala.setVisible(true);
		dispose();
	}

	public void obtenerSalas() {
		cliente.enviarMsg(new MsgObtenerSalas());
	}

	public JList<Sala> getLstSalas() {
		return this.lstSalas;
	}
	
	public synchronized Sala getSalaCreador() {
		for (Sala sala : salasCreadas) {
			if (sala.getIdCreador() == cliente.getIdCliente()) 
				return sala;
		}
		
		return null;
	}
}
