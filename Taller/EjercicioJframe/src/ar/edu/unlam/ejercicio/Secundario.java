package ar.edu.unlam.ejercicio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Secundario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;

	/**
	 * Create the frame.
	 */
	public Secundario(Principal princip) {
		setTitle("Secundario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(30, 35, 356, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setText(princip.getTxtNombre());
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				princip.setTxtNombre(txtNombre.getText());
				dispose();
				princip.setVisible(true);				
			}
		});
		btnAceptar.setBounds(323, 214, 89, 23);
		setLocationRelativeTo(princip);
		
		contentPane.add(btnAceptar);
	}		
}
