package com.emmettbrown.base.datos.base;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JPasswordField;

public class Registrarse extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JLabel lblNewLabel_2;
	private JTextField textFieldErrores;
	private JPasswordField passwordField;


	public Registrarse(Usuario usuario,GestionBD gestion,Loguearse referencia) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				confirmarCierreVentana(referencia);
			}
		});
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(43, 64, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setBounds(43, 126, 103, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(156, 61, 196, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		
		JButton btnNewButtonAceptar = new JButton("Aceptar");
		btnNewButtonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario.setUsuario(textFieldUsuario.getText());
				usuario.setContraseña(new String(passwordField.getPassword()));
				if(gestion.insertarRegistro(usuario) == false) {
					
					passwordField.setText(null);
					textFieldUsuario.setText(null);
					JOptionPane.showMessageDialog(null,"Usuario ya registrado");
				}
				else{
//					referencia.setTextFieldContraseña(usuario.getContraseña());
					referencia.setTextFieldUsuario(usuario.getUsuario());
					referencia.setVisible(true);
					dispose();
				}					
			}
		});
		btnNewButtonAceptar.setBounds(156, 197, 89, 23);
		contentPane.add(btnNewButtonAceptar);
		
		lblNewLabel_2 = new JLabel("REGISTRARSE");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		lblNewLabel_2.setBounds(149, 11, 148, 14);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 123, 196, 20);
		contentPane.add(passwordField);
	}
	
	private void confirmarCierreVentana(Loguearse referencia) {
		int respuesta = JOptionPane.showConfirmDialog(this, "Desea cerrar esta ventana", "confirmar Salir", JOptionPane.YES_NO_OPTION);
		if(respuesta == JOptionPane.YES_OPTION) {
			referencia.setVisible(true);
			dispose();
		}			
	}
}
