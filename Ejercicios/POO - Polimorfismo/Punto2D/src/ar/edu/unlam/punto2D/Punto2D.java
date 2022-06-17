package ar.edu.unlam.punto2D;

public class Punto2D implements Punto {

	private double x;
	private double y;	
	
	/** Getters y Setters*/
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	/** Constructores */
	public Punto2D() {
		this.x = 0.0;
		this.y = 0.0;
	}
	
	public Punto2D(double x, double y) {
		this.x = x;
		this.y = y;
	}	
	
	public double distancia(Object obj) {
		Punto2D p2 = new Punto2D(((Punto2D) obj).x, ((Punto2D) obj).y);
		return Math.sqrt(Math.pow(p2.x- this.x, 2) + Math.pow(p2.y - this.y, 2));
	}
	
	public double distanciaAlOrigen() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public void desplazamiento(Object obj) {
		this.x += ((Punto2D) obj).x;
		this.y += ((Punto2D) obj).y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punto2D other = (Punto2D) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Punto2D: (" + x + ", " + y + ")";
	}
	
	@Override
	public Punto2D clone() {
		return new Punto2D(this.x, this.y);
	}
	
}
