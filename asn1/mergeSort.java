/********************************************************************
 * Name: Maryam Karimi Firozjaei
 * Student #: 250999590
 * Student ID: mkarimif
 ********************************************************************/

public class mergeSort {
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  
	 * This method gets size as input and makes a reversed array of that size
	 * For example, for size 3, it makes A[0] = 3, A[1] = 2, and A[2] = 1
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public static int [] makeReverseArray (int size) {
		int [] array = new int[size];
		for (int i=size; i>0; i--) {
			array[size-i] = i; 
		}
		return array;
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * This method gets a list and sorts it using MergeSort algorithm
	 * This method is recursive and uses merge method to merge 2 arrays
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public static void MergeSort(int[] A){
		if (A.length <2) {
			return;
		}
		int mid = A.length/2;
		int [] left = new int[mid];
		int [] right = new int[A.length-mid];
		for (int i=0; i<mid; i++) {
			left[i] = A[i];
		}
		
		for (int i=mid; i< A.length; i++) {
			right[i-mid] = A[i];
		}
		
		MergeSort(left);
		MergeSort(right);
		merge(left,right,A);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * This is a helping method used by MergeSort which gets two sorted 
	 * arrays (right and left), and merges the two and saves the results
	 * in mergedList
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	private static int[] merge(int[] right,int[] left, int[] mergedList) {
		
		// index for right list
		int i=0;
		// index for left list
		int j=0;
		// index for mergedList
		int k=0;
		
		while (i<right.length && j<left.length) {
			if (right[i]<left[j]) {
				mergedList[k] = right[i];
				i++;
				k++;
			}
			else {
				mergedList[k] = left[j];
				j++;
				k++;
			}
		}
		
		while (i<right.length) {
			mergedList[k] = right[i];
			i++;
			k++;
		}
		
		while (j<left.length) {
			mergedList[k] = left[j];
			j++;
			k++;
		}
		return mergedList;
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * This method displays the first 20 elements of an array
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public static void display(int[] list) {
		if (list.length > 19) {
			String result = "[";
			for (int i =0 ; i<20; i++) {
				int cur = list[i];
				result+=cur+",";
			}
			char[] resultChar = result.toCharArray();
			resultChar[resultChar.length-1] = ']';
			System.out.println(resultChar);
		}
	}
	
	public static void main(String[] args) {
		int size = 0;
		// if user doesn't enter size
		if (args.length == 0) {
			System.out.println("Please enter the array size. (e.g asn1_b 20000)");
		}
		else {
			size = Integer.parseInt(args[0]);
			int [] array = makeReverseArray (size);
			
			// display the first 20 elements of array before sorting
			System.out.println("_________________________________");
			System.out.println("First 20 elements BEFORE sorting:");
			display(array);
			
			// sort the array
			MergeSort(array);
			
			// display the first 20 elements of array after sorting
			System.out.println("First 20 elements AFTER sorting:");
			display(array);
			System.out.println("_________________________________");
		}
	}
}

