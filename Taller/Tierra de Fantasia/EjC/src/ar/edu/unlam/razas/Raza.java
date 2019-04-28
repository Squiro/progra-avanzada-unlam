package ar.edu.unlam.razas;

public abstract class Raza {
	
	protected int saludActual;
	protected int danioBasico;
	protected double rangoDeAtaque;
	protected int saludMaxima;
	protected int pos;
	protected int danioActual;
	
	public abstract void atacar(Raza r);
	
	
	
	public abstract boolean puedeAtacar(Raza r);
	
	public abstract void serAtacado(Raza r);

	public abstract void descansar();
	
	

}
