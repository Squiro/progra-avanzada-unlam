package ar.edu.unlam.operaciones;

public class Operaciones {

	public static void main(String[] args) {	
		Operaciones op = new Operaciones();
		
		System.out.println(op.operaciones(2017));
	}
	
	public int operaciones(int n) {
		int o = 0;
		while (n != 0) {
			n = n%2 == 0 ? n/2 : n-1;
			o++;
		}
		return o;
	}
}
