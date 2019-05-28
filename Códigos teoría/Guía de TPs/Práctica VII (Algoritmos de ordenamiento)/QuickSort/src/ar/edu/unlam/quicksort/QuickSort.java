package ar.edu.unlam.quicksort;

public class QuickSort {

	private int array[];
	
	public QuickSort(int array[]) {
		this.array = array;
	}
	
	public static void main(String[] args) {
		int arr[] = { 1, 45, 4, 3, 9, 20, 16, 12 };
		QuickSort q = new QuickSort(arr);
		q.quicksort(q.array, 0, q.array.length-1);
		q.print();
	}
	
	
	public void quicksort(int arr[], int lo, int hi) {
		if (lo < hi) {
			int p = particion(arr, lo, hi);
			quicksort(arr, lo, p);
			quicksort(arr, p + 1, hi);
		}
		
	}
	
	public int particion(int arr[], int lo, int hi) {
		int pivot = arr[lo + (hi-lo)/2];
		
		int i = lo-1;
		int j = hi+1;
		
		while(true) {
			do {
				i++;
			} while (arr[i] < pivot);
			
			do {
				j--;
			} while (arr[j] > pivot);
			
			if ( i >= j) {
				return j;
			}
			
			swap(arr, i, j);
		}
	}
	
	public void swap(int arr[], int i, int j) {
		int aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}
	
	public void print() {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
	
}
