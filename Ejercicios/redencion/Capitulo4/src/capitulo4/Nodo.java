package capitulo4;

public class Nodo {

	private String ciudad;
	private int numNodo;
	
	public Nodo (String ciudad, int numNodo) {
		this.ciudad = ciudad;
		this.numNodo = numNodo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getNumNodo() {
		return numNodo;
	}

	public void setNumNodo(int numNodo) {
		this.numNodo = numNodo;
	}
	
}
