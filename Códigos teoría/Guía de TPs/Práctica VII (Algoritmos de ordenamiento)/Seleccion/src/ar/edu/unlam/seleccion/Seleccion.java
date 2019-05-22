package ar.edu.unlam.seleccion;

public class Seleccion<T extends Comparable<T>> {
	
	private T vec[];
	
	public Seleccion(T vec[]) {
		this.vec = vec;
	}
	
	public static void main(String[] args) {
		Integer[] vec = { 4, 3, 6, 7, 1 };
		
		Seleccion<Integer> s = new Seleccion<Integer>(vec);
		
		s.sort();
		
		s.print();
	}
	
	public void sort()	{
		
		for (int i = 0; i < vec.length; i++) {
			int pMenor = hallarPosMenor(i);
			
			if (pMenor != i) {
				swap(pMenor, i);
			}
		}
				
	}
	
	public int hallarPosMenor(int currentPos) {
		int min = currentPos;
		
		for (; currentPos < vec.length; currentPos++) {
			if (vec[currentPos].compareTo(vec[min]) < 0)
				min = currentPos;
		}
		
		return min;
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
