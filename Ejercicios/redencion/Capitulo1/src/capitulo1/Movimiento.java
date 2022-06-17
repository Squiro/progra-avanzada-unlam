package capitulo1;

public class Movimiento {
	
	private int rotaciones;
	private String sentido;
	
	public Movimiento (int rotaciones, String sentido) {
		this.rotaciones = rotaciones;
		this.sentido = sentido;
	}

	public int getRotaciones() {
		return rotaciones;
	}

	public void setRotaciones(int rotaciones) {
		this.rotaciones = rotaciones;
	}

	public String getSentido() {
		return sentido;
	}

	public void setSentido(String sentido) {
		this.sentido = sentido;
	}

}
