package ar.edu.unlam.operaciones;

public class Operaciones {

	public static void main(String[] args) {	
		Operaciones op = new Operaciones();
		
		System.out.println("Num OP: " + op.operaciones(2));
	}
	
	public int operaciones(int n) {
			int o = 0;
			for (;n > 0;o++)
				n = n%2 < 1 ? n/2 : n-1;
			return o;
	}
}
