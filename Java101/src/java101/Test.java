package java101;

public class Test {
	
	static int[] getMode(int[] input) {
		int[] occ = new int[101];
		boolean[] visited = new boolean[101];
		
		for (int i = 0; i < 101; i++) {
			occ[i] = 0;
			visited[i] = false;
		}
		
		int numDistinct = 0;
		
		for (int i = 0; i < input.length; i++) {
			int num = input[i];
			occ[num]++;
			if (!visited[num]) {
				visited[num] = true;
				numDistinct++;
			}
		}
				
		int mostOccurrences = 0;
		int numOfModes = 0;
		
		for (int numOccurrences : occ) {
			if (numOccurrences > mostOccurrences) {
				mostOccurrences = numOccurrences;
				numOfModes = 1;
			} else if (numOccurrences == mostOccurrences) {
				numOfModes++;
			} 
		}
		
		if (mostOccurrences == 0 || numOfModes == numDistinct) {
			return new int[0];
		}
		int[] sol = new int[numOfModes];
		int solIndex = 0;
		for (int i = 0; i < occ.length; i++) {
			int occurrences = occ[i];
			if (occurrences == mostOccurrences) {
				sol[solIndex] = i;
				solIndex++;
			}
		}
		return sol;
	}
	
	static void print(int[] arr) {
		if (arr.length == 0) {
			System.out.println("[]");
			return;
		}
		
		System.out.print("[");
		for (int i = 0; i < arr.length - 1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[arr.length - 1] + "]");
	}
	
	public static void main(String[] args) {
		int[] sol;
		
		// No mode
		int[] test0 = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
		sol = getMode(test0);
		print(sol);
		
		// Empty input string
		int[] test1 = new int[0];
		sol = getMode(test1);
		print(sol);
		
		// one mode
		int[] test2 = {1, 1, 1, 2, 3, 4, 5, 5};
		sol = getMode(test2);
		print(sol);
		
		// multiple modes
		int[] test3 = {1, 1, 2, 2, 3, 4, 4};
		sol = getMode(test3);
		print(sol);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
