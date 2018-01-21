import static org.junit.Assert.*;

import org.junit.Test;

public class CheckerBoardTest 
{

	
	CheckerBoard test = new CheckerBoard(true);
	@Test
	public void testPlace()
	{
		Piece testPiece1 = new Piece(1, 1, true, true, test);
		test.place(testPiece1, 1, 1);
		assertEquals(test.pieceAt(1, 1), testPiece1);
		Piece testPiece2 = new Piece(2, 2, true, true, test);
		test.place(testPiece2, -1, -1);
		assertEquals(test.pieceAt(2, 2), null);
		Piece testPiece3 = null;
		test.place(testPiece3, 1, 1);
		assertEquals(test.pieceAt(1, 1), testPiece1);
	}
	
	public void testPieceAt()
	{
		Piece testPiece1 = new Piece(1, 1, true, true, test);
		test.place(testPiece1, 1, 1);
		assertEquals(test.pieceAt(1, 1), testPiece1);
		assertEquals(test.pieceAt(0, 0), null);
	}
	
	public void testRemove()
	{
		Piece testPiece1 = new Piece(1, 1, true, true, test);
		test.place(testPiece1, 1, 1);
		assertEquals(test.remove(1, 1), testPiece1);
		assertEquals(test.remove(8, 9), null);
		assertEquals(test.remove(0, 0), null);
	}
	
	public void testCanEndTurn()
	{
		Piece testPiece1 = new Piece(1, 1, true, true, test);
		test.place(testPiece1, 1, 1);
		testPiece1.setMoved(true);
		assertEquals(test.canEndTurn(), true);
		testPiece1.setMoved(false);
		testPiece1.setCaptured(true);
		assertEquals(test.canEndTurn(), true);
		testPiece1.setMoved(true);
		assertEquals(test.canEndTurn(), true);
		testPiece1.setMoved(false);
		testPiece1.setCaptured(false);
		assertEquals(test.canEndTurn(), false);
	}
	
	public void testEndTurn()
	{
		Piece testPiece1 = new Piece(1, 1, true, true, test);
		testPiece1.setMoved(true);
		testPiece1.setCaptured(true);
		test.setTurn(true);
		test.endTurn();
		assertEquals(testPiece1.hasCaptured(), false);
		assertEquals(testPiece1.hasMoved(), false);
		assertEquals(test.getTurn(), false);
	}
	
	public void test()
	{
		CheckerBoard test = new CheckerBoard(true);
		Piece testPiece1 = new Piece(1, 1, true, true, test);
		test.setTurn(true);
		test.place(testPiece1, 0, 0);
		assertEquals(test.winner(), "Dark");
		assertEquals(test.getTurn(), false);
	}
	
	public void testCanSelect()
	{
		Piece testPiece1 = new Piece(1, 1, true, true, test);
		test.place(testPiece1, 1, 1);
		test.setSelect(null);
		assertEquals(test.canSelect(1, 1), true);
		assertEquals(test.canSelect(0,  0), true);
		test.setSelect(testPiece1);
		assertEquals(test.canSelect(2, 2), true);
		Piece testPiece2 = new Piece(2, 2, false, true, test);
		assertEquals(test.canSelect(3, 3), true);	
	}
	
	public void testSelect()
	{
		Piece testPiece1 = new Piece(1, 1, true, true, test);
		test.setSelect(testPiece1);
		test.select(2, 2);
		assertEquals(test.pieceAt(2, 2), testPiece1);
		assertEquals(testPiece1.getX(), 2);
		assertEquals(testPiece1.getY(), 2);
	}
}
