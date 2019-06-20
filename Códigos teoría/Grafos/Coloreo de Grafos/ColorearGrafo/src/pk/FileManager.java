package pk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class FileManager {
	public static int[][] leerArchivo(String path, Grafo g) {
		Scanner scanner = null;

		try {
			scanner = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		scanner.useLocale(Locale.ENGLISH);

		int cantNodos = scanner.nextInt();
		int[][] res = new int[cantNodos + 1][cantNodos + 1];
		llenarMatrizInfinitos(res);		
		for (int i = 0; i < cantNodos; i++) {
			int nodoIni = scanner.nextInt();
			int nodoFin = scanner.nextInt();
			while(nodoFin != -1) {
				res[nodoIni][nodoFin] = 1;	
				nodoFin = scanner.nextInt();
			}
		}	
		scanner.close();
		
		return res;
	}

	private static void llenarMatrizInfinitos(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				m[i][j] = Integer.MAX_VALUE;
			}
		}
	}
}
