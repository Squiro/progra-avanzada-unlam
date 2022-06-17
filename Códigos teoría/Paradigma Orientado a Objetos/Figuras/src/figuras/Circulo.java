package figuras;

public class Circulo extends Figura {
	private double radio;
	
	public Circulo (double radio, String color) {		
		//La primera instrucción del constructor de la clase hijo es la llamada al constructor
		//de la clase padre. Se hace implicitamente invocando al constructor por defecto del padre.
		super(color); //Invoca al constructor de la clase padre
		
		this.radio = radio;	
	}
	
	public String getColor() {
		return this.color;
	}
	
	//Notar el iconito verde que aparece al lado en el IDE
	@Override
	public double calcularArea(){
		//return pi*radio*radio;
		return Math.PI * Math.pow(radio, 2);
	}
	
	@Override
	public String toString() {
		return "Circulo. Radio: " + radio + " Color:" + super.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(radio);
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
		Circulo other = (Circulo) obj;
		if (Double.doubleToLongBits(radio) != Double.doubleToLongBits(other.radio))
			return false;
		return true;
	}	
}
