package ar.edu.unlam.punto2D;

public interface Punto {
	
	public abstract double distancia(Object obj);
	public abstract double distanciaAlOrigen();
	public abstract String toString();
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
	public abstract Punto clone();
	public abstract void desplazamiento(Object obj);
}
