package com.checkers.Logic;

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
    assertFalse("Can't allow large moves", boardforTest.isMoveable(2, 2, 6, 6));
    assertFalse("Can't allow large moves", boardforTest.isMoveable(5, 5, 3, 1));

    // test for 1 cell moves
    assertTrue("Valid 1 move cell", boardforTest.isMoveable(2, 2, 3, 3));
    assertTrue("Valid 1 move cell", boardforTest.isMoveable(7, 7, 6, 6));

    // test for valid cutting pieces
    assertFalse("A red piece can't cut a red piece", boardforTest.isMoveable(1, 3, 3, 5));
    customCheckersBoardInstance[3][3] = typeB;
    assertTrue("A red piece can cut a black piece", boardforTest.isMoveable(2, 2, 4, 4));
    assertFalse("A black piece can't cut a black piece", boardforTest.isMoveable(6, 4, 4, 6));
    customCheckersBoardInstance[4][6] = typeR;
    assertTrue(boardforTest.isMoveable(5, 7, 3, 5));

  }
  /*
   * @Test public void testCutPiece() { fail("Not yet implemented"); }
   * @Test public void testPieceCount() { fail("Not yet implemented"); }
   * @Test public void testIsUsed() { fail("Not yet implemented"); }
   * @Test public void testIsUsedByNormalPiece() { fail("Not yet implemented"); }
   * @Test public void testIsUsedByQueen() { fail("Not yet implemented"); }
   * @Test public void testGetSize() { fail("Not yet implemented"); }
   * @Test public void testGetTypeRList() { fail("Not yet implemented"); }
   * @Test public void testGetTypeBList() { fail("Not yet implemented"); }
   * @Test public void testCalcHeuristicValue() { fail("Not yet implemented"); }
   * @Test public void testCalcHeuristicValueEnding() { fail("Not yet implemented"); }
   * @Test public void testGetTypeR() { fail("Not yet implemented"); }
   * @Test public void testGetTypeB() { fail("Not yet implemented"); }
   * @Test public void testIsMoveableByType() { fail("Not yet implemented"); }
   * @Test public void testMovePieceByType() { fail("Not yet implemented"); }
   * @Test public void testHasMovesIntInt() { fail("Not yet implemented"); }
   * @Test public void testHasMovesChar() { fail("Not yet implemented"); }
   */
}
