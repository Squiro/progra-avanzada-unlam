package ar.edu.unlam.ejercicio;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Anagrama {

	private String[] diccionario;
	private Map<String, String> anagramas;
	
	public Anagrama(String[] diccionario) {
		this.diccionario = diccionario;
		this.anagramas = new HashMap<String, String>();
	}
	
	public static void main(String[] args) {
		String[] diccionario = {
				"cava",
				"empresa",
				"pote",
				"torta",
				"tope",
				"trota",
				"vaca"
		};
		
		Anagrama ana = new Anagrama(diccionario);
		ana.buscarPares();
		ana.imprimirPares();
	}
	
	public void buscarPares() {
		String[] ordenado = ordenarDiccionario();
		
		
		
		for (int i = 0; i < ordenado.length; i++) {
			for (int j = i+1; j < ordenado.length; j++) {
				//System.out.println(ordenado[i] + " - " + ordenado[j]);
				if (ordenado[i].equals(ordenado[j]))
					anagramas.put(diccionario[i], diccionario[j]);
			}
		}
	}
	
	public String[] ordenarDiccionario() {
		String[] ordenado = new String[diccionario.length];
		
		for (int i = 0; i < diccionario.length; i++) {
			char[] str = diccionario[i].toCharArray();
			Arrays.sort(str);
			ordenado[i] = new String(str);
		}
		
		return ordenado;
	}
	
	public void imprimirPares() {
	    Iterator<Entry<String, String>> it = anagramas.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
	        System.out.println(pair.getKey() + " - " + pair.getValue());
	    }
	}
	
	
}
