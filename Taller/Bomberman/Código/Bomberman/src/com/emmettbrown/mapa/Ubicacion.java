package com.emmettbrown.mapa;

public class Ubicacion implements Comparable<Ubicacion> {
	private double posX;
	private double posY;

	public Ubicacion(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public double getPosX() {
		return this.posX;
	}

	public double getPosY() {
		return this.posY;
	}

	@Override
	public int compareTo(Ubicacion ubicacion) {
		double cmp;
		if((cmp = this.posX - ubicacion.posX) == 0) 
			return (int)((this.posY - ubicacion.posY)*100);
		return (int)(cmp*100);
	}

	@Override
	public String toString() {
		return "Ubicacion [posX=" + posX + ", posY=" + posY + "]";
	}

	public Ubicacion clone() {
		return new Ubicacion(getPosX(),getPosY());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(posX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(posY);
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
		Ubicacion other = (Ubicacion) obj;
		if (Double.doubleToLongBits(posX) != Double.doubleToLongBits(other.posX))
			return false;
		if (Double.doubleToLongBits(posY) != Double.doubleToLongBits(other.posY))
			return false;
		return true;
	}

	public void cambiarPosY(double i) {
		this.posY += i;	
	}

	public void cambiarPosX(double i) {
		this.posX += i;
	}
	
}
