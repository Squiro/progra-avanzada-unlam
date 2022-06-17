package ar.edu.unlam.mergesort;

public class MergeSort {
	
	private int array[];
	
	//https://www.vogella.com/tutorials/JavaAlgorithmsMergesort/article.html
	
	public MergeSort(int array[]) {
		this.array = array;
	}
	
	public static void main(String[] args) {
		int arr[] = { 1, 45, 4, 3, 9, 20, 16, 12 };
		MergeSort m = new MergeSort(arr);
		m.mergeSort(m.array, 0, m.array.length-1);
		m.print();
	}
	
	public void mergeSort(int arr[], int lo, int hi) {
		if (lo < hi) {
			int mid = lo + (hi-lo)/2;
			mergeSort(arr, lo, mid);
			mergeSort(arr, mid+1, hi);
			merge(arr, lo, mid, hi);
		}
	}
	
	public void merge(int arr[], int lo, int mid, int hi) {
		int helper[] = new int[arr.length];
		
		// Copy both parts into the helper array
        for (int i = lo; i <= hi; i++) {
            helper[i] = arr[i];
        }

        int i = lo;
        int j = mid + 1;
        int k = lo;
        
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= mid && j <= hi) {
            if (helper[i] <= helper[j]) {
            	arr[k] = helper[i];
                i++;
            } else {
            	arr[k] = helper[j];
                j++;
            }
            k++;
        }
        
        // Copy the rest of the left side of the array into the target array
        while (i <= mid) {
        	arr[k] = helper[i];
            k++;
            i++;
        }
        // Since we are sorting in-place any leftover elements from the right side
        // are already at the right position.	
	}
	
	public void print() {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}
