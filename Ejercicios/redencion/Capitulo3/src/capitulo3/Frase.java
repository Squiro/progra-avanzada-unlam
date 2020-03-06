package capitulo3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Frase {
	
	private String cifrado;
	private String moldava;
	private String descifrado;
	
	public Frase(String str, String str2) {
		this.cifrado = str;
		this.moldava = str2;
	}
	
	public static void main(String[] args) throws IOException {
		Frase fr = leerArchivo("03.in", "moldv.in");
		fr.resolver();
		fr.escribirArchivo("salida.out");
	}
	
	public void escribirArchivo(String path) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(path));
		
		out.println("La frase encontrada es: ");
		out.println(descifrado);
		out.close();
	}
	
	public void resolver() {		
		int begin = 0, end = 3;
		StringBuilder str = new StringBuilder();
		
		while (end < cifrado.length()) {
			int num1 =  Integer.parseInt(cifrado.substring(begin, end));
			int num2 = Integer.parseInt(moldava.substring(begin, end));			
			
			
			str.append((char) (num1^num2));
			begin = end;
			end+=3;
		}
		
		System.out.println(str.toString());
	
		this.descifrado = str.toString();

	}	
		
	public static Frase leerArchivo(String path, String path2) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		String cif = sc.nextLine();
		
		
		sc.close();
		sc = new Scanner(new FileReader(path2));
		
		Frase fr = new Frase(cif, sc.nextLine());
		sc.close();
		
		return fr;
	}

}
