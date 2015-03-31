package com.checkers.Logic;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Dinu
 */
public class CheckerBoardTwoTest {

    private char[][] customCheckersBoardInstance;
    private CheckerBoard boardforTest;
    char typeR = 'r';
    char typeB = 'b';
    char empty = '_';
    char invalid = '#';
    char rKing = 'R';
    char bKing = 'B';

    public CheckerBoardTwoTest() {
    }

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

    @Test
    public void testMove() {

        customCheckersBoardInstance[6][2] = empty;
        customCheckersBoardInstance[5][3] = typeR;
        customCheckersBoardInstance[7][5] = empty;
        customCheckersBoardInstance[6][4] = typeB;
        assertTrue(boardforTest.move(5, 5, 4, 4));
        assertFalse(boardforTest.move(5, 3, 5, 4));
        assertFalse(boardforTest.move(5, 5, 6, 4));
        assertFalse(boardforTest.move(0, 0, 1, 1));
        resetCustomCheckerBoard();

    }

    @Test
    public void testHasCuts() {
        customCheckersBoardInstance[6][2] = empty;
        customCheckersBoardInstance[5][3] = typeR;
        customCheckersBoardInstance[7][5] = empty;
        customCheckersBoardInstance[6][4] = typeB;
        assertTrue(boardforTest.hasCuts(6, 4));
        assertTrue(boardforTest.hasCuts(5, 3));
        assertFalse(boardforTest.hasCuts(5, 5));
        assertFalse(boardforTest.hasCuts(1, 1));
        assertTrue(boardforTest.hasCuts(customCheckersBoardInstance, 5, 3));
        assertTrue(boardforTest.hasCuts(customCheckersBoardInstance, 6, 4));
        assertFalse(boardforTest.hasCuts(customCheckersBoardInstance, 5, 5));
        assertFalse(boardforTest.hasCuts(customCheckersBoardInstance, 1, 1));
        resetCustomCheckerBoard();
    }

    @Test
    public void testHasMoreCuts() {

        boardforTest.setLastCutPieceRow(-1);
        assertFalse(boardforTest.hasMoreCuts(typeR));
    }

    @Test
    public void testCutPeiceByType() {
        customCheckersBoardInstance[3][7] = typeR;
        customCheckersBoardInstance[4][6] = typeB;
        customCheckersBoardInstance[2][6] = empty;
        customCheckersBoardInstance[5][5] = empty;
        setRBLists();
        assertTrue(boardforTest.cutPieceByType(typeR, 3, 7, 4, 6));
        assertFalse(boardforTest.cutPieceByType(typeB, 4, 6, 3, 7));
        resetCustomCheckerBoard();
    }

    /**
     * Test canMove method in the CheckerBoard class. The objective of canMove
     * method is to return true if a chip in a given location can be moved to a
     * destination location.
     */
    @Test
    public void testCanMove() {
        customCheckersBoardInstance[3][7] = typeR;
        customCheckersBoardInstance[4][6] = typeB;
        customCheckersBoardInstance[2][6] = empty;
        customCheckersBoardInstance[5][5] = empty;
        customCheckersBoardInstance[1][7] = empty;
        customCheckersBoardInstance[6][4] = empty;
        customCheckersBoardInstance[7][3] = rKing;
        customCheckersBoardInstance[5][3] = typeB;
        customCheckersBoardInstance[4][4] = empty;
        setRBLists();
        // checking sRow=dRow situtation
        assertFalse(boardforTest.canMove(customCheckersBoardInstance, 3, 7, 3, 5));
        // checking sCol=dCol situation
        assertFalse(boardforTest.canMove(customCheckersBoardInstance, 3, 7, 1, 7));
        // checking for invalid cell situations
        assertFalse(boardforTest.canMove(customCheckersBoardInstance, 3, 7, 4, 8));
        assertFalse(boardforTest.canMove(customCheckersBoardInstance, 7, 5, 8, 6));
        assertFalse(boardforTest.canMove(customCheckersBoardInstance, 0, 2, -1, 3));
        assertFalse(boardforTest.canMove(customCheckersBoardInstance, 2, 0, 1, -1));
        // checking for the situations where Math.abs(sRow - dRow) > 2
        assertFalse(boardforTest.canMove(customCheckersBoardInstance, 2, 4, 5, 5));
        // checking for situations where Math.abs(sCol - dCol) > 2
        assertFalse(boardforTest.canMove(customCheckersBoardInstance, 2, 2, 3, 5));
        // checking for situations wehere Math.abs(sRow - dRow) == 1 && Math.abs(sCol - dCol) == 1) and destination cell is empty
        //// where the chip is a King
        assertTrue(boardforTest.canMove(customCheckersBoardInstance, 7, 3, 6, 4));
        //// where the chip is blue
        assertTrue(boardforTest.canMove(customCheckersBoardInstance, 5, 3, 4, 4));
        //// where the chip is red
        assertTrue(boardforTest.canMove(customCheckersBoardInstance, 1, 5, 2, 6));
        // checking for situations where Math.abs(sRow - dRow) == 2 && Math.abs(sCol - dCol) == 2
        //// where the chip is king
        customCheckersBoardInstance[6][4] = typeB;
        setRBLists();
        assertTrue(boardforTest.canMove(customCheckersBoardInstance, 7, 3, 5, 5));
        //// where the chip is blue
        customCheckersBoardInstance[2][2] = empty;
        customCheckersBoardInstance[4][2] = typeR;
        setRBLists();
        assertTrue(boardforTest.canMove(customCheckersBoardInstance, 5, 1, 3, 3));
        //// where the chip is red
        assertTrue(boardforTest.canMove(customCheckersBoardInstance, 3, 7, 5, 5));
        resetCustomCheckerBoard();

    }

    @Test
    public void testGetAllCaptures() {

        // test when predecessorOfMultiMove == null
        Chip predecessorMultiMove = null;

        // set the board for typeR
        customCheckersBoardInstance[5][7] = empty;
        customCheckersBoardInstance[5][1] = empty;
        customCheckersBoardInstance[6][0] = empty;
        customCheckersBoardInstance[3][1] = typeB;
        customCheckersBoardInstance[1][3] = typeB;
        customCheckersBoardInstance[3][5] = typeB;
        customCheckersBoardInstance[2][0] = empty;
        customCheckersBoardInstance[1][1] = empty;
        customCheckersBoardInstance[0][4] = empty;
        customCheckersBoardInstance[4][2] = typeR;
        customCheckersBoardInstance[3][3] = typeR;

        List<int[]> captureListExpected = new ArrayList<int[]>();
        captureListExpected.add(new int[]{2, 2, 4, 0});// (row,col,row+2,col-2)
        captureListExpected.add(new int[]{2, 4, 4, 6});// (row,col,row+2,col+2)
        captureListExpected.add(new int[]{2, 6, 4, 4});// (row,col,row+2,col-2)
        // (row,col, row-2,col+2),(row,col,row-2,col-2) cuts are illegal for typeR

        List<int[]> captureListActual = new ArrayList<int[]>();
        captureListActual = boardforTest.getAllCaptures(customCheckersBoardInstance, typeR, predecessorMultiMove);
        assertEquals(captureListActual.size(), captureListExpected.size());
        for (int i = 0; i < captureListExpected.size(); i++) {
            for (int j = 0; j < captureListExpected.get(i).length; j++) {
                assertEquals(captureListActual.get(i)[j], captureListExpected.get(i)[j]);
            }
        }
        resetCustomCheckerBoard();

        // set the board for typeB
        customCheckersBoardInstance[2][6] = empty;
        customCheckersBoardInstance[4][6] = typeR;
        captureListExpected.clear();
        captureListExpected.add(new int[]{5, 5, 3, 7});// (row,col,row-2,col+2)
        captureListExpected.add(new int[]{5, 7, 3, 5});// (row,col,row-2,col-2)
        // (row,col, row+2,col+2),(row,col,row+2,col-2) cuts are illegal for typeB

        captureListActual.clear();
        captureListActual = boardforTest.getAllCaptures(customCheckersBoardInstance, typeB, predecessorMultiMove);
        assertEquals(captureListActual.size(), captureListExpected.size());
        for (int i = 0; i < captureListExpected.size(); i++) {
            for (int j = 0; j < captureListExpected.get(i).length; j++) {
                assertEquals(captureListActual.get(i)[j], captureListExpected.get(i)[j]);
            }
        }
        resetCustomCheckerBoard();


        // test when predecessorOfMultiMove != null
        predecessorMultiMove = new Chip(3, 5);

        customCheckersBoardInstance[7][1] = empty;
        customCheckersBoardInstance[7][5] = empty;
        customCheckersBoardInstance[5][1] = empty;
        customCheckersBoardInstance[5][3] = typeR;
        customCheckersBoardInstance[4][2] = typeB;
        customCheckersBoardInstance[2][4] = empty;

        captureListExpected.clear();
        captureListExpected.add(new int[]{5, 3, 7, 5});
        captureListExpected.add(new int[]{5, 3, 7, 1});
        captureListActual.clear();
        captureListActual = boardforTest.getAllCaptures(customCheckersBoardInstance, typeR, predecessorMultiMove);
        assertEquals(captureListActual.size(), captureListExpected.size());
        for (int i = 0; i < captureListExpected.size(); i++) {
            for (int j = 0; j < captureListExpected.get(i).length; j++) {
                assertEquals(captureListActual.get(i)[j], captureListExpected.get(i)[j]);
            }
        }
        resetCustomCheckerBoard();

    }

    /**
     * Initialize a custom checker board for testing and reset it
     */
    private void resetCustomCheckerBoard() {
        int boardSize = 8;
        customCheckersBoardInstance = new char[boardSize][boardSize];

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

    private void setRBLists() {
        int boardSize = customCheckersBoardInstance.length;
        boardforTest.clearTypeBList();
        boardforTest.clearTypeRList();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (customCheckersBoardInstance[i][j] == (typeB | bKing)) {
                    boardforTest.addToTypeBList(i, j);
                } else if (customCheckersBoardInstance[i][j] == (typeR | rKing)) {
                    boardforTest.addToTypeRList(i, j);
                }
            }
        }


    }
}