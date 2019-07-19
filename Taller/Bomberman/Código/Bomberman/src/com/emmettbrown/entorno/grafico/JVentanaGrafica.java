package com.emmettbrown.entorno.grafico;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import javax.sound.sampled;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.controles.Teclado;
import com.emmettbrown.mensajes.servidor.MsgSiguienteRonda;

public class JVentanaGrafica extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanelGrafico contentPane;
	private Teclado teclado;
	private Cliente cliente;
	//private GameLoop gameloop;
	private RefreshThread refresh;
	private Clip sonido;
	public JVentanaGrafica(int ancho, int alto, Cliente clien) {
		super(DefConst.TITLE);
		setResizable(false);
		this.cliente = clien;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, ancho, alto);
		setBackground(Color.RED);
		setLocationRelativeTo(null);
		
		contentPane = new JPanelGrafico(cliente);
		contentPane.setBackground(new Color(32,155,221));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		sonido();
		this.teclado = new Teclado(cliente);
		
		addKeyListener(teclado);
		//Refresca esta ventana constantemente, 30 fps
		
		crearRefreshThread();
	}
	
	public void sonido()
	{
		try {
			sonido = AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(new File("./src/resources/music/cancBomb.wav")));
			sonido.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			System.out.println("ERROR AL ABRIR EL SONIDO EN JVENTANAGRAFICA");
			e.printStackTrace();
		}
		
	}
	
	public void detenerSonido() {
		sonido.close();
	}
	
	public Teclado getTeclado() {
		return this.teclado;
	}
	
	public Cliente obtenerCliente() {
		return cliente;
	}
	
	public void crearRefreshThread() {
		refresh = new RefreshThread(this, DefConst.FPS);
		refresh.start();
	}
		
	public void finRonda() {
		//Matamos el thread de refresh
		refresh.matarThread();
	
		Sala sala = cliente.getSalaActual();
		sala.getReloj().stop();
		JOptionPane.showMessageDialog(null, "La ronda "+ cliente.getSalaActual().getRondaActual() +" ha finalizado! Esperando a que el creador inicie la siguiente ronda.");
		
		if (sala.getIdCreador() == cliente.getIdCliente()) {
			System.out.println("Soy el creador. Inicio la siguiente ronda -VentanaGrafica");
			cliente.enviarMsg(new MsgSiguienteRonda());
		}
	}
	
	public void finPartida() {
		this.detenerSonido();
		refresh.matarThread();
		JOptionPane.showMessageDialog(null, "La partida ha finalizado.");
		JVentanaInicial ventAct = new JVentanaInicial(cliente);
		ventAct.setVisible(true);
		dispose();
	}
}
