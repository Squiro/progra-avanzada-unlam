package com.emmettbrown.base.datos.base;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Loguearse extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;


	public Loguearse(Usuario usuario,GestionBD gestion) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				gestion.getFactory().close();
			}
		});
		
		Loguearse referencia=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(49, 53, 66, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setBounds(49, 104, 66, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(160, 50, 207, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		
		
		JButton btnNewButtonRegistrarse = new JButton("Registrarse");
		btnNewButtonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registrarse registarse = new Registrarse(usuario,gestion,referencia);
				registarse.setVisible(true);
			}
		});
		btnNewButtonRegistrarse.setBounds(85, 180, 126, 23);
		contentPane.add(btnNewButtonRegistrarse);
		
		
		JButton btnNewButtonLoguearse = new JButton("Loguearse");
		btnNewButtonLoguearse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usuario.setUsuario(textFieldUsuario.getText());
				usuario.setContraseña(new String(passwordField.getPassword()));
				String consulta = "select p.contraseña from Usuario p ";
				consulta += "where p.usuario=" + "'" + usuario.getUsuario() + "'" + 
						" and p.contraseña=" + "'" + usuario.getContraseña() + "'";
				List<Object[]> lista=gestion.obtenerRegistro(usuario, consulta);
				if(lista.size()==1) {
					System.out.println("Iniciar juego o entrar en la sala de espera");
					gestion.getFactory().close();
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null,"Usuario o contraseña invalido");
				}
				
			}
		});
		btnNewButtonLoguearse.setBounds(232, 180, 117, 23);
		contentPane.add(btnNewButtonLoguearse);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("LOGUEARSE");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		lblNewLabel_2.setBounds(119, 11, 197, 14);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 101, 207, 20);
		contentPane.add(passwordField);
	}

	public void setTextFieldUsuario(String usuario) {
		this.textFieldUsuario.setText(usuario);
	}

//	public void setTextFieldContraseña(String  contra) {
//		this.textFieldContraseña.setText(contra);
//	}
	
	
}
