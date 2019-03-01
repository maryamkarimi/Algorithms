
/***********************************************************************
 * A Union-Find data structure
 * Name: Maryam Karimi Firozjaei
 * Student #: 250999590
 ***********************************************************************/
public class uandf {

	boolean is_final;
	int n;
	int [] rank, parent;
	
	// constructs an union-find data type with n elements, 1, 2, . . . , n.
	public uandf(int n) {
		is_final = false;
		this.n = n;
		parent = new int[n];
		rank = new int[n];
	}
	
	// creates a new set whose only member (and thus representative) is i.
	public void make_set(int i) {
		if (!is_final) {
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	// unites the dynamic sets that contains i and j, respectively,
	// into a new set that is the union of these two sets.	
	public void union_sets(int i, int j) {
		if (!is_final) {
			link(find_set(i), find_set(j));
		}
	}
	
	// helper method for union_sets
	private void link(int i, int j) {
		if (rank[i] > rank[j]) {
			parent[j] = i;
		}
		else if (rank[i] < rank[j]){
			parent[i] = j;
		}
		else if (i!=j) {
			parent[j] = i;
			rank[i]++;
		}
	}
	
	// returns the representative of the set containing i.
	public int find_set(int i) {
		if (parent[i] != i) {
			parent[i] = find_set(parent[i]);
		}
		return parent[i];
	}
	
	// returns the total number of current sets
	public int final_sets() {
		
		// finalizes the current sets 
		is_final = true;
		
		// resets the representatives of the sets
		for (int i =1; i<n; i++ ) {
			if (parent[i] != 0) {
				find_set(i);
			}
		}
		
		int counter = 1;
		for (int i=1; i<n; i++) {
			if (parent[i] == i) {
				parent[i] = counter;
				rank[i] = 1;
				counter++;
			}
			else {
				rank[i] = 0;
			}
		}
		
		for (int i=1; i<n; i++ ) {
			if (rank[i] == 0 && parent[i]>0) {
				parent[i] = parent[parent[i]];
			}
		}
		
		return counter-1;
	}
}
