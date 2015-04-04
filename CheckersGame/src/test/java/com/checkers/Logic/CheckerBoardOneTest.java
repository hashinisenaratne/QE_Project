package com.checkers.Logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
*
* @author Laksheen
*/
public class CheckerBoardOneTest {
  
  private char[][] customCheckersBoardInstance;
  private CheckerBoard boardforTest;
  char typeR = 'r';
  char typeB = 'b';
  char empty = '_';
  char invalid = '#';
  char rKing = 'R';
  char bKing = 'B';
  private List<Chip> typeRList1;
  private List<Chip> typeBList2;

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
      boardforTest = new CheckerBoard(8);
    resetCustomCheckerBoard();
      boardforTest.setCheckersBoard(customCheckersBoardInstance);
  }

  @After
  public void tearDown() {
  }
  
  /**
   * Initialize a custom checker board for testing
   */
  private void resetCustomCheckerBoard() {
      int boardSize = 8;
      customCheckersBoardInstance = new char[boardSize][boardSize];
      char typeR = 'r';
      char typeB = 'b';
      char empty = '_';
      char invalid = '#';

      for (int i = 0; i < boardSize; i++) {
          for (int j = 0; j < boardSize; j++) {
              if ((i + j) % 2 == 0) {
                  customCheckersBoardInstance[i][j] = empty;    //EMPTY cell
              } else {
                  customCheckersBoardInstance[i][j] = invalid;
              }
          }
      }
      for (int i = 0; i < (boardSize / 2) - 1; i++) {
          int j;
          if (i % 2 == 0) {
              j = 0;
          } else {
              j = 1;
          }
          while (j < boardSize) {
              customCheckersBoardInstance[i][j] = typeR;    //RED piece
              j += 2;
          }
      }
      for (int i = boardSize - 1; i >= (boardSize) / 2 + 1; i--) {
          int j;
          if (i % 2 == 0) {
              j = 0;
          } else {
              j = 1;
          }
          while (j < boardSize) {
              customCheckersBoardInstance[i][j] = typeB;     //BLACK piece
              j += 2;
          }
      }


  }
  
  @Test
  public void testCheckerBoard() {
    assertNotNull("checker board is null",customCheckersBoardInstance);
  }

  @Test
  public void testPrintBoard() {
    // boardforTest.printBoard();
  }

  @Test
  public void testMovePiece() {
    // test for invalid moves
    assertFalse(boardforTest.movePiece(0, 9, 2, 5));
    assertFalse(boardforTest.movePiece(6, 4, 4, 6));

    // test for valid moves normal
    assertTrue(boardforTest.movePiece(2, 4, 3, 5));
    assertTrue(boardforTest.movePiece(5, 7, 4, 6));

    // test for valid moves advanced
    customCheckersBoardInstance[5][3] = typeR;
    boardforTest.removeFromTypeBList(5, 3);
    boardforTest.addToTypeRList(5, 3);
    customCheckersBoardInstance[7][5] = empty;
    boardforTest.removeFromTypeBList(7, 5);
    // boardforTest.printBoard();
    assertTrue(boardforTest.movePiece(5, 3, 7, 5));
    // boardforTest.printBoard();

    customCheckersBoardInstance[2][4] = typeB;
    boardforTest.addToTypeBList(2, 4);
    customCheckersBoardInstance[0][2] = empty;
    boardforTest.removeFromTypeRList(7, 5);
    // boardforTest.printBoard();
    assertTrue(boardforTest.movePiece(2, 4, 0, 2));
    // boardforTest.printBoard();

  }

  @Test
  public void testIsMoveable() {

    // test for different invalid cell combinations
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(7, 2, 6, 2));
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(6, 0, 1, 4));
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(5, 2, 6, 3));

    // test for impossible destination rows
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(7, 1, 9, 3));
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(7, 1, -1, 3));

    // test for impossible destination columns
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(7, 1, 5, 11));
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(7, 1, 5, -3));

    // test for empty source cell
    customCheckersBoardInstance[3][1] = empty;
    assertFalse("Source cell is empty, no piece to move", boardforTest.isMoveable(3, 1, 6, 2));

    // test for not empty destination cell, from a not empty source cell
    assertFalse("Destination cell is occupied, can't move", boardforTest.isMoveable(0, 2, 2, 4));

    // test for a move in the same column
    assertFalse("Can't move in the same column", boardforTest.isMoveable(1, 3, 3, 3));

    // test for a red piece to move backward
    customCheckersBoardInstance[1][1] = empty;
    assertFalse("Piece cannot move backward", boardforTest.isMoveable(2, 2, 1, 1));

    // test for a black piece to move backward
    customCheckersBoardInstance[6][6] = empty;
    assertFalse("Piece cannot move backward", boardforTest.isMoveable(5, 7, 6, 6));

    // test for a larger move for both pieces
    customCheckersBoardInstance[5][3] = empty;
    assertFalse("Can't allow large moves", boardforTest.isMoveable(2, 2, 5, 3));
    customCheckersBoardInstance[1][1] = typeR;
    customCheckersBoardInstance[2][6] = empty;
    assertFalse("Can't allow large moves", boardforTest.isMoveable(1, 1, 2, 6));
    assertFalse("Can't allow large moves", boardforTest.isMoveable(2, 2, 6, 6));

    // test for 1 cell moves
    assertTrue("Valid 1 move cell", boardforTest.isMoveable(2, 2, 3, 3));
    // assertTrue("Valid 1 move cell", boardforTest.isMoveable(7, 7, 6, 6));

    // test for valid cutting pieces
    customCheckersBoardInstance[3][3] = typeB;
    assertTrue("A red piece can cut a black piece", boardforTest.isMoveable(2, 2, 4, 4));
    assertFalse("A black piece can't cut a black piece", boardforTest.isMoveable(1, 3, 3, 5));

    // System.out.println("test Is Moveable");
    // boardforTest.printBoard();
  }

  @Test
  public void testCutPiece() {

    // assertFalse(boardforTest.cutPiece(attackerRow, attackerCol, victimRow, victimCol));
    assertFalse(boardforTest.cutPiece(-1, 5, 2, 5));
    assertFalse(boardforTest.cutPiece(2, 5, -4, 5));
    assertFalse(boardforTest.cutPiece(0, -3, 2, 5));
    assertFalse(boardforTest.cutPiece(1, 5, 2, -9));

    assertFalse(boardforTest.cutPiece(10, 5, 2, 5));
    assertFalse(boardforTest.cutPiece(2, 5, 9, 5));
    assertFalse(boardforTest.cutPiece(0, 8, 2, 5));
    assertFalse(boardforTest.cutPiece(1, 5, 2, 15));

    assertFalse(boardforTest.cutPiece(4, 2, 2, 5));
    assertFalse(boardforTest.cutPiece(3, 7, 5, 5));
    assertFalse(boardforTest.cutPiece(5, 1, 4, 0));
    assertFalse(boardforTest.cutPiece(2, 4, 3, 5));

    assertFalse(boardforTest.cutPiece(5, 3, 5, 3));
    assertFalse(boardforTest.cutPiece(2, 6, 2, 6));

    boardforTest.printBoard();
    customCheckersBoardInstance[2][2] = empty;
    boardforTest.removeFromTypeRList(2, 2);
    customCheckersBoardInstance[4][2] = typeR;
    boardforTest.addToTypeRList(4, 2);
    assertTrue(boardforTest.cutPiece(5, 1, 4, 2));
    setUp();
    customCheckersBoardInstance[2][2] = empty;
    boardforTest.removeFromTypeRList(2, 2);
    customCheckersBoardInstance[4][2] = typeR;
    boardforTest.addToTypeRList(4, 2);
    assertTrue(boardforTest.cutPiece(5, 3, 4, 2));

    customCheckersBoardInstance[5][5] = empty;
    boardforTest.removeFromTypeBList(5, 5);
    customCheckersBoardInstance[3][5] = typeB;
    boardforTest.addToTypeBList(3, 5);
    assertTrue(boardforTest.cutPiece(2, 4, 3, 5));
    setUp();
    customCheckersBoardInstance[5][5] = empty;
    boardforTest.removeFromTypeBList(5, 5);
    customCheckersBoardInstance[3][5] = typeB;
    boardforTest.addToTypeBList(3, 5);
    assertTrue(boardforTest.cutPiece(2, 6, 3, 5));

    setUp();
    customCheckersBoardInstance[7][5] = rKing;
    boardforTest.removeFromTypeBList(7, 5);
    boardforTest.addToTypeRList(7, 5);
    customCheckersBoardInstance[5][3] = empty;
    boardforTest.removeFromTypeBList(5, 3);
    assertTrue(boardforTest.cutPiece(7, 5, 6, 4));

    setUp();
    customCheckersBoardInstance[7][5] = rKing;
    boardforTest.removeFromTypeBList(7, 5);
    boardforTest.addToTypeRList(7, 5);
    customCheckersBoardInstance[5][7] = empty;
    boardforTest.removeFromTypeBList(5, 7);
    assertTrue(boardforTest.cutPiece(7, 5, 6, 6));

    setUp();
    customCheckersBoardInstance[0][4] = bKing;
    boardforTest.removeFromTypeRList(0, 4);
    boardforTest.addToTypeBList(0, 4);
    customCheckersBoardInstance[2][2] = empty;
    boardforTest.removeFromTypeRList(2, 2);
    assertTrue(boardforTest.cutPiece(0, 4, 1, 3));

    setUp();
    customCheckersBoardInstance[0][4] = bKing;
    boardforTest.removeFromTypeRList(0, 4);
    boardforTest.addToTypeBList(0, 4);
    customCheckersBoardInstance[2][6] = empty;
    boardforTest.removeFromTypeRList(2, 6);
    assertTrue(boardforTest.cutPiece(0, 4, 1, 5));

    setUp();
    customCheckersBoardInstance[5][5] = typeR;
    boardforTest.removeFromTypeBList(5, 5);
    boardforTest.addToTypeRList(5, 5);
    assertFalse(boardforTest.cutPiece(5, 3, 5, 5));
  }

  @Test
  public void testGetSize() {
    assertEquals(8, boardforTest.getSize());

  }

  @Test
  public void testGetTypeRList() {
    assertNotNull(boardforTest.getTypeRList());

  }

  @Test
  public void testGetTypeBList() {
    assertNotNull(boardforTest.getTypeBList());

  }
  
  @Test
  public void testPieceCount() {
    assertTrue(boardforTest.pieceCount(typeB) <= 12);
    assertTrue(boardforTest.pieceCount(typeR) <= 12);

  }

  @Test
  public void testIsUsed() {
    assertTrue(boardforTest.isUsed(typeB, 5, 3));
    assertTrue(boardforTest.isUsed(typeR, 1, 5));

    assertFalse(boardforTest.isUsed(typeB, 1, 3));
    assertFalse(boardforTest.isUsed(typeR, 6, 2));

    customCheckersBoardInstance[7][3] = rKing;
    assertTrue(boardforTest.isUsed(typeR, 7, 3));

    customCheckersBoardInstance[0][4] = bKing;
    assertTrue(boardforTest.isUsed(typeB, 0, 4));
  }

  @Test
  public void testIsUsedByNormalPiece() {
    assertTrue(boardforTest.isUsedByNormalPiece(typeB, 5, 5));
    assertFalse(boardforTest.isUsedByNormalPiece(typeR, 5, 1));

  }

  @Test
  public void testIsUsedByQueen() {
    customCheckersBoardInstance[7][3] = rKing;
    assertTrue(boardforTest.isUsedByQueen(typeR, 7, 3));

    assertFalse(boardforTest.isUsedByQueen(typeB, 7, 5));

  }

  /*
   * @Test public void testCalcHeuristicValue() { fail("Not yet implemented"); }
   * @Test public void testCalcHeuristicValueEnding() { fail("Not yet implemented"); }
   */

  @Test
  public void testGetTypeR() {
    assertEquals('r', boardforTest.getTypeR());

  }

  @Test
  public void testGetTypeB() {
    assertEquals('b', boardforTest.getTypeB());

  }

  /*
   * @Test public void testIsMoveableByType() { fail("Not yet implemented"); }
   * @Test public void testMovePieceByType() { fail("Not yet implemented"); }
   * @Test public void testHasMovesIntInt() { fail("Not yet implemented"); }
   */

  @Test
  public void testHasMoves() {
    assertTrue(boardforTest.hasMoves(5, 3));
    assertTrue(boardforTest.hasMoves(2, 6));
    assertTrue(boardforTest.hasMoves(5, 1));
    assertFalse(boardforTest.hasMoves(1, 1));
    customCheckersBoardInstance[4][4] = typeB;
    customCheckersBoardInstance[3][3] = typeR;

    System.out.println("test Has Moves");
    boardforTest.printBoard();
    assertTrue(boardforTest.hasMoves(3, 3));
    customCheckersBoardInstance[4][2] = typeR;
    customCheckersBoardInstance[5][5] = empty;
    assertTrue(boardforTest.hasMoves(3, 3));
    assertTrue(boardforTest.hasMoves(5, 3));
    assertTrue(boardforTest.hasMoves(5, 7));

    customCheckersBoardInstance[3][1] = typeR;
    customCheckersBoardInstance[6][0] = empty;
    assertTrue(boardforTest.hasMoves(4, 2));

    customCheckersBoardInstance[4][0] = typeR;
    customCheckersBoardInstance[6][0] = typeB;
    customCheckersBoardInstance[3][3] = empty;
    assertTrue(boardforTest.hasMoves(5, 1));
  }

  @Test
  public void testHasMovesChar() {
    assertTrue(boardforTest.hasMoves(typeB));
    assertTrue(boardforTest.hasMoves(typeR));

    customCheckersBoardInstance[3][1] = typeB;
    customCheckersBoardInstance[3][3] = typeB;
    customCheckersBoardInstance[3][5] = typeB;
    customCheckersBoardInstance[3][7] = typeB;
    customCheckersBoardInstance[4][0] = typeB;
    customCheckersBoardInstance[4][2] = typeB;
    customCheckersBoardInstance[4][4] = typeB;
    customCheckersBoardInstance[4][6] = typeB;

    customCheckersBoardInstance[7][1] = empty;
    customCheckersBoardInstance[7][3] = empty;
    customCheckersBoardInstance[7][5] = empty;
    customCheckersBoardInstance[7][7] = empty;
    customCheckersBoardInstance[6][0] = empty;
    customCheckersBoardInstance[6][2] = empty;
    customCheckersBoardInstance[6][4] = empty;
    customCheckersBoardInstance[6][6] = empty;

    assertFalse(boardforTest.hasMoves(typeB));
    assertFalse(boardforTest.hasMoves(typeR));
  }

}
