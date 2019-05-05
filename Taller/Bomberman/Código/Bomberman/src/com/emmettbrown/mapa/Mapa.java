package com.emmettbrown.mapa;

import com.emmettbrown.entidades.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Mapa {
	public static final int ANCHO = 5;
	public static final int ALTO = 5;
	private Map<Ubicacion, Entidad> conjuntoEntidades = new TreeMap<Ubicacion, Entidad>();
	private Bomberman[] listaBomberman;

	public void generarMapa(){
		for (int i=0; i<ANCHO; i++) {
			for (int j=0; j<ALTO; j++) {
				if (i%2!=0 && j%2!=0) {
					conjuntoEntidades.put(new Ubicacion(i, j), new Muro(i, j));
				}
				
			}
		}
		conjuntoEntidades.put(new Ubicacion(0, 1), new Obstaculo(0, 1));
	}
	
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
		int calculo, i = 1;
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
		Bomba b =((Bomba)conjuntoEntidades.get(u));
		b.explotar(this);
		
	}
	
	public void explotarBomba(int posX,int posY) {
		Bomba b =((Bomba)conjuntoEntidades.get(new Ubicacion(posX,posY)));
		b.explotar(this);
		
	}	
}
