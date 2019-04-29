package ar.edu.unlam.punto2D;

public class Punto3D extends Punto2D implements Punto {
	
	private double z;	
	
	public Punto3D(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}
		
	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	
	@Override
	public double distancia(Object obj) {
		Punto3D p2 = new Punto3D(((Punto3D) obj).getX(), ((Punto3D) obj).getX(), ((Punto3D) obj).z);
		return Math.sqrt(Math.pow(p2.getX() - this.getX(), 2) + Math.pow(p2.getY() - this.getY(), 2) + Math.pow(p2.z - this.z, 2));
	}
	
	@Override
	public double distanciaAlOrigen() {
		return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.z, 2));
	}
	
	@Override
	public void desplazamiento(Object obj) {		
		this.setX(this.getX() + ((Punto3D) obj).getX());
		this.setY(this.getY() + ((Punto3D) obj).getY());
		this.z += ((Punto3D) obj).z;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punto3D other = (Punto3D) obj;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Punto3D: (" + this.getX() + ", " + this.getY() + ", " + this.z + ")";
	}
	
	@Override
	public Punto3D clone() {
		return new Punto3D(this.getX(), this.getY(), this.z);
	}
	
	

}
