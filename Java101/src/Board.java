public class Board 
{
	
	private int boardSize;
	private int[][]board;
	private int numQueens;
	
	/**
	 * Creates a new board.
	 * @param boardSize The length of 1 side of the board.
	 */
	//Also drawing the board here.
	public Board(int boardSize)
	{
		StdDrawPlus.setXscale(0, boardSize);
		StdDrawPlus.setYscale(0, boardSize);
		this.boardSize = boardSize;
		this.board = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++)
		{
			int temp = row;
			for (int column = 0; column < boardSize; column++)
			{
				temp++;
				board[row][column] = 0;
				this.drawSquare(column, row);
			}
		}
		numQueens = 0;
	}
	/**
	 * Deletes queens for the setQueen function.
	 * @param subtractQueenNum The queen in which to remove. 
	 */
	public void delete(int subtractQueenNum)
	{
		for (int row = 0; row < boardSize; row++)//deletes the previous queen + all places blocked by that queen
		{
			for (int column = 0; column < boardSize; column++)
			{
				if (board[row][column] == -(numQueens - subtractQueenNum))
				{
					board[row][column] = 0;
				}
			}
		}
	}
	/**
	 * Marks a place on the board to show that a queen cannot go there. 
	 * @param subtractQueenNum The queen that will be marked.
	 * @return the column of the mark. 
	 */
	public int marker(int subtractQueenNum)
	{
		int temp = 0;
		for (int queencolumn = 0; queencolumn < boardSize; queencolumn++)//Marks the index to make sure not to place queen here.
		{
			temp = queencolumn;
			if (board[numQueens - subtractQueenNum][queencolumn] == 1)
			{
				board[numQueens - subtractQueenNum][queencolumn] = -(boardSize + 1);
				this.drawSquare(queencolumn, numQueens - subtractQueenNum);
			} 
		}
		return temp;
		
	}
	private void drawSquare(int y, int x)
	{
		if(x % 2 == y % 2)
		{
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
			StdDrawPlus.filledSquare(x + .5, boardSize - 1 - y + .5, .5);
		}
		else
		{
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			StdDrawPlus.filledSquare(x + .5, boardSize - 1 - y + .5, .5);
		}
	}
	
	private void drawPiece(int y, int x)
	{
		StdDrawPlus.picture(x + .5, boardSize - 1 - y + .5, "img/queen-dark.png", 1, 1);
	}
	public void findAllQueens()
	{
		int answers = 0;
		int queenRow = 0;
		int queenColumn = 0;
		int columns = 0;
		while (true)
		{
			outerloop:
			{
				//Finds a place to put the queen.  
				boolean checkSpaces = false;
				for (int findQueenColumn = 0; findQueenColumn < boardSize; findQueenColumn++)
				{
					columns = findQueenColumn;
					if (board[numQueens][findQueenColumn] == 0)
					{
						board[numQueens][findQueenColumn] = 1;//A queen
						this.drawPiece(findQueenColumn, numQueens);
						checkSpaces = true;
						break;
					}
				}	
				if (!checkSpaces)//this is for the backtracking
				{
					if(numQueens <= 1)
					{
						break outerloop;
					}	
					this.delete(0);//deletes the queen's invalid areas. 
					int yPos = this.marker(1);//marks the queen's original spot as invalid.
					int checkPositions = 0;	
					for (int column = yPos; column < boardSize; column++)//checks if there are any more places on the row to put the queen.
					{
						if (board[numQueens - 1][column] == 0)
						{
							checkPositions++;
						}						
					}	
					if (checkPositions == 0)//when there is no place to put the queen on that row
					{
						for (int markerColumn = 0; markerColumn < boardSize; markerColumn++)//deletes markers
						{
							if (board[numQueens - 1][markerColumn] == -(boardSize + 1))
							{
								board[numQueens - 1][markerColumn] = 0;
							}	
						}					
						this.delete(1); 
						this.marker(2); 
						numQueens--;
					}
					numQueens--;
				}
				else//marks queen stuff
				{
					queenRow = numQueens; 
					queenColumn = columns;
					for (int column = 0; column < boardSize; column++)//marks the columns
					{
						if (column != queenColumn && board[queenRow][column] == 0)
						{
							board[queenRow][column] = -(numQueens + 1);//Invalid
						}	
					}	
					for (int row = 0; row < boardSize; row++)//marks the rows
					{
						if (row != queenRow && board[row][queenColumn] == 0)
						{
							board[row][queenColumn] = -(numQueens + 1);//Invalid	
						}		
					}
					for (int row = 0; row < boardSize; row++)//rows
					{
						for (int column = 0; column < boardSize; column++)//columns
						{
							if (queenRow - row == queenColumn - column && queenRow != row && board[row][column] == 0)
							{
								board[row][column] = -(numQueens + 1);//Invalid
							}
							else if (queenRow - row == -(queenColumn - column) && queenRow != row && board[row][column] == 0)
							{
								board[row][column] = -(numQueens + 1);//Invalid
							}	
						}
					}
					numQueens++;	
				}
				if (numQueens == boardSize)
				{
					answers++;
					System.out.println("Solution #" + answers + ":");
					this.printBoard();
					StdDrawPlus.show(1000);
					this.delete(0);//deletes the queen's invalid areas. 
					int yPos = this.marker(1);//marks the queen's original spot as invalid.
					int checkPositions = 0;	
					for (int column = yPos + 1; column < boardSize; column++)//checks if there are any more places on the row to put the queen.
					{
						if (board[numQueens - 1][column] == 0)
						{
							checkPositions++;
						}						
					}	
					if (checkPositions == 0)//when there is no place to put the queen on that row
					{
						for (int markerColumn = 0; markerColumn < boardSize; markerColumn++)//deletes markers
						{
							if (board[numQueens - 1][markerColumn] == -(boardSize + 1))
							{
								board[numQueens - 1][markerColumn] = 0;
							}	
						}					
						this.delete(1);//deletes the markers of queen 7
						this.marker(2);//marks queen 7's position as DO NOT GO. 
						numQueens--;//numQueens is 7
					}
					numQueens--;//7 or 6
				}	
			}
		}		
	}

	/**
	 * Sets a queen on the board. 
	 */
	public void setQueen()
	{
		int queenRow = 0;
		int queenColumn = 0;
		int columns = 0;
		while (numQueens < boardSize)
		{
			//Finds a place to put the queen.  
			boolean checkSpaces = false;
			for (int findQueenColumn = 0; findQueenColumn < boardSize; findQueenColumn++)
			{
				columns = findQueenColumn;
				if (board[numQueens][findQueenColumn] == 0)
				{
					board[numQueens][findQueenColumn] = 1;//A queen
					this.drawPiece(findQueenColumn, numQueens);
					checkSpaces = true;
					break;
				}
			}	
			if (!checkSpaces)//this is for the backtracking
			{
				this.delete(0);//deletes the queen's invalid areas. 
				int yPos = this.marker(1);//marks the queen's original spot as invalid.
				int checkPositions = 0;	
				for (int column = yPos; column < boardSize; column++)//checks if there are any more places on the row to put the queen.
				{
					if (board[numQueens - 1][column] == 0)
					{
						checkPositions++;
					}						
				}	
				if (checkPositions == 0)//when there is no place to put the queen on that row
				{
					for (int markerColumn = 0; markerColumn < boardSize; markerColumn++)//deletes markers
					{
						if (board[numQueens - 1][markerColumn] == -(boardSize + 1))
						{
							board[numQueens - 1][markerColumn] = 0;
						}	
					}					
					this.delete(1); 
					this.marker(2); 
					numQueens--;
				}
				numQueens--;
			}
			else//marks queen stuff
			{
				queenRow = numQueens; 
				queenColumn = columns;
				for (int column = 0; column < boardSize; column++)//marks the columns
				{
					if (column != queenColumn && board[queenRow][column] == 0)
					{
						board[queenRow][column] = -(numQueens + 1);//Invalid
					}	
				}	
				for (int row = 0; row < boardSize; row++)//marks the rows
				{
					if (row != queenRow && board[row][queenColumn] == 0)
					{
						board[row][queenColumn] = -(numQueens + 1);//Invalid	
					}		
				}
				for (int row = 0; row < boardSize; row++)//rows
				{
					for (int column = 0; column < boardSize; column++)//columns
					{
						if (queenRow - row == queenColumn - column && queenRow != row && board[row][column] == 0)
						{
							board[row][column] = -(numQueens + 1);//Invalid
						}
						else if (queenRow - row == -(queenColumn - column) && queenRow != row && board[row][column] == 0)
						{
							board[row][column] = -(numQueens + 1);//Invalid
						}	
					}
				}
				numQueens++;	
			}
		}
	}
	
	
	public void printBoard()
	{
		for (int row = 0; row < boardSize; row++)
		{  
	        for (int column = 0; column < boardSize; column++)
	        {  
	        	if(board[column][row] == 1)
	        	{
	        		System.out.print("Q  ");
	        	}
	        	else
	        	{
	        		System.out.print(-1 * (board[column][row]) + "  ");
	        	}
	        }
	        System.out.print("\n");
	    }
	}
	
	public static void main(String[] args)
	{
		Board board = new Board(8);
		board.findAllQueens();
		board.printBoard();
	}
}