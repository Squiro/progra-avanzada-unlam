package com.emmettbrown.controles;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.entorno.grafico.JVentanaGrafica;
import com.emmettbrown.entorno.grafico.JVentanaInicial;
import com.emmettbrown.mensajes.servidor.MsgDesconectarDeSala;
import com.emmettbrown.mensajes.servidor.MsgMover;

public class Teclado implements KeyListener {
	
	private Cliente cliente;
	private Movimientos mov;
	public Teclado(Cliente cliente) {
		this.cliente = cliente;
		mov = Movimientos.NULL;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (mov == Movimientos.NULL) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
					this.mov = Movimientos.ABAJO;
					this.cliente.enviarMsg(new MsgMover(Movimientos.ABAJO));
				break;
			case KeyEvent.VK_RIGHT:
					this.cliente.enviarMsg(new MsgMover(Movimientos.DERECHA));
					this.mov = Movimientos.DERECHA;
				break;
			case KeyEvent.VK_LEFT:
					this.cliente.enviarMsg(new MsgMover(Movimientos.IZQUIERDA));
					this.mov = Movimientos.IZQUIERDA;
				break;
			case KeyEvent.VK_UP:
					this.cliente.enviarMsg(new MsgMover(Movimientos.ARRIBA));
					this.mov = Movimientos.ARRIBA;
				break;
			case KeyEvent.VK_ESCAPE:
				JVentanaInicial ventAct = new JVentanaInicial(cliente);
				ventAct.setVisible(true);
				JVentanaGrafica ventGraf = cliente.getVentanaGrafica();
				ventGraf.detenerSonido();
				ventGraf.dispose();
				cliente.enviarMsg(new MsgDesconectarDeSala());
				break;
			case KeyEvent.VK_L:
				if(this.cliente.getBomber().cantBombasAct() < DefConst.CANTBOMBASMAX)
					this.cliente.enviarMsg(new MsgMover(Movimientos.BOMBA));
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {


		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			this.cliente.enviarMsg(new MsgMover(Movimientos.NULL));
			this.mov = Movimientos.NULL;
			break;
		case KeyEvent.VK_RIGHT:
			this.cliente.enviarMsg(new MsgMover(Movimientos.NULL));
			this.mov = Movimientos.NULL;
			break;
		case KeyEvent.VK_LEFT:
			this.cliente.enviarMsg(new MsgMover(Movimientos.NULL));
			this.mov = Movimientos.NULL;
			break;
		case KeyEvent.VK_UP:
			this.cliente.enviarMsg(new MsgMover(Movimientos.NULL));
			this.mov = Movimientos.NULL;
			break;
		case KeyEvent.VK_ESCAPE:
			//Agregar comando para salir
			break;
		case KeyEvent.VK_L:
			this.cliente.enviarMsg(new MsgMover(Movimientos.NULL));
			break;
		default:
			break;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}