package com.emmettbrown.entidades;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.mapa.Mapa;


public class Obstaculo extends Entidad{
	
	private Clip sonido;
	
	public Obstaculo(int posX, int posY) {
		super(posX, posY, DefConst.TILESIZE, DefConst.TILESIZE);
		this.destructible = true;
		this.img = new ImageIcon("./src/resources/game-map/brick/obstaculo.png");
	}
	
	@Override	
	public void explotar(Mapa map) {
		this.esVisible = false;
		sonido();
		map.removerEntidadDelConjunto(this.ubicacion);
	}

	private void sonido() {
		try {
			sonido = AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(new File("./src/resources/music/breakWall.wav")));
			sonido.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			System.out.println("ERROR AL ABRIR EL SONIDO EN JVENTANAGRAFICA");
			e.printStackTrace();
		}
	}	
}
