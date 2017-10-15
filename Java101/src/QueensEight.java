/** 
 * This class constructs a red and gray checkerboard. This is the basis used to
 * solve a puzzle known as the Eight Queens Puzzle, which is as follows: 
 * 
 * You try to place x number of queens on an x by x checkerboard so that no 2 
 * queens can attack each other. This can be done with any size checkerboard over 3.
 * 
 * For the solving of this problem, I used a backtrace method to place and remove
 * queens: 
 * 
 * First, search for an empty space on the first available row, which would be 
 * denoted as 0 in the two-dimensional array.
 * 
 * Queens are marked with a "-1" on the board and markers are denoted by 
 * incrementing the current value by 1. 

 * If there is a valid space on that row to place a piece, I would place a queen 
 * there and mark the queen's attack movements. The queen's attack movements include
 * its immediate diagonals, row, and column.
 * There is no need to mark the movements on or above the row of the queen because
 * the next search will continue below the current queen. 
 *  
 * After this, I will search for an available space on the next row.
 * If there are x amount of queens on the board, the program will stop for 1 second, 
 * print out a graphical interpretation to the console, and remove the queen 
 * on the last row.
 *  
 * 
 * However, if I could not find a space on that row, I will remove the queen on the 
 * preceding row and its attack markers and find the next available space on that
 * preceding row.
 * 
 * The program ends when the queen on the first row cannot find a space on its row.
 * This occurs when the queen on the uppermost row has reached the end of the board. 
 * Once the program removes this queen, it will not be able to find another space to
 * place it (because the queen is at the edge of the board). This is when the program
 * terminates.
 *
 *
 * @author Wesley
 *
 */
public class QueensEight 
{
	
	private int[][] board; 
	public static final int DEFAULT_BOARD_SIZE = 8;
	public static final int QUEEN_MARK = -1;
	
	/**
	 * Creates a new checker board with dimensions of eight by eight.
	 */
	public QueensEight()
	{
		this(DEFAULT_BOARD_SIZE);
	}
	
	/**
	 * Creates a new checkerboard of a specified input size. It will use a default 
	 * board size if the inputted boardsize is invalid.
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
	 * Draws a red and gray checker board on the screen using StdDrawPlus. The 
	 * bottom left square should always be gray.
	 */
	public void drawBoard()
	{
		StdDrawPlus.setXscale(0, board.length);
		StdDrawPlus.setYscale(0, board.length);
		for (int row = 0; row < board.length; row++)
		{
			for (int column = 0; column < board.length; column++)
			{
				this.drawSquare(column, row, 10);
			}
		}
	}
	
	/**
	 * Removes a queen from the checker board, along with its attack markers. 
	 * Also updates the screen by removing a piece.
	 * @param queenX The row of the queen removed.
	 * @param queenY The column of the queen removed.
	 */
	private void remove(int queenX, int queenY)
	{
		//Removing piece
		board[queenX][queenY] = 0;
		this.drawSquare(queenX, queenY, 100);
		//Marking markers
		for (int row = queenX + 1; row < board.length; row++)
		{
			board[row][queenY]--;
		}
		for (int row = queenX + 1, column = queenY - 1; 
			row < board.length && column >= 0; 
			row++, column--)
		{
			board[row][column]--;
		}
		for (int row = queenX + 1, column = queenY + 1; 
			row < board.length && column < board.length; 
			row++, column++)
		{
			board[row][column]--;
		}
	}
	
	
	/**
	 * Places a queen on the checker board, and marks its attack spaces. 
	 * Also updates the screen by placing a queen.
	 * @param queenX The row number of the queen.
	 * @param queenY The column number of the queen
	 */
	private void place(int queenX, int queenY)
	{
		//Placing the piece
		board[queenX][queenY] = QUEEN_MARK;
		this.drawPiece(queenX, queenY, 100);
		
		//Placing markers
		for (int row = queenX + 1; row < board.length; row++)
		{
			board[row][queenY]++;
		}

		for (int row = queenX + 1, column = queenY - 1; 
			row < board.length && column >= 0; 
			row++, column--)
		{
			board[row][column]++;
		}
		for (int row = queenX + 1, column = queenY + 1; 
			row < board.length && column < board.length; 
			row++, column++)
		{
			board[row][column]++;
		}
	}
	
	
	/**
	 * Finds all solutions of the Queens puzzle. It prints out each solution 
	 * on the console and with graphics. At the end, it returns the number of 
	 * solutions for the board.
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
					//If we are on the last row:
					if (row >= board.length - 1)
					{
						//Print out solution
						System.out.println("Solution #" + (++solutions));
						board[row][column] = QUEEN_MARK;
						printBoard();
						drawPiece(row, column, 1000);
						//Remove the queen
						board[row][column] = 0;
						drawSquare(row, column, 0);
						column++;
					} else //If we are not yet at a solution
					{
						//Place the queen
						queenColumns[row] = column;
						place(row, column);
						//Go another row down and start searching from the first column
						row++;
						column = 0;
					}
				} else // Increment to the next space in the row
				{
					column++;
				}
			} else //We have reached the end of the row and have not found a place
				//to put the piece; thus we must remove the previous piece.
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
	 * Draws a red or gray square on the checkerboard. The bottom left square of
	 * the checkerboard will always be gray.
	 * @param x The x position of the square.
	 * @param y The y position of the square.
	 */
	private void drawSquare(int x, int y, int time)
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
		StdDrawPlus.show(time);
	}
	
	/**
	 * Draws a queen piece on the checker board. 
	 * @param x The x position of the piece.
	 * @param y The y position of the piece.
	 */
	private void drawPiece(int x, int y, int time)
	{
		StdDrawPlus.picture(x + .5, board.length - 1 - y + .5, "queen-dark2.png", 1, 1);
		StdDrawPlus.show(time);
	}
	
	/**
	 * Prints the board on the console. Queens are represented using Q's and 
	 * spaces with periods. It will be printed in the form of a square to give 
	 * a graphically interpretation of the board on the console.
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
		QueensEight checkerBoard;
		int boardSize = 7;
		try
		{
			//If any input was given
			if (args.length > 0)
			{
				//If there is an error parsing we will go down to the catch statement.
				//If the boardsize is out of bounds the constructor will print out a 
				//warning and use the default board size.
				boardSize = Integer.parseInt(args[0]);
			} 
			//If any input was NOT given, use default size. No error.
		} catch (NumberFormatException exception)
		{
			//This prints out an error message and sets default to 8.
			System.err.println("Error -- " + exception);
		}
		checkerBoard = new QueensEight(boardSize);
		int solutions = checkerBoard.find();
		System.out.println("For a " + checkerBoard.board.length + " by " + checkerBoard.board.length 
				+ " board, there are " + solutions + " solutions. ");		
	}
}








