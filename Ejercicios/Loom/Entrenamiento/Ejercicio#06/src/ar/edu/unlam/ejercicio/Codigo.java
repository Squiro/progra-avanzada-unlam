package ar.edu.unlam.ejercicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Codigo {

	static final String nombres[] = {
			"Abdul, Abad - 111",
			"Gomez, Juan - 222",
			//"Gomez, Jose - 232",  //comentar y descomentar para ver la magia
			"Martinez, Carlos - 333",
			"Suriano, Jose - 444"
	};
	
	static Map<Character, Integer> map;	
	private String codigo;
	
	public Codigo(String codigo) {
		this.codigo = codigo;
		map = establecerMapeo();
	}

	public static void main(String[] args) {
		Codigo cod = new Codigo("46639*5*");
		System.out.println(cod.hallarTelefono());
		
	}
	
	public String hallarTelefono() {
		List<String> listado = new ArrayList<String>();		
		
		for (int i = 0; i < nombres.length; i++) {
			if (decodificar(nombres[i]).equals(codigo))
				listado.add(nombres[i]);
		}

		if (listado.size() > 1) {
			return "Hay dos o más personas con el mismo código. Se necesita más información";
		}	

		return hallarNumero(listado.get(0));
	}		
		
	static Map<Character, Integer> establecerMapeo() {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		map.put('a', 2);
		map.put('b', 2);
		map.put('c', 2);
		map.put('d', 3);
		map.put('e', 3);
		map.put('f', 3);
		map.put('g', 4);
		map.put('h', 4);
		map.put('i', 4);
		map.put('j', 5);
		map.put('k', 5);
		map.put('l', 5);
		map.put('m', 6);
		map.put('n', 6);
		map.put('ñ', 6);
		map.put('o', 6);
		map.put('p', 7);
		map.put('q', 7);
		map.put('r', 7);
		map.put('s', 7);
		map.put('t', 8);
		map.put('u', 8);
		map.put('v', 8);
		map.put('w', 9);
		map.put('x', 9);
		map.put('y', 9);
		map.put('z', 9);
		
		return map;
	}
	
	public String decodificar(String nombre) {
		int cant = 0;
		
		nombre = nombre.toLowerCase();
		
		while (nombre.charAt(cant) != ' ') {
			cant++;
		}
		
		cant+=2;		
		
		char[] decodif = new char[cant];
		int i;
		for (i = 0; i < cant-3; i++) {
			decodif[i] = Character.forDigit(map.get(nombre.charAt(i)), 10); 
		}
		
		decodif[i] = '*';
		decodif[i+1] = Character.forDigit(map.get(nombre.charAt(i+2)), 10); 
		decodif[i+2] = '*';		

		return new String(decodif);
	}
	
	public String hallarNumero(String nombre) { 
		int start = 0;
		
		while (!Character.isDigit(nombre.charAt(start))) {
			start++;
		}
		
		return nombre.substring(start, nombre.length());
	}
}
