package ar.edu.unlam.motanañacomo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Montaña {

	private int cimasY[];
	private int cimasX[];
	private int vallesY[];
	private int vallesX[];
	private Carrito carrito;
	private int indiceCimaSig;
	private int indiceValleSig;
	
	
	public Montaña(int cimasY[], int vallesY[], int cantTotal, Carrito car) {
		this.cimasY = cimasY;
		this.cimasX = new int[cantTotal];
		this.vallesY = vallesY;
		this.vallesX =  new int[cantTotal];		
		this.carrito = car;
	}
	
	public static void main(String[] args) throws IOException {
		Montaña mon = leerArchivo("entrada.in");
		mon.calcularCoordenadasX();
		mon.calcularYCarrito();
		mon.calcularPosFinalCarrito();
		mon.escribirResultados("salida.out");
	}
	
	public void escribirResultados(String path) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(path));		
		out.println(carrito.getX() + " " + carrito.getY());
		out.close();
	}
	public void calcularPosFinalCarrito() {
		int posX = 0;
		int posY = carrito.getY();
		
		//Si se encuentra en una subida (rampa), el carrito se queda ahí, quieto
		if (seEncuentraEnSubida()) {
			return;
		}
		
		while (posY >= cimasY[indiceCimaSig] && indiceValleSig < vallesY.length && indiceCimaSig < cimasY.length) {
			//La diferencia entre el valle y la cima, mas 1 (porque pasamos por un valle)
			posY -= cimasY[indiceCimaSig] - vallesY[indiceValleSig] + 1;
			indiceValleSig++;
			//Mismo que arriba, solo que con el valle siguiente
			posY += cimasY[indiceCimaSig] - vallesY[indiceValleSig];
			indiceCimaSig++;
		}
		//Siempre vamos a terminar en una bajada, asi que vamos a pasar por un valle sí o sí
		//Restamos 1 por eso
		posY--;		
		//La posición X va a estar dada por el último valle al que pasamos, más la diferencia entre la 
		//posición Y de nuestro carrito menos la posY del valle anterior		
		posX = vallesX[indiceValleSig] + posY - vallesY[indiceValleSig];
		carrito.setX(posX);
		carrito.setY(posY);
	}	
	
	public boolean seEncuentraEnSubida() {
		return cimasX[indiceCimaSig] < vallesX[indiceValleSig];
	}
	
	public void calcularYCarrito() {
		int posCima = hallarSiguienteCima();
		int posValle = hallarSiguienteValle();
		
		this.indiceCimaSig = posCima;
		this.indiceValleSig = posValle;
		
		//Si la cima que le sigue al carrito es mas grande que el primer valle luego de este, 
		//quiere decir que el carrito se encuentra entre una CIMA - VALLE 
		if (cimasX[posCima] > vallesX[posValle]) {
			carrito.setY(vallesX[posValle]-carrito.getX() + vallesY[posValle]);			
		}
		//Caso contrario, se encuentra entre un VALLE - CIMA
		else {
			carrito.setY(carrito.getX()-vallesX[posValle-1] + vallesY[posValle-1]);
		}
	}
	
	public int hallarSiguienteCima() {
		for (int i = 0; i < cimasX.length; i++) {
			if (cimasX[i] >= carrito.getX())
				return i;
		}
		return 0;
	}
	
	public int hallarSiguienteValle() {
		for (int i = 0; i < vallesX.length; i++) {
			if (vallesX[i] >= carrito.getX())
				return i;
		}
		return 0;
	}
	
	public void calcularCoordenadasX() {		
		//La primera es 0, empezamos desde 1
		for (int i = 1; i < cimasY.length; i++) {
			//El valleX es la suma de la X de la cima anterior mas la diferencia entre alturas de
			//la cima anterior y el valle actual
			vallesX[i] = cimasX[i-1] + (cimasY[i-1]-vallesY[i]);
			//La cimaX es la suma del valle anterior mas la diferencia entre la altura de la cima actual
			//menos el valle anterior
			//Notar que, como el valle anterior lo calculamos arriba, el indice es i
			cimasX[i] = vallesX[i] + cimasY[i]-vallesY[i];
		}
	}
	
	public static Montaña leerArchivo(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		
		int cantTotal = sc.nextInt();
		cantTotal = cantTotal/2+1;
		int cimasY[] = new int[cantTotal];
		int vallesY[] = new int[cantTotal];
		
		cimasY[0] = sc.nextInt();
		
		for (int i = 1; i < cimasY.length; i++) {
			vallesY[i] = sc.nextInt();
			cimasY[i] = sc.nextInt();
		}
		
		int carritoX = sc.nextInt();
		sc.close();
		
		return new Montaña(cimasY, vallesY, cantTotal, new Carrito(carritoX));		
	}
	
	
}
