package ar.edu.unlam.mapa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ar.edu.unlam.entidades.Bomba;
import ar.edu.unlam.entidades.Bomberman;
import ar.edu.unlam.entidades.Entidad;
import ar.edu.unlam.entidades.Obstaculo;
import ar.edu.unlam.juego.Juego;

public class Mapa {
	
	private static int ancho = 12;
	private static int largo = 24;
	private List<Entidad> entidades;
	private List<Bomberman> bombermans;
	private List<Bomba> bombas;	
	private char[][] mapa;
	
	private Juego juego;
	
	public Mapa(Juego juego) {
		this.entidades = new ArrayList<Entidad>();
		this.bombermans = new ArrayList<Bomberman>();
		this.bombas = new ArrayList<Bomba>();
		this.mapa = new char[ancho][largo];
		this.juego = juego;
		
		this.inicializarMapa();
		this.crearMapa();
		mostrarMapa();
	}	
	
	public void update() {
		this.bombermans.get(0).update();
	}
	
	public void inicializarMapa() {
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < largo; j++) {
				mapa[i][j] = 'a';
			}
		}
	}
	
	public void crearMapa() {
		crearObstaculos();
		crearBomberman();
		
		Entidad enti;
		
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < largo; j++) {
				if ((enti = getEntidad(i, j)) != null) {
					mapa[i][j] = enti.getSimbolo();
				}
			}
		}
		
	}
	
	public void  mostrarMapa() {
		String s = "";
		
		for (int i = 0; i < Mapa.ancho; i++) {
			for (int j = 0; j < Mapa.largo; j++) {
				s += this.mapa[i][j];
			}
			s += "\n";
		}
		
		System.out.println(s);
	}		
	
	public void crearObstaculos() {
		Random ran = new Random();
		int x, y;
		for (int i = 0; i < Juego.cantObstaculos; i++) {
			
			do {
				x = ran.nextInt(Mapa.ancho);
				y = ran.nextInt(Mapa.largo);				
			} while (getEntidad(x, y) != null); 
			
			this.entidades.add(new Obstaculo(x, y));
		}
	}
	
	public void crearBomberman() {
		Random ran = new Random();
		int x, y;		
		
		do {
			x = ran.nextInt(Mapa.ancho);
			y = ran.nextInt(Mapa.largo);				
		} while (getEntidad(x, y) != null); 
		
		this.bombermans.add(new Bomberman(x, y, juego.getInput(), this));		
	}
	
	public Entidad getEntidad(int x, int y) {
		Entidad res = null;
		
		res = getEntidadEn(x, y);
		
		if (res != null) {
			return res;
		}
		
		res = getBombaEn(x, y);
		
		if (res != null) {
			return res;
		}
		
		res = getBombermanEn(x, y);

		return res;
	}
	
	public Entidad getEntidadEn(int x, int y) {
		for (Entidad enti : entidades) {
			if (enti.getX() == x && enti.getY() == y) {
				return enti;
			}
		}		
		
		return null;		
	}
	
	public Bomba getBombaEn(int x, int y) {
		for (Bomba bomba : bombas) {
			if (bomba.getX() == x && bomba.getY() == y) {
				return bomba;
			}
		}
		
		return null;
	}
	
	public Bomberman getBombermanEn(int x, int y) {
		for (Bomberman bomb : bombermans) {
			if (bomb.getX() == x && bomb.getY() == y) {
				return bomb;
			}
		}
		
		return null;
	}	
}
