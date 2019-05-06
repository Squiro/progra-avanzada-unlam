package com.emmettbrown.mapa;

import com.emmettbrown.entidades.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Mapa {
	public static final int ANCHO = 9;
	public static final int ALTO = 9;
	private Map<Ubicacion, Entidad> conjuntoEntidades = new TreeMap<Ubicacion, Entidad>();
	private Bomberman[] listaBomberman;

	public void generarMapa() {
		for (int i = 0; i < ANCHO; i++) {
			for (int j = 0; j < ALTO; j++) {
				if (i % 2 != 0 && j % 2 != 0) { /// EN LAS POSICIONES I,J IMPARES PONDREMOS INDESTRUCTIBLES, EN CASO
												/// CONTRARIO EVALUAREMOS PONER OBSTACULOS
					conjuntoEntidades.put(new Ubicacion(i, j), new Muro(i, j));
				} else if ((posicionValida(i, j)) && Math.random() >= 0.25) {///75% DE PROBABILIDAD DE CREAR UN OBSTACULO
					conjuntoEntidades.put(new Ubicacion(i, j), new Obstaculo(i, j));
				}
			}
		}
	}

	private boolean posicionValida(final int posX, final int posY) {
		if (posX == 0 && posY == 0 || posX == 0 && posY == 1 || posX == 1 && posY == 0) {
			return false;
		}

		if (posX == ANCHO - 1 && posY == 0 || posX == ANCHO - 2 && posY == 0 || posX == ANCHO - 1 && posY == 1) {
			return false;
		}

		if (posX == 0 && posY == ALTO - 1 || posX == 0 && posY == ALTO - 2 || posX == 1 && posY == ALTO - 1) {
			return false;
		}

		if (posX == ANCHO - 1 && posY == ALTO - 1 || posX == ANCHO - 2 && posY == ALTO - 1
				|| posX == ANCHO - 1 && posY == ALTO - 2) {
			return false;
		}
		return true;
	}
	
	public boolean puedeMoverse(Ubicacion ubic) {
		if (ubic.getPosX() < 0 || ubic.getPosX() >= Mapa.ANCHO) 
			return false;
		if (ubic.getPosY() < 0 || ubic.getPosY() >= Mapa.ALTO)
			return false;
		
		return estaLibre(ubic);		
	}
	
	public void moverBomberman(int numeroBomberman, String direccion) {
		Ubicacion aux = listaBomberman[numeroBomberman-1].obtenerUbicacion().clone();
		switch (direccion) {
		case Bomberman.DERECHA:
			aux.cambiarPosX(Bomberman.VELOCIDAD);
			if(puedeMoverse(aux)) {
				listaBomberman[numeroBomberman-1].moverDer();
				
			}
			System.out.println(aux);
			break;

		case Bomberman.IZQUIERDA:
			aux.cambiarPosX(-Bomberman.VELOCIDAD);
			if(puedeMoverse(aux)) {
				listaBomberman[numeroBomberman-1].moverIzq();
			}
			break;
			
		case Bomberman.ABAJO:
			aux.cambiarPosY(Bomberman.VELOCIDAD);
			if(puedeMoverse(aux)) {
				listaBomberman[numeroBomberman-1].moverAbajo();
			}
			break;

		case Bomberman.ARRIBA:
			aux.cambiarPosY(-Bomberman.VELOCIDAD);
			if(puedeMoverse(aux)) {
				listaBomberman[numeroBomberman-1].moverArriba();
			}
			break;
		}
	}
	
//	public void actualizarPosiciones(Ubicacion posAnt) {
//		for(int i=0; i<listaBomberman.length; i++) {
//			if(!puedeMoverse(listaBomberman[i].obtenerUbicacion())) {
//				listaBomberman[i].moverse(posAnt.getPosX(), posAnt.getPosY());
//			}
//		}
//	}

	public Map<Ubicacion, Entidad> obtenerListaEntidades() {
		return conjuntoEntidades;
	}

	public void agregarBomba(Ubicacion miUbicacion) {
		Ubicacion copia = miUbicacion.clone();
		conjuntoEntidades.put(copia, new Bomba(copia));

	}

	public void mostrarMapa() {
		Ubicacion ver;
		Iterator<Ubicacion> it = conjuntoEntidades.keySet().iterator();
		double calculo;
		int i = 1;
		while (it.hasNext()) {
			ver = it.next();
			calculo = ver.getPosX() * ANCHO + ver.getPosY() + 1;
			for (; i < calculo; i++) {
				System.out.printf("NULO\t");
				if (i != 0 && i % ANCHO == 0)
					System.out.println();
			}
			System.out.printf("%s\t", conjuntoEntidades.get(ver).getClass().getSimpleName().substring(0, 4));
			if (i != 0 && i % ANCHO == 0)
				System.out.println();
			i++;
		}
		i--;
		for (; i < (ANCHO * ALTO); i++) {
			if (i != 0 && i % ANCHO == 0)
				System.out.println();
			System.out.printf("NULO\t");
		}
		System.out.println();
	}

	public boolean estaLibre(Ubicacion miUbicacion) {
		if (conjuntoEntidades.get(miUbicacion) == null) {
			return true;
		}
		return false;
	}

	public void agregarBombermans(Bomberman[] lista) {
		listaBomberman = lista;
	}
	
	public void eliminarBomba(Ubicacion ubicacion) {
		conjuntoEntidades.remove(ubicacion);
	}

	public Bomberman[] obtenerBombermans() {
		return listaBomberman;
	}

	public void explotarBomba(Ubicacion u) {
		Bomba b = ((Bomba) conjuntoEntidades.get(u));
		b.explotar(this);

	}

	public void explotarBomba(int posX, int posY) {
		Bomba b = ((Bomba) conjuntoEntidades.get(new Ubicacion(posX, posY)));
		b.explotar(this);

	}
}
