
public class Cuadrado extends Figura {
	private double lado;
	//private double area;
	
	public Cuadrado (double lado){
		this.lado = lado;
	}
	
	@Override
	public double calcularArea(){
		return lado*lado;	
	}

}
