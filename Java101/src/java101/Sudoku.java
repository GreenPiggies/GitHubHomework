package java101;
/**
 * This class creates a Sudoku board. //TODO: Fix
 * A Sudoku board is a 9 by 9 grid filled with the numbers 1 to 9. This is represented using a two-dimensional array. 
 * In a solved Sudoku puzzle, every column, row, and subsquare (found by dividing the board into 9 equal squares) should be a legal permutation of numbers from 1 to 9.
 * 
 * TODO: Add defition of permutation, completion -> valid solution
 *
 * The method used to check for completion is called checkBoard. It iterates through the entire board, checking all rows, columns, and subsquares. It uses the isPermutation method to check if each is a legal permutation.
 * If one is an invalid permutation, the board is not a valid solution and false is returned. If all are valid permutations, than true is returned.
 * @author Wesley
 *
 */
public class Sudoku 
{
	
	private int[][] sudokuBoard;
	
	/**
	 * Constructs a sudoku board.
	 * @param sudoku Determines the numbers on the sudoku puzzle. //TODO: FIX
	 */
	public Sudoku(int[][] sudoku)
	{
		sudokuBoard = sudoku;
	}
	/**
	 * Checks if sudoku board is a permutation. //TODO: FIX permutation -> valid solution
	 * @return True if board is permutation, false if not.
	 */
	 //TODO: Print out at end how may of each is wrong...
	public boolean checkBoard()
	{
		for (int row = 0; row < sudokuBoard.length; row++)
		{
			if (!isPermutation(sudokuBoard[row]))
			{
				return false;
			}
			int[] columnArray = new int[sudokuBoard.length];
			int[] subSquareArray = new int[sudokuBoard.length];
			for (int column = 0; column < sudokuBoard.length; column++)
			{
				columnArray[column] = sudokuBoard[row][column];
				subSquareArray[column] = sudokuBoard[(row / 3) * 3 + (column / 3)][(row % 3) * 3 + (column % 3)];
			}
			if (!isPermutation(columnArray))
			{
				return false;
			}
			if (!isPermutation(subSquareArray))
			{
				return false;
			}
		}
		return true;
	}
	/**
	 * Checks if an integer array is a permutation. //TODO: Explain permutation and return
	 * @param inputArray The array to be checked.
	 * @return True if inputArray is a permutation, false if not.
	 */
	 //CLEAN
	public boolean isPermutation(int[] inputArray)
	{
		int min = inputArray[0];
		int max = inputArray[0]; 
		
		for (int index = 0; index < inputArray.length; index++)
		{
			bins[index] = false;
			if (inputArray[index] < min)
			{
				min = inputArray[index];
			}
			if (inputArray[index] > max)
			{
				max = inputArray[index];
			}
		}
		if (max - min != inputArray.length - 1) //Reverse
		{
			return false;
		} 
		boolean[] bins = new boolean[inputArray.length];
		
		for (int index = 0; index < inputArray.length; index++)
		{
			bins[index] = false;
		}
		
		for (int index = 0; index < inputArray.length; index++)
		{
			if (bins[inputArray[index] - min])
			{
				return false;
			} 
			bins[inputArray[index] - min] = true;
		}
		return true;
	}
	//REFERENCES DOWN HERE
	/**
	 * Gets a subsquare of the sudoku board.
	 * @param squareIndex The square number. It would look something like this: 
	 * 0 | 1 | 2
	 * ----------
	 * 3 | 4 | 5
	 * ----------
	 * 6 | 7 | 8
	 * @return The row in the form of an int array.
	 */
	public int[] getSubSquare(int squareIndex)
	{
		int[] squareArray = new int[sudokuBoard.length];
		int index = 0;
		//Make for loop for index
		for (int row = ((squareIndex / 3) * 3); row < (((squareIndex) / 3) * 3 + 3); row++)
		{
			for (int column = ((squareIndex % 3) * 3); column < (((squareIndex % 3)) * 3 + 3); column++)
			{
				squareArray[index] = sudokuBoard[row][column];
				index++;
			}
		}
		return squareArray;
	}
	/**
	 * Gets a row of the sudoku board.
	 * @param rowIndex The row number.
	 * @return The row in the form of an int array.
	 */
	public int[] getRow(int rowIndex)
	{
		return sudokuBoard[rowIndex];
	}
	/**
	 * Gets a column of the sudoku board.
	 * @param colIndex The column number.
	 * @return The column in the form of an int array.
	 */
	public int[] getColumn(int colIndex)
	{
		int[] columnArray = new int[sudokuBoard.length];
		for (int row = 0; row < sudokuBoard.length; row++)
		{
			columnArray[row] = sudokuBoard[row][colIndex];
		}
		return columnArray;
	}
	
	/** TESTING METHODS START HERE **/
	
	/**
	 * Switches 2 random elements in a sudoku puzzle.
	 * @param puzzle The sudoku puzzle in which 2 elements are swapped.
	 * @return The new swapped puzzle.
	 */
	public static int[][] damage(int[][] puzzle)
	{
		int[][] newPuzzle = puzzle.clone();
		int row = (int) (Math.random() * puzzle.length);
		int col = (int) (Math.random() * puzzle[puzzle.length - 1].length);
		int change;
		do
		{
			change = (int) (Math.random() * puzzle.length);
		} while (change == newPuzzle[row][col]);
		newPuzzle[row][col] = change;
		return newPuzzle;
	}
	/**
	 * Prints out an array of integers. (Used for testing)
	 * @param array The input to be printed.
	 * @return A string that prints the array in a formatted form.
	 */
	public static String print(int[] array)
	{
		StringBuffer buffer = new StringBuffer();
		for (int index = 0; index < array.length; index++)
		{
			buffer.append(array[index] + "\t");
		}
		buffer.append("\n");
		return buffer.toString();
	}
	
	/**
	 * Tests the Sudoku class.
	 * @param args
	 */
	public static void main(String[] args)
	{
		/* Sample Sudoku puzzle with the correct solution */
		int[][] sample =   {{5,3,4,6,7,8,9,1,2},
        					{6,7,2,1,9,5,3,4,8},
        					{1,9,8,3,4,2,5,6,7},
        					{8,5,9,7,6,1,4,2,3},
        					{4,2,6,8,5,3,7,9,1},
        					{7,1,3,9,2,4,8,5,6},
        					{9,6,1,5,3,7,2,8,4},
        					{2,8,7,4,1,9,6,3,5},
        					{3,4,5,2,8,6,1,7,9}};
		Sudoku board = new Sudoku(sample);
		System.out.println(board.checkBoard());

		/* Sudoku puzzle with problems in the rows */
        int[][] sampleR =  {{5,3,4,6,7,8,9,1,2},
        					{6,7,2,1,9,5,3,4,8},
        					{1,9,8,3,4,2,5,6,7},
        					{8,5,9,7,6,1,4,2,3},
        					{4,2,3,8,5,3,7,9,1},
        					{7,1,6,9,2,4,8,5,6},
        					{9,6,1,5,3,7,2,8,4},
        					{2,8,7,4,1,9,6,3,5},
        					{3,4,5,2,8,6,1,7,9}};
        
        board = new Sudoku(sampleR);
		System.out.println(board.checkBoard());

		/* Sudoku puzzle with problems in the columns */
        int[][] sampleC =  {{5,3,4,6,7,8,9,1,2},
        					{6,7,2,1,9,5,3,4,8},
        					{1,9,8,3,4,2,5,6,7},
        					{8,5,9,7,6,1,4,2,3},
        					{4,2,6,5,8,3,7,9,1},
        					{7,1,3,9,2,4,8,5,6},
        					{9,6,1,5,3,7,2,8,4},
        					{2,8,7,4,1,9,6,3,5},
        					{3,4,5,2,8,6,1,7,9}};
        
        board = new Sudoku(sampleC);
		System.out.println(board.checkBoard());

		/* Sudoku puzzle with problems in the subsquares */
        int[][] sampleS =  {{5,3,4,6,7,8,9,1,2},
							{6,7,2,1,9,5,3,4,8},
							{1,9,8,3,4,2,5,6,7},
							{8,5,7,9,6,4,1,2,3},
							{4,2,6,8,5,3,7,9,1},
							{9,4,3,7,2,1,8,5,6},
							{7,6,1,5,3,9,2,8,4},
							{2,8,9,4,1,7,6,3,5},
							{3,1,5,2,8,6,4,7,9}};
        
        board = new Sudoku(sampleS);
		System.out.println(board.checkBoard());

		/* Sudoku puzzle with the problem generated by the damage method */
        int[][] badSample = damage(sample);
        
        board = new Sudoku(badSample);
		System.out.println(board.checkBoard());
	}
}
