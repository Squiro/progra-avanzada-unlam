import java.util.ArrayList;

public class Dibujo {
	
//	private ArrayList<Cuadrado> cuadrados;
//	private ArrayList<Circulo> circulos;
	private ArrayList<Figura> figuras;
	private final double CM2_POR_POMO = 25;

	public Dibujo (){
//		this.cuadrados = new ArrayList<Cuadrado>();
//		this.circulos = new ArrayList<Circulo>();
		this.figuras = new ArrayList<Figura>();
	}
	
//	public void agregarCuadrado(Cuadrado cua){
//		cuadrados.add(cua);
//	}
//	
//	public void agregarCirculo(Circulo cir){
//		circulos.add(cir);	
//	}
//	
	public void agregarFigura(Figura fig){
		figuras.add(fig);	
	}
	
	public double calcularTemp(){
		//double tempAUtilizar = 0;
		double areaTot = 0;
		
		/*for (Circulo circulo : circulos) {
			areaTot += circulo.calcularArea();
		}
		
		for (Cuadrado cuadrado : cuadrados) {
			areaTot+=cuadrado.calcularArea();
		}*/
		
		for (Figura fig : figuras) {
			areaTot += fig.calcularArea();
		}
		return Math.ceil(areaTot/CM2_POR_POMO);
	}
	
	
}
