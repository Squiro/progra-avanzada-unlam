package com.emmettbrown.entorno.grafico;

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
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.mensajes.servidor.MsgCrearUsuario;
import com.emmettbrown.mensajes.servidor.MsgLogin;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


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

	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/resources/icons/bomb.png")));
		setTitle("Iniciar sesi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 215);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblIngreseUsuario = new JLabel("Ingrese Usuario");

		JLabel lblIngreseContrasea = new JLabel("Ingrese Contrase\u00F1a");

		txtUsername = new JTextField();
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		JButton btnIniciarSesin = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtUsername.getText().isEmpty() || new String(txtPassword.getPassword()).isEmpty())
					JOptionPane.showMessageDialog(null, "Los dos campos deben estar completados", "Acceso denegado",
							JOptionPane.ERROR_MESSAGE);
				else {
				if (cliente != null) {
					cliente.enviarMsg(new MsgLogin(txtUsername.getText(),new String(txtPassword.getPassword())));
				}else {
					cliente = new Cliente(DefConst.IP, DefConst.PORT, txtUsername.getText());
					cliente.enviarMsg(new MsgLogin(txtUsername.getText(),new String(txtPassword.getPassword())));
					setPantallaLoginEnCliente();
				}
				}
					
			}
		});

		JButton btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtUsername.getText().isEmpty() || new String(txtPassword.getPassword()).isEmpty())
					JOptionPane.showMessageDialog(null, "Los dos campos deben estar completados", "Acceso denegado",
							JOptionPane.ERROR_MESSAGE);
				else {
				if (cliente != null) {
					cliente.enviarMsg(new MsgCrearUsuario(txtUsername.getText(),new String(txtPassword.getPassword())));
				}else {
					cliente = new Cliente(DefConst.IP, DefConst.PORT, txtUsername.getText());
					cliente.enviarMsg(new MsgCrearUsuario(txtUsername.getText(),new String(txtPassword.getPassword())));
				}
				setPantallaLoginEnCliente();			
			}
			}
		});
		
		JLabel lblCrearUsuario = new JLabel("O tambien puede");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCrearUsuario)
							.addPreferredGap(ComponentPlacement.RELATED, 198, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblIngreseContrasea, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblIngreseUsuario, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
									.addGap(2)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnCrearUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
								.addComponent(txtUsername, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
								.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
								.addComponent(btnIniciarSesin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
					.addGap(98))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblIngreseUsuario)
							.addGap(11)
							.addComponent(lblIngreseContrasea))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnIniciarSesin, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addGap(14)
					.addComponent(lblCrearUsuario, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCrearUsuario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void setPantallaLoginEnCliente() {
		cliente.setPantallaLogin(this);
	}
}
