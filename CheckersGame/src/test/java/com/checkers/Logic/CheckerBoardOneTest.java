package com.checkers.Logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
      boardforTest = new CheckerBoard(8);
      setCustomCheckerBoard();
      boardforTest.setCheckersBoard(customCheckersBoardInstance);
  }

  @After
  public void tearDown() {
  }
  
  /**
   * Initialize a custom checker board for testing
   */
  private void setCustomCheckerBoard() {
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

  /*
   * @Test public void testPrintBoard() { fail("Not yet implemented"); }
   * @Test public void testMovePiece() { fail("Not yet implemented"); }
   */
  @Test
  public void testIsMoveable() {

    // test for different invalid cell combinations
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(7, 2, 6, 3));
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(7, 2, 5, 3));
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(7, 1, 6, 3));

    // test for impossible destination rows
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(7, 1, 9, 3));
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(7, 1, -1, 3));

    // test for impossible destination columns
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(7, 1, 5, 11));
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(7, 1, 5, -3));

    // test for empty source cell
    assertFalse("Invalid cells are accepted", boardforTest.isMoveable(3, 1, 6, 2));

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
   * @Test public void testMove() { fail("Not yet implemented"); }
   * @Test public void testHasCutsIntInt() { fail("Not yet implemented"); }
   * @Test public void testHasCutsCharArrayArrayIntInt() { fail("Not yet implemented"); }
   * @Test public void testHasMoreCuts() { fail("Not yet implemented"); }
   * @Test public void testCutPieceByType() { fail("Not yet implemented"); }
   * @Test public void testCanMove() { fail("Not yet implemented"); }
   * @Test public void testGetAllCaptures() { fail("Not yet implemented"); }
   * @Test public void testGetAllNonCaptures() { fail("Not yet implemented"); }
   * @Test public void testCloneArray() { fail("Not yet implemented"); }
   * @Test public void testGetMoveFromMinMax() { fail("Not yet implemented"); }
   * @Test public void testInvertColour() { fail("Not yet implemented"); }
   * @Test public void testFindValue() { fail("Not yet implemented"); }
   * @Test public void testCalcHeuristic() { fail("Not yet implemented"); }
   * @Test public void testEval() { fail("Not yet implemented"); }
   * @Test public void testReset() { fail("Not yet implemented"); }
   */
}
