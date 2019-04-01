
public class App {

	public static void main(String[] args) {
		int vector[] = { 1, 3, 5, 5, 5, 5, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4 };
		int i = 0, k = 0;
		int intercambiables[] = new int[vector.length];
		int estampillaActual;
		int apariciones;

		while (i < vector.length) 
		{
			estampillaActual = vector[i];
			apariciones = 1;
			
			for (int b = i + 1; b < vector.length; b++)
				if (vector[b] == estampillaActual)
					apariciones++;
		
			if (apariciones > 2)
				intercambiables[k] = estampillaActual;
			i++;
			k++;
		}
		
		for (int h = 0; h < k; h++)
			
		if (intercambiables[h] != 0)
			System.out.print(intercambiables[h] + " ");

	}

}
