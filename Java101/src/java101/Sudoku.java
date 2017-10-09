package java101;
/**
 * A Sudoku board is a 9 by 9 grid filled with the numbers 1 to 9. This is 
 * represented using a two-dimensional array. 
 * In a valid Sudoku puzzle, every column, row, and subsquare (found by 
 * dividing the board into 9 equal squares) should be a legal permutation of 
 * numbers from 1 to 9.
 * 
 * A permutation is an arrangement of integers between a lower and upper boundary, 
 * inclusive. Only one of each number is present.
 *
 * The method used to check for a valid solution is called checkBoard. It iterates 
 * through the entire board, checking all rows, columns, and subsquares. It uses the 
 * isPermutation method to check if each is a legal permutation.
 * If one is an invalid permutation, the board is not a valid solution and false is 
 * returned. If all rows, columns, and subsquares are valid permutations, then true 
 * is returned.
 * @author Wesley
 *
 */
public class Sudoku 
{
	
	private int[][] sudokuBoard;
	
	/**
	 * Constructs a Sudoku object. The contents of the Sudoku board are specified 
	 * with the user-specifed two-dimensional array.
	 * @param sudoku The data present on the sudoku board. 
	 */
	public Sudoku(int[][] sudoku)
	{
		if (sudoku != null && sudoku.length == 9)
		{
			sudokuBoard = sudoku;
		} else
		{
			sudokuBoard = new int[9][9];
			for (int row = 0; row < 9; row++)
			{
				int startNum = row / 3 + (3 * (row / 3)) + 1;
				for (int column = 0; column < 9; column++)
				{
					sudokuBoard[row][column] = (startNum + column) % sudokuBoard.length;
				}
			}
			//TODO: FILL THE BOARD WITH SOMETHING
			/* Something like this: 
			 * 1 2 3 4 5 6 7 8 9 
			 * 4 5 6 7 8 9 1 2 3 
			 * 7 8 9 1 2 3 4 5 6 
			 * 2 3 4 5 6 7 8 9 1 
			 * 5 6 7 8 9 1 2 3 4
			 * 8 9 1 2 3 4 5 6 7
			 * 3 4 5 6 7 8 9 1 2 
			 * 6 7 8 9 1 2 3 4 5 
			 * 9 1 2 3 4 5 6 7 8 */
			
		}
	}
	/**
	 * Returns if the Sudoku object is a valid solution and, if applicable, 
	 * prints out the number of errors total for rows, columns, and subsquares.
	 * A valid solution is defined as a Sudoku object where all rows, columns, 
	 * and subsquares are all valid permutations.
	 * @return Returns true if the board is a valid solution, or false if not.
	 */
	public boolean checkBoard()
	{
		int rowProblem = 0;
		int columnProblem = 0;
		int squareProblem = 0;
		int subSquareSize = (int) Math.sqrt(sudokuBoard.length);
		for (int row = 0; row < sudokuBoard.length; row++)
		{
			if (!isPermutation(sudokuBoard[row]))
			{
				rowProblem++;
			}
			int[] columnArray = new int[sudokuBoard.length];
			int[] subSquareArray = new int[sudokuBoard.length];
			for (int column = 0; column < sudokuBoard.length; column++)
			{
				columnArray[column] = sudokuBoard[row][column];
				subSquareArray[column] = sudokuBoard
					[(row / subSquareSize) * subSquareSize + (column / subSquareSize)]
					[(row % subSquareSize) * subSquareSize + (column % subSquareSize)]; 
			}
			if (!isPermutation(columnArray))
			{
				columnProblem++;
			}
			if (!isPermutation(subSquareArray))
			{
				squareProblem++;
			}
		}
		System.out.println("# of invalid rows: " + rowProblem);
		System.out.println("# of invalid columns: " + columnProblem);
		System.out.println("# of invalid sub squares: " + squareProblem);
		return rowProblem + columnProblem + squareProblem == 0;
	}
	
	/**
	 * Checks if an integer array is a permutation.
	 * A permutation is an arrangement of integers between a lower and upper boundary, 
	 * inclusive. Only one of each number is present.
	 * @param inputArray The array to be checked.
	 * @return True if inputArray is a permutation, false if not.
	 */
	public boolean isPermutation(int[] inputArray)
	{
		if (inputArray != null && inputArray.length > 0) 
		{
			int min = inputArray[0];
			int max = inputArray[0]; 
			boolean[] bins = new boolean[inputArray.length];
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
			if (max - min == inputArray.length - 1)
			{
				for (int index = 0; index < inputArray.length; index++)
				{
					int compareIndex = inputArray[index] - min;
					if (bins[compareIndex])
					{
						return false;
					} 
					bins[compareIndex] = true;
				}
				return true;
			} 
		}
		return false;	
	}
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
		Sudoku board = new Sudoku(null);
		StringBuffer buffer = new StringBuffer();
		for (int row = 0; row < board.sudokuBoard.length; row++)
		{
			for (int column = 0; column < board.sudokuBoard.length; column++)
			{
				buffer.append(board.sudokuBoard[row][column] + "\t");
			}
			buffer.append("\n");
		}
		System.out.println(buffer.toString());
		/* Sample Sudoku puzzle with the correct solution */
		/*int[][] sample =   {{5,3,4,6,7,8,9,1,2},
        					{6,7,2,1,9,5,3,4,8},
        					{1,9,8,3,4,2,5,6,7},
        					{8,5,9,7,6,1,4,2,3},
        					{4,2,6,8,5,3,7,9,1},
        					{7,1,3,9,2,4,8,5,6},
        					{9,6,1,5,3,7,2,8,4},
        					{2,8,7,4,1,9,6,3,5},
        					{3,4,5,2,8,6,1,7,9}};
		board = new Sudoku(sample);
		System.out.println(board.checkBoard());*/

		/* Sudoku puzzle with problems in the rows */
        /*int[][] sampleR =  {{5,3,4,6,7,8,9,1,2},
        					{6,7,2,1,9,5,3,4,8},
        					{1,9,8,3,4,2,5,6,7},
        					{8,5,9,7,6,1,4,2,3},
        					{4,2,3,8,5,3,7,9,1},
        					{7,1,6,9,2,4,8,5,6},
        					{9,6,1,5,3,7,2,8,4},
        					{2,8,7,4,1,9,6,3,5},
        					{3,4,5,2,8,6,1,7,9}};
        
        board = new Sudoku(sampleR);
		System.out.println(board.checkBoard());*/

		/* Sudoku puzzle with problems in the columns */
        /*int[][] sampleC =  {{5,3,4,6,7,8,9,1,2},
        					{6,7,2,1,9,5,3,4,8},
        					{1,9,8,3,4,2,5,6,7},
        					{8,5,9,7,6,1,4,2,3},
        					{4,2,6,5,8,3,7,9,1},
        					{7,1,3,9,2,4,8,5,6},
        					{9,6,1,5,3,7,2,8,4},
        					{2,8,7,4,1,9,6,3,5},
        					{3,4,5,2,8,6,1,7,9}};
        
        board = new Sudoku(sampleC);
		System.out.println(board.checkBoard())*/

		/* Sudoku puzzle with problems in the subsquares */
        /*int[][] sampleS =  {{5,3,4,6,7,8,9,1,2},
							{6,7,2,1,9,5,3,4,8},
							{1,9,8,3,4,2,5,6,7},
							{8,5,7,9,6,4,1,2,3},
							{4,2,6,8,5,3,7,9,1},
							{9,4,3,7,2,1,8,5,6},
							{7,6,1,5,3,9,2,8,4},
							{2,8,9,4,1,7,6,3,5},
							{3,1,5,2,8,6,4,7,9}};
        
        board = new Sudoku(sampleS);
		System.out.println(board.checkBoard());*/

		/* Sudoku puzzle with the problem generated by the damage method */
        /*int[][] badSample = damage(sample);
        
        board = new Sudoku(badSample);
		System.out.println(board.checkBoard());*/
	}
}
