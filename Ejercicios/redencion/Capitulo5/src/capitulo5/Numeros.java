package capitulo5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Numeros {

	private ArrayList<Integer> numeros;
	private ArrayList<Boolean> discretos;
	
	public Numeros(ArrayList<Integer> numeros) {
		this.numeros = numeros;
		discretos = new ArrayList<Boolean>();
	}

	public static void main(String[] args) throws IOException {
		Numeros num = leerArchivo("05.in");
		num.resolver();
		num.escribirArchivo("salida5.out");
	}
	
	public void resolver() {
		Iterator<Integer> iter = numeros.iterator();
		
		while (iter.hasNext()) {
			int num = iter.next();
			
			if (num < 1476) {
				this.discretos.add(new Boolean(false));		
			}
			else {
				this.discretos.add(new Boolean(true));			
			}
			
			iter.remove();
			
		}
	
	}
	
	public void escribirArchivo(String path) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(path));
		
		StringBuilder str = new StringBuilder();
	
		
		for (Boolean valor : discretos) {
			if (valor.booleanValue()) {
				str.append("1");
			} else {
				str.append("0");
			}					
		}
		out.println(str.toString());
		out.close();
	}
	
	public static Numeros leerArchivo(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		
		String str = sc.nextLine();
		String arrStr[] = str.split(",");
		sc.close();
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		
		for (int i = 0; i < arrStr.length; i++) {
			numeros.add(Integer.parseInt(arrStr[i].trim()));
		}
		
		return new Numeros(numeros);
	}
	
	
}
