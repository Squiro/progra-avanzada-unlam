package ar.edu.unlam.punto2D;

public class App {
	
	public static void main(String[] args) {
		Punto2D puntito = new Punto2D(20, 20);		
		System.out.println(puntito.toString());
		puntito.desplazamiento(puntito);
		System.out.println(puntito.toString());
		System.out.println(puntito.distanciaAlOrigen());
		System.out.println("Equals: " + puntito.equals(puntito));		
		Punto3D puntote = new Punto3D(puntito.getX(), puntito.getY(), 30);
		System.out.println(puntote.toString());
		puntote.desplazamiento(puntote);
		System.out.println(puntote.toString());
		System.out.println(puntote.distanciaAlOrigen());
		Punto3D puntote2 = new Punto3D(puntote.getX(), puntote.getY(), puntote.getZ());
		System.out.println("Equals: " + puntote.equals(puntote2));
		
		/*puntito = puntote;
		System.out.println(puntito.getX());*/
	}

}
