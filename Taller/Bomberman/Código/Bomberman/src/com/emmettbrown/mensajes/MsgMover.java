package com.emmettbrown.mensajes;

import java.util.HashMap;

import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.entidades.DefConst;
import com.emmettbrown.entidades.Entidad;
import com.emmettbrown.mapa.Ubicacion;
import com.emmettbrown.servidor.HiloCliente;
import com.sun.javafx.geom.Rectangle;

public class MsgMover extends Msg{

	private Bomberman b;
	private int dx;
	private int dy;
	private HashMap<Ubicacion, Entidad> conjuntoEntidades;

	public MsgMover (Bomberman b, int despX,int despY, HashMap<Ubicacion, Entidad> ce) {
		this.b = b;
		this.dx = despX;
		this.dy = despY;
		this.conjuntoEntidades = ce;
	}
	
	/**
	 * Realiza una serie de chequeos y en caso de validar correctamente, mueve el
	 * bomberman a una nueva ubicacion en el mapa.
	 * 
	 * @param bomberman el bomberman a mover
	 * @param despX     el desplazamiento en el eje X que realizara el bomberman
	 * @param despY     el desplazamiento en el eje Y que realizara el bomberman
	 */
	
	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente hilo =(HiloCliente) obj;
		if (puedeMoverse(b.getX()+dx, b.getY()+dy, b)) {
			b.cambiarPosX(dx);
			b.cambiarPosY(dy);	
			//Actualizamos la ubicacion relativa en la matriz
			Ubicacion ubic = new Ubicacion(b.getX()/DefConst.TILESIZE, b.getY()/DefConst.TILESIZE);
			b.cambiarUbicacion(ubic);	
			//Actualizamos el flag "ignorarColisionCreador" de las bombas del bomberman
			b.actualizarColBomba();
		}	
		
		return null;
	}
	
	


	private void moverBomberman(Bomberman bomberman, int despX, int despY) {

	}

	/**
	 * Chequea si el bomberman puede moverse a la posicion que recibe por parametro.
	 * 
	 * @param ubic ubicacion auxiliar que refleja la posible nueva ubicacion del
	 *              Bomberman
	 * @return true: puede moverse, false: no puede moverse
	 */
	public boolean puedeMoverse(int x, int y, Bomberman bomberman) {
		return !chequearColisiones(bomberman, x, y);
	}

	/**
	 * Chequea si no existe ninguna otra entidad colisionable del juego presente en
	 * la ubicacion que llega por parametro.
	 * 
	 * @param ubic ubicacion a revisar en buscar de entidades
	 * @return true: no hay ninguna entidad presente, false: hay una entidad en
	 *         dicha posicion
	 */

	public boolean hayEntidadEn(Ubicacion ubic) {
		return conjuntoEntidades.get(ubic) == null;
	}
	
	/** Chequea si una entidad está colisionando con alguna del conjunto de entidades
	 * 
	 * @param ent la entidad con la que se va a chequear si hay colision
	 * @return true: hay colision, false: no hay
	 */
	
	public boolean chequearColisiones(Bomberman bomberman, int x, int y) {
		//Como puede haber mas de una colision al mismo tiempo, usamos una variable booleana
		//en vez de retornar el valor individual de una colision
		boolean col = false;	
		
		//Recibo el X, Y del bomberman con un desplazamiento...
		//Ubicacion del desplazamiento
		Ubicacion ubicDesp = new Ubicacion(x/DefConst.TILESIZE, y/DefConst.TILESIZE);
		
		/* Solo tengo que fijarme si en los casilleros aledaños hay una colisión con el Bomberman
		* El bomberman siempre va a tener 9 casilleros aledaños:
		* 	Tres arriba suyo		
		*	Tres en la misma fila
		* 	Y tres abajo suyo
		*/
		
		for (int i = 0; i < 3; i++) {
			//Entidad que se encuentra en la fila superior a la del bomberman
			Entidad entidadSup = conjuntoEntidades.get(new Ubicacion((ubicDesp.getPosX()-1)+i, ubicDesp.getPosY()-1));		
			//Entidad que se encuentra en la misma fila que la del bomberman
			Entidad entidadSame = conjuntoEntidades.get(new Ubicacion((ubicDesp.getPosX()-1)+i, ubicDesp.getPosY()));
			//Entidad que se encuentra en la fila inferior a la del bomberman
			Entidad entidadInf = conjuntoEntidades.get(new Ubicacion((ubicDesp.getPosX()-1)+i, ubicDesp.getPosY()+1));
			
			//Si existe colision con alguna de las tres...
			if (hayColision(bomberman, x, y, entidadSup) || hayColision(bomberman, x, y, entidadSame) 
					|| hayColision(bomberman, x, y, entidadInf)) {
				col = true;
			}			
		}		
		
		return col;
	}
	
	/** Chequea que haya una colisión, y en caso de haberlo, maneja las mismas. 
	 * 
	 * @param bman
	 * @param x
	 * @param y
	 * @param ent
	 * @return
	 */
	
	public boolean hayColision(Bomberman bman, int x, int y, Entidad ent)  {
		if (ent != null) {
			//Si existe una colision de hitboxes
			if (interseccionHitBox(bman, x, y, ent)) {
				//Manejamos la colision con la entidad
				if (bman.manejarColisionCon(ent)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/** Chequea si existe una intereseccion entre los hitboxes de un bomberman y una entidad cualquiera.
	 *  
	 * 
	 * @param bman el bomberman
	 * @param x posicion X del bomberman, puede ser la posicion de desplazamiento o no
	 * @param y posicion Y del bomberman, puede ser la posicion de desplazamiento o no
	 * @param ent la entidad
	 * @return true: hay interseccion de hitbox, false: no hay
	 */
	
	public boolean interseccionHitBox(Bomberman bman, int x, int y, Entidad ent) {
		//Hitbox del bomberman
		Rectangle hitBoxBman = new Rectangle(x, y, bman.getWidth(), bman.getHeight());
		//Hitbox de la entidad
		Rectangle hitBoxEnt = ent.getHitBox();
		//Vemos si existe una interseccion entre ambos rectangulos
		Rectangle intersection = hitBoxBman.intersection(hitBoxEnt);
		//Si la interseccion no es vacia, entonces retornamos que hay colision
		if (!intersection.isEmpty()) {
			return true;
		}
		
		return false;		
	}

	/**
	 * Reciben como parametros el bomberman a mover, y el desplazamiento sin NINGUN
	 * tipo de signo. 
	 * 
	 * De igual forma, los valores están forzados a un math.abs. Está todo fool proofeado.
	 */

	public void moverBombermanArriba(Bomberman bomberman, int desplazamiento) {
		bomberman.cambiarImagenArriba();
		this.moverBomberman(bomberman, 0, -Math.abs(desplazamiento));
		
	}

	public void moverBombermanAbajo(Bomberman bomberman, int desplazamiento) {
		bomberman.cambiarImagenAbajo();
		this.moverBomberman(bomberman, 0, Math.abs(desplazamiento));
	}

	public void moverBombermanIzq(Bomberman bomberman, int desplazamiento) {
		bomberman.cambiarImagenIzquierda();
		this.moverBomberman(bomberman, -Math.abs(desplazamiento), 0);
		
	}

	public void moverBombermanDer(Bomberman bomberman, int desplazamiento) {
		bomberman.cambiarImagenDerecha();
		this.moverBomberman(bomberman, Math.abs(desplazamiento), 0);
	}	

}
