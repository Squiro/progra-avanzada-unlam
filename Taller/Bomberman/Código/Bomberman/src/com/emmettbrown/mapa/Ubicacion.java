package com.emmettbrown.mapa;

public class Ubicacion implements Comparable<Ubicacion> {
	private int posX;
	private int posY;

	public Ubicacion(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	@Override
	public int compareTo(Ubicacion ubicacion) {
		int cmp;
		if((cmp = this.posX - ubicacion.posX) == 0) 
			return this.posY - ubicacion.posY;
		return cmp;
	}

	@Override
	public String toString() {
		return "Ubicacion [posX=" + posX + ", posY=" + posY + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posX;
		result = prime * result + posY;
		return result;
	}
	public Ubicacion clone() {
		return new Ubicacion(getPosX(),getPosY());
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
		if (posX != other.posX)
			return false;
		if (posY != other.posY)
			return false;
		return true;
	}

	public void cambiarPosY(int i) {
		this.posY += i;	
	}

	public void cambiarPosX(int i) {
		this.posX += i;
	}
	
}
