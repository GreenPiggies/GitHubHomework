import static org.junit.Assert.*;

import org.junit.Test;

public class PieceTest {

	CheckerBoard test = new CheckerBoard();
	@Test
	public void testValidMove() 
	{
		
		Piece testPiece1 = new Piece(1, 1, true, true, test); 
		assertEquals(testPiece1.validMove(0, 0), true);
		assertEquals(testPiece1.validMove(0, 2), true);
		assertEquals(testPiece1.validMove(2, 0), true);
		assertEquals(testPiece1.validMove(2, 2), true);
		Piece testPiece2 = new Piece(1, 1, true, false, test);
		assertEquals(testPiece2.validMove(0, 0), false);
		assertEquals(testPiece2.validMove(2, 0), false);
		assertEquals(testPiece2.validMove(0, 2), true);
		assertEquals(testPiece2.validMove(2, 2), true);
		Piece testPiece3 = new Piece(1, 1, false, false, test);
		assertEquals(testPiece3.validMove(0, 0), true);
		assertEquals(testPiece3.validMove(2, 0), true);
		assertEquals(testPiece3.validMove(0, 2), false);
		assertEquals(testPiece3.validMove(2, 2), false);
		Piece testPiece4 = new Piece(9, 9, false, false, test);
		assertEquals(testPiece4.validMove(0, 0), false);
		assertEquals(testPiece4.validMove(2, 0), false);
		assertEquals(testPiece4.validMove(0, 2), false);
		assertEquals(testPiece4.validMove(2, 2), false);
	}
	
	public void testIsKing()
	{
		Piece testPiece1 = new Piece(1, 1, true, true, test); 
		assertEquals(testPiece1.isKing(), true);
		Piece testPiece2 = new Piece(1, 1, true, false, test);
		assertEquals(testPiece2.isKing(), false);

	}
	
	public void testIsDark()
	{
		Piece testPiece1 = new Piece(1, 1, true, true, test); 
		assertEquals(testPiece1.isDark(), true);
		Piece testPiece2 = new Piece(1, 1, false, true, test);
		assertEquals(testPiece2.isDark(), false);
	}
	
	public void testHasCaptured()
	{
		Piece testPiece1 = new Piece(1, 1, true, true, test); 
		testPiece1.setCaptured(true);
		assertEquals(testPiece1.hasCaptured(), true);
		testPiece1.setCaptured(false);
		assertEquals(testPiece1.hasCaptured(), false);	
	}
	
	public void testHasMoved()
	{
		Piece testPiece1 = new Piece(1, 1, true, true, test); 
		testPiece1.setMoved(true);
		assertEquals(testPiece1.hasMoved(), true);
		testPiece1.setMoved(false);
		assertEquals(testPiece1.hasMoved(), false);	
	}
	
	public void testMoved()
	{
		Piece testPiece1 = new Piece(1, 1, true, true, test);
		test.place(testPiece1, 1, 1);
		testPiece1.setPosition(2, 2);
		assertEquals(testPiece1.hasMoved(), true);
		assertEquals(testPiece1.hasCaptured(), false);
		assertEquals(testPiece1.getX(), 2);
		assertEquals(testPiece1.getY(), 2);
		assertEquals(test.pieceAt(2, 2), testPiece1);
		
		Piece testPiece2 = new Piece(3, 3, false, true, test);
		test.place(testPiece2, 3, 3);
		testPiece2.setPosition(1, 1);
		assertEquals(testPiece2.hasCaptured(), true);
		assertEquals(testPiece2.hasMoved(), false);
		assertEquals(testPiece2.getX(), 1);
		assertEquals(testPiece2.getY(), 1);
		assertEquals(test.pieceAt(2, 2), null);
		assertEquals(test.pieceAt(1, 1), testPiece2);	
	}

}
