package figuras;

public class Cuadrado extends Figura {
	private double lado;
	//private double area;
	
	public Cuadrado (double lado, String color){
		super(color); //Invoca al constructor de la clase padre
		this.lado = lado;
	}
	
	@Override
	public double calcularArea(){
		return lado*lado;	
	}
	
	@Override
	public String toString() {
		return "Cuadrado. Lado: " + lado + " Color:" + super.toString();
	}
}
