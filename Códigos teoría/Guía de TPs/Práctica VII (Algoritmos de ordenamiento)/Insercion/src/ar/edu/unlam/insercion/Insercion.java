package ar.edu.unlam.insercion;

public class Insercion<T extends Comparable<T>> {
	
	private T vec[];
	
	public Insercion(T vec[]) {
		this.vec = vec;
	}
	
	public static void main(String[] args) {
		//Integer[] vec = { 4, 3, 6, 7, 1 };
		Integer[] vec = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		
		Insercion<Integer> i = new Insercion<Integer>(vec);
		
		i.sort();
		i.print();
	}
	
	public void sort() {
		int i = 1;
		int pass = 0;
		while (i < vec.length) {
			T x = vec[i];
			int j = i-1;
			
			while (j >= 0 && vec[j].compareTo(x) > 0) {
				pass++;
				vec[j+1] = vec[j];
				j--;
			}
			vec[j+1] = x;
			i++;
		}
		
		System.out.println(pass);

	}
	
	public void swap(int i, int j) {
		T aux = vec[i];
		vec[i] = vec[j];
		vec[j] = aux;
	}
	
	public void print() {
		for (int i = 0; i < vec.length; i++) {
			System.out.println(vec[i].toString());
		}
	}

}


