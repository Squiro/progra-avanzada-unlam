package figuras;

public abstract class Figura {
	
	protected String color;
	
	/* Java crea un constructor por defecto implicitamente (el compilador lo hace). Si yo creo
	 * un constructor con o sin parametros, este constructor deja de existir y no lo puedo usar implicitamente.
	 * Se puede crear un constructor por defecto a mano, explicitamente, y lo puedo utilizar sin dramas.
	 * 
	 * 
	 * En una herencia, primero se inicializan las variables de padre con su constructor, y luego
	 * las del hijo con el constructor de la clase hijo. El fin de los constructores es ese: inicializar 
	 * las variables.
	 * 
	 * Con super() yo invoco al constructor de la clase padre explicitamente.
	 * 
	 * Existe la posibilidad de crear un constructor privado, pero en casos especificos.
	 */
	
	public Figura (String color) {
		this.color = color;
	}
	
	/*public double calcularArea() {
		return 0;
	}*/
	
	//Método abstracto
	public abstract double calcularArea();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String toString() {
		return this.color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
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
		Figura other = (Figura) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}
	
	

}
