
/********************************************************************
 * Name: Maryam Karimi Firozjaei
 * Student #: 250999590
 * Student ID: mkarimif
 ********************************************************************/

public class insertionSort {
	
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
	 * This method gets a list and sorts it using InsertionSort algorithm
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public static void InsertionSort(int[] list){
		for (int j = 1; j<list.length; j++) {
			int key = list[j];
		
			int i = j-1;
			while (i>-1 && list[i]>key) {
				list[i+1] = list[i];
				i--;
			}
			
			list[i+1] = key;	
		}
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
			System.out.println("Please enter the array size. (e.g asn1_a 20000)");
		}
		else {
			size = Integer.parseInt(args[0]);
			int [] array = makeReverseArray (size);
	
			// display the first 20 elements of array before sorting
			System.out.println("_________________________________");
			System.out.println("First 20 elements BEFORE sorting:");
			display(array);
			
			// sort the array
			InsertionSort(array);
			
			// display the first 20 elements of array after sorting
			System.out.println("First 20 elements AFTER sorting:");
			display(array);
			System.out.println("_________________________________");
		}
	}
}

