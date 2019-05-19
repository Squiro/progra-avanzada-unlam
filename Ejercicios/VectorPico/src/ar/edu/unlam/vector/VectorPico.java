package ar.edu.unlam.vector;

public class VectorPico {
	
	private int[] vector;
	
	public VectorPico(int[] vec) {
		this.vector = vec;
	}
	
	public static void main(String[] args) {
		int vec[] = { 1, 2, 3, 4, 3, 2, 1 };
		
		VectorPico vp = new VectorPico(vec);
		
		System.out.println(vp.hallarPico(0, vec.length));
	}
	
	public int hallarPico(int limInf, int limSup) {
		int m = limInf+limSup/2;
		
		if (vector[m] > vector[m-1] && vector[m] > vector[m+1])
			return m;
		
		if (vector[m-1] < vector[m] && vector[m+1] > vector[m])
			return hallarPico(m+1, limSup);
		
		return hallarPico(limInf, m-1);
	}
}
