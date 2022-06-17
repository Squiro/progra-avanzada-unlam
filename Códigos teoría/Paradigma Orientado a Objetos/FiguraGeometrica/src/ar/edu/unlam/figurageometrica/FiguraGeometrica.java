package ar.edu.unlam.figurageometrica;

public class FiguraGeometrica {
	
	private double area;	
	
	public FiguraGeometrica(double area) 
	{
		this.area = area;
	}
	
	public FiguraGeometrica() 
	{
		this.area = 0;
	}
	
	public static void main(String[] args) {

	}
	
	public double obtenerArea() {
		return this.area;
	}
	
	public void setArea(double area) {
		this.area = area;
	}
}

