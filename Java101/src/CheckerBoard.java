import javax.swing.JComponent;
//TODO: place should change moved and captured variables; let setUpBoard and endTurn reset the piece when it shouldnt be changed.
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

/*
 * The purpose of the CheckerBoard object is to represent a checker board and allow the user to play a game of checkers with it.
 * 
 * A CheckerBoard object contains a two-dimensional array of Piece objects, a boolean indicating the player turn, and a selected Piece object. There is also a constant BOARD_LENGTH that is used to determine the size of the board.
 * The two-dimensional array of Piece objects represents the checker board and stores the values of the pieces of the checker board in their respective locations.
 * The boolean indicating player turn is true if the player controlling the dark pieces is currently having their turn.
 * The selected Piece object stores the piece at the location currently selected by the player. If the player has not yet selected a piece, the variable holds null.
 * 
 * To play the game, run the main method of this class. 
 * This main method repeatedly checks for mouse clicks (selections) and space bar presses (turn switching) and handles these inputs accordingly if they are valid or not (selects a location if possible, ends turn if possible).
 * Also, it repeatedly checks the board to determine if there is a winner.
 * 
 */
public class CheckerBoard {
	private Piece[][] board;
	public static final int BOARD_LENGTH = 8;
	private boolean darkTurn;
	private Piece selectedPiece;

	/**
	 * Creates a CheckerBoard object. This initializes the two dimensional array
	 * that holds the information of the checker board. It creates a normal checker
	 * board with pieces on the gray squares for the top three and bottom three
	 * rows. Also, it sets the current turn (in this case, it defaults to dark
	 * starting first) and initializes the selected piece to null.
	 */
	public CheckerBoard() {
		this(false);
	}

	/**
	 * Creates a CheckerBoard object. This initializes the two dimensional array
	 * that holds the information of the checker board. If test is false, it creates
	 * a normal checker board with pieces on the gray squares for the top three and
	 * bottom three rows. If test is true, it creates a checker board with no pieces
	 * on it. Also, it sets the current turn (in this case, it defaults to dark
	 * starting first) and initializes the selected piece to null.
	 * 
	 * @param test
	 *            If this is false, the checker board will be set with pieces on the
	 *            black tiles for the top three and bottom three rows (classic
	 *            checker board setup). If this is true, the checker board starts
	 *            off empty.
	 */
	public CheckerBoard(boolean test) {
		board = new Piece[BOARD_LENGTH][BOARD_LENGTH];
		setScale();
		darkTurn = true;
		selectedPiece = null;
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board.length; column++) {
				board[row][column] = null;
				drawSquare(row, column);
			}
		}
		if (!test) {
			setUpBoard();
		}
	}

	/**
	 * Sets the scale of the graphical checker board.
	 */
	public void setScale() {
		StdDrawPlus.setXscale(0, board.length);
		StdDrawPlus.setYscale(0, board.length);
	}

	/**
	 * Positions the pieces for the start of the game. This includes placing dark
	 * pieces on the gray squares in the top three rows, as well as placing light
	 * pieces on the gray squares in the bottom three rows. The pieces are also
	 * drawn on the graphical checker board.
	 */
	public void setUpBoard() {
		// Let's do the top ones first!
		for (int row = 0; row < 3; row++) {
			for (int column = row % 2; column < board.length; column += 2) {
				place(new Piece(row, column, true), row, column);
			}
		}

		// Let's do the bottom ones next!
		for (int row = board.length - 3; row < board.length; row++) {
			for (int column = row % 2; column < board.length; column += 2) {
				place(new Piece(row, column, false), row, column);
			}
		}

	}

	/**
	 * Places and draws a given piece at a given position on the checker board and 
	 * places the piece on the board instance variable. This
	 * method also changes the piece's internal location, and sets the piece's moved 
	 * or captured methods appropriately depending if the piece moved or captured.
	 * 
	 * @param piece
	 *            The piece to be placed on the checkerBoard
	 * @param destinationX
	 *            The x position of the given location.
	 * @param destinationY
	 *            The y position of the given location.
	 */
	public void place(Piece piece, int destinationX, int destinationY) {
		board[destinationX][destinationY] = piece;
		piece.pieceMove(destinationX, destinationY);
		this.drawPiece(piece);
	}

	/**
	 * Draws a given piece at its internal location. It determines the piece's shape
	 * by its color and its royalty.
	 * 
	 * @param piece
	 *            The given piece to be drawn.
	 */
	public void drawPiece(Piece piece) {
		final String FILE_TYPE = ".png";
		final String CROWN = "-crowned";
		final String DARK = "dark";
		final String LIGHT = "light";
		final double OFFSET = 0.5;
		final String PAWN = "pawn";
		if (piece != null) {
			String color = "-" + (piece.isDark() ? DARK : LIGHT);
			String royalty = piece.isKing() ? CROWN : "";

			StdDrawPlus.picture(piece.getX() + OFFSET, piece.getY() + OFFSET, PAWN + color + royalty + FILE_TYPE, 1, 1);
		}

	}

	/**
	 * Removes and returns the piece at the given location on the checker board.
	 * This also removes the piece's drawing from the graphical checker board.
	 * 
	 * @param selectedX
	 *            The x position of the given location.
	 * @param selectedY
	 *            The y position of the given location.
	 * @return The piece that was removed, or null if there was no piece to
	 *         remove/location is invalid.
	 */
	public Piece remove(int selectedX, int selectedY) {

		Piece removedPiece = board[selectedX][selectedY];
		board[selectedX][selectedY] = null;
		this.drawSquare(selectedX, selectedY);
		return removedPiece;
	}

	/**
	 * Draws a square on the checker board at the given location. If the given
	 * location is also the location of the selected piece, the square is white. If
	 * not, the square color is determined by its location. If the (sum of the x and
	 * y position of the given location) % 2 == 0, then the square is gray. If not,
	 * the square is red.
	 * 
	 * @param selectedX
	 *            The x position of the given location.
	 * @param selectedY
	 *            The y position of the given location.
	 */
	public void drawSquare(int selectedX, int selectedY) {
		final double OFFSET = .5;
		if (board[selectedX][selectedY] == selectedPiece) {
			// g2.setColor(Color.WHITE);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		} else if ((selectedX + selectedY) % 2 == 0) {
			// g2.setColor(Color.RED);
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else {
			// g2.setColor(Color.GRAY);
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		StdDrawPlus.filledSquare(selectedX + OFFSET, selectedY + OFFSET, OFFSET);
	}

	/**
	 * Checks and returns a boolean signifying if the player can end their current
	 * turn. A player can end their turn when they have moved a piece.
	 * 
	 * @return True if the player can end, false if not.
	 */
	public boolean canEndTurn() {
		return selectedPiece != null && selectedPiece.hasMoved();
	}

	/**
	 * Ends the player's turn. This method stores the x and y positions of the
	 * selected piece, resets the selected piece and sets the selected piece
	 * variable to null, and removes the white square.
	 */
	public void endTurn() {
		int xPosition = selectedPiece.getX();
		int yPosition = selectedPiece.getY();
		selectedPiece.reset();
		selectedPiece = null;
		this.drawSquare(xPosition, yPosition);
		this.drawPiece(selectedPiece);
		darkTurn = !darkTurn;
	}

	/**
	 * Determines if the game has ended, and if it has, who won. This method
	 * searches the board for light and dark pieces. If there are no light pieces,
	 * the dark player has won. If there are no dark pieces, the light player has
	 * won.
	 * 
	 * @return If the game has ended, it returns a string saying either "Light has
	 *         won!" or "Dark has won!" depending on which player won. If the game
	 *         has not ended, it returns null.
	 */
	public String winner() {
		int light = 0;
		int dark = 0;
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board.length; column++) {
				if (board[row][column] != null) {
					if (board[row][column].isDark()) {
						dark++;
					} else {
						light++;
					}
				}
			}
		}
		if (light == 0) {
			return "Dark has won!";
		} else if (dark == 0) {
			return "Light has won!";
		}
		return null;
	}

	/**
	 * Moves the selected piece to a given location. This is accomplished by first
	 * removing the selected piece, then placing it at a different location. The
	 * internal location of the selected piece and its moved status are also
	 * changed.
	 * 
	 * @param destinationX
	 *            The x position of the given location.
	 * @param destinationY
	 *            The y position of the given location.
	 */
	public void move(int destinationX, int destinationY) {
		Piece movedPiece = remove(selectedPiece.getX(), selectedPiece.getY());
		place(movedPiece, destinationX, destinationY);
	}

	/**
	 * Move the selected piece to a given location and captures the piece between the selected piece's location and the given location.
	 * The method uses the midpoint formula to remove the captured piece, and then calls the move() method to move the selected piece to the given location.
	 * 
	 * @param destinationX
	 *            The x position of the given location.
	 * @param destinationY
	 *            The y position of the given location.
	 */
	public void capture(int destinationX, int destinationY) {
		/*
		 * Let's do some math! In order to figure out the piece captured, we can use the
		 * midpoint formula to figure out the location halfway between two points (e.g.
		 * the piece halfway between selectedPiece's original position and its new
		 * position is the captured piece!)
		 */
		remove((selectedPiece.getX() + destinationX) / 2, (selectedPiece.getY() + destinationY) / 2);
		move(destinationX, destinationY);
	}

	/**
	 * Calculates and returns whether or not the distance of selectedPiece's given
	 * movement qualifies as a capture movement (used in canSelect).
	 * 
	 * @param destinationX
	 *            The x position of the given movement.
	 * @param destinationY
	 *            The y position of the given movement.
	 * @return True if the movement distance qualifies as a capture, false if not.
	 */
	public boolean isCaptureDistance(int destinationX, int destinationY) {
		return ((selectedPiece.getX() - destinationX == 2 || selectedPiece.getX() - destinationX == -2)
				&& (selectedPiece.getY() - destinationY == 2 || selectedPiece.getY() - destinationY == -2));
	}

	/**
	 * Returns whether or not the location clicked can be selected. This will be true in one of two
	 * circumstances: - there is a piece at the selected location, the piece is the player's
	 * piece, and no piece has been selected or the selected piece has not moved. -
	 * there is no piece at the selected location, the selected piece is not null, 
	 * and the selected piece has not moved or has previously made a capture move and plans to make another valid capture move.
	 * 
	 * @param selectedX
	 *            The x position on the board that is specified to be checked.
	 * @param selectedY
	 *            The y position on the board that is specified to be checked.
	 * @return True if the position can be selected, false if not.
	 */
	public boolean canSelect(int selectedX, int selectedY) {
		if (selectedX > -1 && selectedX < board.length && selectedY > -1 && selectedY < board.length) 
		{
			if (board[selectedX][selectedY] != null)
			{
				return darkTurn == board[selectedX][selectedY].isDark()
						&& (selectedPiece == null || !selectedPiece.hasMoved());
			} else 
			{
				if (selectedPiece != null && (!selectedPiece.hasMoved()
						|| (selectedPiece.hasCaptured() && isCaptureDistance(selectedX, selectedY)))) 
				{
					return validMove(selectedX, selectedY);
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if a given location is a valid destination for the selected piece to be
	 * moved to.
	 * 
	 * First, the selected piece's royalty and the direction of its movement is checked. 
	 * If the piece is crowned (can move in any direction) or the direction in which the piece is moving is appropriate for its color,
	 * the distance of the move is checked.
	 * If the distance of the move is representative of a regular move (diagonal by one tile), the method returns true.
	 * If the distance of the move is representative of a capture move (diagonal by two tiles), 
	 * the method checks the location between the selected piece and the destination.
	 * 	If this location contains a piece of the opposite color of the selected piece, the method returns true.
	 * 
	 * @param destinationX
	 *            The x position of the given location.
	 * @param destinationY
	 *            The y position of the given location.
	 * @return
	 */
	public boolean validMove(int destinationX, int destinationY) {
		int incrementX = destinationX - selectedPiece.getX();
		int incrementY = destinationY - selectedPiece.getY();

		if (selectedPiece.isKing()
				|| (selectedPiece.isDark() && incrementY > 0 || (!selectedPiece.isDark() && incrementY < 0))) {
			if ((incrementY == 1 || incrementY == -1) && (incrementX == 1 || incrementX == -1)) // Move
			{
				return true;
			} else if ((incrementY == 2 || incrementY == -2) && (incrementX == 2 || incrementX == -2)) // Capture
			{
				// Captured piece does not share the same color as selected piece.
				int capturedX = (selectedPiece.getX() + destinationX) / 2;
				int capturedY = (selectedPiece.getY() + destinationY) / 2;
				return board[capturedX][capturedY] != null
						&& (board[capturedX][capturedY].isDark() != selectedPiece.isDark());
			}
			return false;
		}
		return false;
	}

	/**
	 * Selects the given location.
	 * 
	 * First, the method checks if the specified location contains a piece.
	 * If the specified location contains a piece, the square at the piece's location is colored white.
	 * If the specified location does not contain a piece, the selected piece is moved to the specified location.
	 * @param x The first index of the 2 dimensional array in which is selected.
	 * @param y The second index of the 2 dimensional array in which is selected.
	 */
	public void select(int x, int y) 
	{
		if (board[x][y] != null)
		{
			selectedPiece = board[x][y];
			drawSquare(x, y);
			drawPiece(selectedPiece);
		} else 
		{
			move(x, y);
		}
	}

	public static void main(String[] args) {

		CheckerBoard test = new CheckerBoard();
		int x, y;

		while (test.winner() == null) {

			if (StdDrawPlus.mousePressed() && StdDrawPlus.mouseX() > -1 && StdDrawPlus.mouseX() < BOARD_LENGTH
					&& StdDrawPlus.mouseY() > -1 && StdDrawPlus.mouseY() < BOARD_LENGTH) {
				x = (int) StdDrawPlus.mouseX();
				y = (int) StdDrawPlus.mouseY();
				if (test.canSelect(x, y)) {
					test.select(x, y);
				}
			}

			// Monitors for space-bar presses. Switches turn whenever the space-bar is
			// pressed.
			if (StdDrawPlus.isSpacePressed()) {
				if (test.canEndTurn()) {
					test.endTurn();
				}
			}

			StdDrawPlus.show(100);
		}
		System.out.println(test.winner());
	}

}
