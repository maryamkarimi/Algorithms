
/***********************************************************************
 * Algorithm to find the connected components in a binary image 
 * using Union-Find data structure
 * Name: Maryam Karimi Firozjaei
 * Student #: 250999590
 ***********************************************************************/

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class asn2 {
	static int [] img = new int [71*71+1];
	static uandf imageSet = new uandf(71*71+1);
	static HashMap<Character,Integer> map = new HashMap<Character, Integer>();
	
	public static void main(String[] args){
		
		// get the input file name
		if (args.length>0) {
			System.out.println("Try again! Input format: asn2 < filename");
			return;
		}
		Scanner sc = new Scanner(System.in);
		int counter = 1;
		
		// read every line of input and initialize the array img
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			for (int j=0; j<line.length(); j++) {
				if (line.charAt(j) == '+') {
					img[counter] = 1;
				}
				else {
					img[counter] = 0;
				}
				counter++;
			}
		}
		sc.close();
		
		// for entries of 1, make_set
		for (int i = 1; i<71*71+1; i++) {
			if (img[i] == 1) {
				imageSet.make_set(i);
			}
		}
		
		// call union_set for entries that are connected
		for (int i = 1; i<71*71+1; i++) {
			if (img[i] == 1) {
				if (img[i+1] == 1 && ((i%71) != 0)){
					imageSet.union_sets(i, i+1);
				}
				if ((i+71<71*71+1) &&img[i+71] ==1) {
					imageSet.union_sets(i, i+71);
				}	
			}
		}
		
		// part 1
		printBinaryInput();
		
		// part 2
		printConnectedComponents();
		
		// part 3
		printComponentInfo();
		
		// part 4
		printWithoutOneTwo();
	}

	
	// Part 1: output the input binary image - walking through the img array
	private static void printBinaryInput() {
		System.out.println("Part 1:");
		for (int i=0; i<71; i++) {
			for (int j=0; j<71; j++) {
				System.out.print(img[i*71+j]);
			}
			System.out.println("");
		}
	}
	
	// Part 2: output the connected component image where each component is labelled with a unique char
	private static void printConnectedComponents() {
		System.out.println("\nPart 2:");
		
		// initialize all chars entries to space
		char [] chars = new char[71*71+1];
		for (int i=0; i<71*71+1; i++) {
			chars[i] = ' ';
		}
		
		int r, ctr = 0;
		for (int i = 1; i<71*71+1; i++) {
			r = imageSet.find_set(i);
			if (chars[r] == ' ' && img[i] == 1) {
				// set chars[r] to a unique letter based on ctr, starting from a
				chars[r] = (char) ('a' + ctr);
				ctr++;
			}
		}
		
		
		// print the results while updating the hashmap which will be used in part 3
		ctr = 1;
		for (int i=0; i<71; i++) {
			for (int j=0; j<71; j++) {
				r = imageSet.find_set(ctr);
				if (chars[r] == ' ') {
					System.out.print(" ");
				}
				else {
					System.out.print(chars[r]);
					if (map.containsKey(chars[r])) {
						map.put(chars[r], map.get(chars[r])+1);
					}
					else {
						map.put(chars[r], 1);
					}
				}
				ctr++;
			}
			System.out.println("");
		}
	}
	
	// Part 3: output a list sorted by component size, where each line of the list contains the size and the label of a component
	private static void printComponentInfo() {
		System.out.println("\nPart 3:");
		
		// make a new hashset that is sorted based on values
		LinkedHashMap<Character,Integer> sorted_map = map.entrySet()
				.stream()
                .sorted((Map.Entry.<Character, Integer>comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    
		// output the info from hashmap
		for (char c:sorted_map.keySet()) {
			System.out.println(c+":\t"+map.get(c));
		}
	}
	
	// Part 4: same as 2 with the connected component whose size equals to one or two removed.
	private static void printWithoutOneTwo() {
		System.out.println("\nPart 4:");
		
		// initialize all chars entries to space
		char [] chars = new char[71*71+1];
		for (int i=0; i<71*71+1; i++) {
			chars[i] = ' ';
		}
		
		int r, ctr = 0;
		for (int i = 1; i<71*71+1; i++) {
			r = imageSet.find_set(i);
			if (chars[r] == ' ' && img[i] == 1) {
				// set chars[r] to a unique letter based on ctr, starting from a
				chars[r] = (char) ('a' + ctr);
				ctr++;
			}
		}

		ctr = 1;
		for (int i=0; i<71; i++) {
			for (int j=0; j<71; j++) {
				r = imageSet.find_set(ctr);
				if (chars[r] == ' ') {
					System.out.print(" ");
				}
				else {
					// if the size is not 1 or 2, then display it
					if (map.get(chars[r])>2) {
						System.out.print(chars[r]);
					}
				}
				ctr++;
			}
			System.out.println("");
		}
	}
	
}
