/**
 * This class constructs a red and gray checkerboard. This is the basis used to solve a puzzle known as the Eight Queens Puzzle, 
 * which is as follows: 
 * 
 * You try to place x number of queens on an x by x checkerboard so that no 2 queens can attack each other. 
 * This can be done with any size checkerboard over 3.
 * 
 * For the solving of this problem, I used a backtrace method to place and remove queens: 
 * 
 * First, search for a space on the first available row. 
 * 
 * If there was a valid space on that row to place a piece, I would place a queen there and mark the queen's attack movements. 
 * There is no need to mark the movements on or above the row of the queen because the next search will continue below the current queen.
 * 
 * However, if I could not find a space on that row, I will remove the previous piece and its attack markers. 
 * Next time around, I will make sure to search to the right of this previous queen. 
 * If I don't, I will place a queen in the same place and repeat the same scenario again.
 * 
 * The program ends when the program cannot place any more pieces on the first row. It returns the number of solutions as an integer, which is then printed out in a statement in main.
 *
 * @author Wesley
 *
 */
 //TODO: Work on the class comments
public class QueensEight 
{
	
	private int[][] board; 
	public static final int DEFAULT_BOARD_SIZE = 8;
	
	/**
	 * Creates a new checker board with dimensions of eight by eight.
	 */
	public QueensEight()
	{
		this(DEFAULT_BOARD_SIZE);
	}
	
	/**
	 * Creates a new checkerboard of a specified input size. It will use a default board size if the inputted boardsize is invalid.
	 * @param boardSize The length of all sides of the board.
	 */
	public QueensEight(int boardSize)
	{
		//Checks if boardSize is valid.
		if (boardSize < 1)
		{
			boardSize = DEFAULT_BOARD_SIZE;
			System.err.println("Board size not valid. Board size set to default of 8.");
		} 
		this.board = new int[boardSize][boardSize];
		
		//Initializes the board
		for (int row = 0; row < board.length; row++)
		{
			for (int column = 0; column < board.length; column++)
			{
				board[row][column] = 0; 
			}
		}
		drawBoard();
	}
	
	/**
	 * Draws a red and gray checker board on the screen using StdDrawPlus. The bottom left square should always be gray.
	 */
	public void drawBoard()
	{
		StdDrawPlus.setXscale(0, board.length);
		StdDrawPlus.setYscale(0, board.length);
		for (int row = 0; row < board.length; row++)
		{
			for (int column = 0; column < board.length; column++)
			{
				this.drawSquare(column, row);
			}
		}
	}
	
	/**
	 * Removes a queen from the checker board, along with its attack markers. Also updates the screen by removing a piece.
	 * @param queenX The row of the queen removed.
	 * @param queenY The column of the queen removed.
	 */
	private void remove(int queenX, int queenY)
	{
		//Removing piece
		board[queenX][queenY] = 0;
		this.drawSquare(queenX, queenY);
		StdDrawPlus.show(10);
		//Marking markers
		for (int row = queenX + 1; row < board.length; row++)
		{
			board[row][queenY]--;
		}
		for (int row = queenX + 1, column = queenY - 1; row < board.length && column >= 0; row++, column--)
		{
			board[row][column]--;
		}
		for (int row = queenX + 1, column = queenY + 1; row < board.length && column < board.length; row++, column++)
		{
			board[row][column]--;
		}
			
	}
	
	
	/**
	 * Places a queen on the checker board, and marks its attack spaces. Also updates the screen by placing a queen.
	 * @param queenX The row number of the queen.
	 * @param queenY The column number of the queen
	 */
	private void place(int queenX, int queenY)
	{
		//Placing the piece
		board[queenX][queenY] = -1;
		this.drawPiece(queenX, queenY);
		StdDrawPlus.show(10);
		
		//Placing markers
		for (int row = queenX + 1; row < board.length; row++)
		{
			board[row][queenY]++;
		}

		for (int row = queenX + 1, column = queenY - 1; row < board.length && column >= 0; row++, column--)
		{
			board[row][column]++;
		}
		for (int row = queenX + 1, column = queenY + 1; row < board.length && column < board.length; row++, column++)
		{
			board[row][column]++;
		}
	}
	
	
	/**
	 * Finds all solutions of the Queens puzzle. It prints out each solution on the console and with graphics. At the end, it returns the number of solutions for the board.
	 * @return The number of solutions for the given board size as an integer.
	 */
	public int find()
	{
		//Variables
		int row = 0;
		int column = 0;
		int[] queenColumns = new int[board.length];
		int solutions = 0;
		
		while (row >= 0)
		{
			//Searching: if there are spaces on the board to search:
			if (column < board.length) 
			{	
				//If there is an empty space to place a queen:
				if (board[row][column] == 0)
				{
					//Place the queen
					queenColumns[row] = column;
					place(row, column);
					//If we are on the last row:
					if (row >= board.length - 1)
					{
						//Print out solution
						System.out.println("Solution #" + (++solutions));
						printBoard();
						StdDrawPlus.show(1000);
						//Remove the queen
						remove(row, column);
						column++;
					} else //If we are not yet at a solution
					{
						//Go another row down and start searching from the first column
						row++;
						column = 0;
					}
				} else // Increment to the next space in the row
				{
					column++;
				}
			} else //We have reached the end of the row and have not found a place to put the piece; thus we must remove the previous piece.
			{ 
				row--;
				if (row >= 0) //If we can remove a piece
				{
					remove(row, queenColumns[row]);
					column = queenColumns[row] + 1;
				} 
			}			
		}
		return solutions;
	}
	
	/**
	 * Draws a red or gray square on the checkerboard. The bottom left square of the checkerboard will always be gray.
	 * @param x The x position of the square.
	 * @param y The y position of the square.
	 */
	private void drawSquare(int x, int y)
	{
		if (x % 2 == (board.length - y) % 2)
		{
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		else
		{
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		}
		StdDrawPlus.filledSquare(x + .5, board.length - 1 - y + .5, .5);
	}
	
	/**
	 * Draws a queen piece on the checker board. 
	 * @param x The x position of the piece.
	 * @param y The y position of the piece.
	 */
	private void drawPiece(int x, int y)
	{
		StdDrawPlus.picture(x + .5, board.length - 1 - y + .5, "queen-dark2.png", 1, 1);
	}
	
	/**
	 * Prints the board on the console. Queens are represented using Q's and spaces with periods. It will be printed in the form of a square to give a graphically interpretation of the board on the console.
	 */
	public void printBoard()
	{
		for (int row = 0; row < board.length; row++)
		{  
	        for (int column = 0; column < board.length; column++)
	        { 
	        	if (board[row][column] == -1)
	        	{
	        		System.out.print("Q ");
	        	} else 
	        	{
	        		System.out.print(". ");
	        	} 
	        }
	        System.out.print("\n");
	    }
		System.out.println();
	}
	
	
	/**
	 * Finds all solutions for a given argument.
	 * @param args 1) Java QueensEight validInteger: use validInteger 
	  			   2) Java QueensEight invalidInteger: print error, default size
	  			   3) Java QueensEight: default
	  			   4) Java QueensEight nonInt: print error, default size
	 */
	public static void main(String[] args)
	{
		QueensEight board;
		int boardSize = 8;
		try
		{
			//If any input was given
			if (args.length > 0)
			{
				//If there is an error parsing we will go down to the catch statement.
				//If the boardsize is out of bounds the constructor will print out a warning and use the default board size.
				boardSize = Integer.parseInt(args[0]);
			} 
			//If any input was NOT given, use default size. No error.
		} catch (NumberFormatException exception)
		{
			//This prints out an error message and sets default to 8.
			System.err.println("Error -- " + exception);
		}
		board = new QueensEight(boardSize);
		int solutions = board.find();
		System.out.println("For an " + board.board.length + " by " + board.board.length + " board, there are " + solutions + " solutions. ");		
	}
}








