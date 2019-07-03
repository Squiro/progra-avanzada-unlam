package com.emmettbrown.entorno.grafico;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entidades.DefConst;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;


public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private Cliente cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/resources/icons/bomb.png")));
		setTitle("Iniciar sesi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIngreseUsuario = new JLabel("Ingrese Usuario");
		lblIngreseUsuario.setBounds(50, 50, 126, 14);
		contentPane.add(lblIngreseUsuario);

		JLabel lblIngreseContrasea = new JLabel("Ingrese Contrase\u00F1a");
		lblIngreseContrasea.setBounds(50, 75, 126, 14);
		contentPane.add(lblIngreseContrasea);

		txtUsername = new JTextField();
		txtUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarUsuario(txtUsername.getText(),new String (txtPassword.getPassword()));
			}
		});
		txtUsername.setBounds(197, 47, 107, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validarUsuario(txtUsername.getText(),new String (txtPassword.getPassword()));
			}
		});
		txtPassword.setBounds(197, 72, 107, 20);
		contentPane.add(txtPassword);
		txtUsername.setText("Nico");
		txtPassword.setText("1234");
		JButton btnIniciarSesin = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validarUsuario(txtUsername.getText(),new String (txtPassword.getPassword()))) {
					cliente = new Cliente("localhost", 5000, txtUsername.getText());

					//JVentanaGrafica ventana = new JVentanaGrafica(DefConst.ANCHO, DefConst.ALTO, cliente);
					//ventana.setVisible(true);
					JVentanaInicial inicial = new JVentanaInicial(cliente);
					dispose();					
				}
			}
		});
		btnIniciarSesin.setBounds(162, 100, 142, 23);
		contentPane.add(btnIniciarSesin);

		JButton btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.setBounds(50, 213, 142, 23);
		contentPane.add(btnCrearUsuario);
	}

	//Esto debería ser server side...
	public boolean validarUsuario(String user, String pass){
		if (pass.equals("1234")){ //if (user.equals("bomber") && pass.equals("1234")){
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Incorrecto, intente de nuevo", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
