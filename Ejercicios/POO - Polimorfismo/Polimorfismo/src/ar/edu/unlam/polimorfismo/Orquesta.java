package ar.edu.unlam.polimorfismo;

import java.util.ArrayList;
import java.util.List;

public class Orquesta {
	
	private List<InstrumentoDeViento> vientos;
	private List<InstrumentoDeCuerdas> cuerdas;
	
	
	/** Getters y Setters */
	public List<InstrumentoDeViento> getVientos() {
		return vientos;
	}
	public void setVientos(List<InstrumentoDeViento> vientos) {
		this.vientos = vientos;
	}
	public List<InstrumentoDeCuerdas> getCuerdas() {
		return cuerdas;
	}
	public void setCuerdas(List<InstrumentoDeCuerdas> cuerdas) {
		this.cuerdas = cuerdas;
	}
	
	/** Constructores */
	public Orquesta() {
		this.vientos = new ArrayList<InstrumentoDeViento>();
		this.cuerdas = new ArrayList<InstrumentoDeCuerdas>();		
	}
	
	/** Métodos */
	
	public boolean add(InstrumentoDeViento inst) {
		return vientos.add(inst);
	}
	
	public boolean add(InstrumentoDeCuerdas inst) {
		return cuerdas.add(inst);
	}
	
	public void tocarVientos() {
		for (InstrumentoDeViento inst : vientos) {
			System.out.println(inst.tocar());	
		}
	}
	
	public void tocarCuerdas() {
		for (InstrumentoDeCuerdas inst : cuerdas) {
			System.out.println(inst.tocar());			
		}
	}
	
	public void tocar() {
		this.tocarVientos();
		this.tocarCuerdas();
	}
	
	public static void main(String[] args) {
		Orquesta orq = new Orquesta();
		
		Laud laud = new Laud();
		Banjo banjo = new Banjo();
		Tuba tuba = new Tuba();
		Oboe oboe = new Oboe();
		Flauta flauta = new Flauta();
		
		orq.add(laud);
		orq.add(banjo);
		orq.add(tuba);
		orq.add(oboe);
		orq.add(flauta);
		
		orq.tocar();
		
		Afinador afi = new Afinador();
		
		afi.afinar(oboe);
		afi.afinar(banjo);
	}


}
