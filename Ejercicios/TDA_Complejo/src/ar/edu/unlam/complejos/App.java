package ar.edu.unlam.complejos;

import java.util.Arrays;

public class App {
	
	public static void main(String[] args) {
		Complejo [] complejos =  {
				new Complejo(1,1),
				new Complejo(2,2),
				new Complejo(0,0),
				new Complejo(-3,-3)
		};
		
		
		for (Complejo complejo : complejos) {
			System.out.println(complejo);
		}
		
		System.out.println("----------");
		
		Arrays.sort(complejos, new ComplejosPorParteReal());
		
		for (Complejo complejo : complejos) {
			System.out.println(complejo);
		}
	}

}
