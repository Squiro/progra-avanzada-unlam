package ar.edu.unlam.burbujeo;

public class Burbujeo<T extends Comparable<T>> {

	private T vec[];
	
	public Burbujeo(T vec[]) {
		this.vec = vec;
	}
	
	public static void main(String[] args) {
		//Integer[] vec = { 4, 3, 6, 7, 1 };
		Integer[] vec = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		
		Burbujeo<Integer> b = new Burbujeo<Integer>(vec);
		
		b.sort();
		b.print();
	}
	
	
	public void sort() {
		boolean swap = true;
		int n = vec.length;
		
		while (swap) {
			swap = false;
			for (int i = 1; i <= n-1; i++) {
				if (vec[i-1].compareTo(vec[i]) > 0) {
					swap = true;
					swap(i-1, i);
				}
			}
		}
	}
	
	public void swap(int ant, int pos) {
		T aux = vec[ant];
		vec[ant] = vec[pos];
		vec[pos] = aux;
	}
	
	public void print() {
		for (int i = 0; i < vec.length; i++) {
			System.out.println(vec[i].toString());
		}
	}
	
}
