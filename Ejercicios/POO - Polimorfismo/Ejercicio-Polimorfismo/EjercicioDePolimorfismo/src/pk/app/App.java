package pk.app;

import java.io.IOException;
import java.util.ArrayList;
import pk.sist.Usuario;

public class App {
	
	
	public static void main(String[] args) throws IOException{
		// USUARIOS
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("1", "1", 0));
		usuarios.add(new Usuario("1", "1", 1));
		usuarios.add(new Usuario("1", "1", 2));
		// VALIDACIÓN USUARIOS
		ingresoSistema(usuarios);
	}

	private static void ingresoSistema(ArrayList<Usuario> usuarios) {
		for ( Usuario u : usuarios) {
			System.out.println("Resultado validacion: " + u.validar());
		}
	}
}
