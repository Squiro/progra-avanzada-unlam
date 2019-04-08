
public class Circulo extends Figura {
	private double radio;
	
	public Circulo (double radio){
		this.radio = radio;	
	}
	
	//Notar el iconito verde que aparece al lado en el IDE
	public double calcularArea(){
		//return pi*radio*radio;
		return Math.PI * Math.pow(radio, 2);
	}
	
}
