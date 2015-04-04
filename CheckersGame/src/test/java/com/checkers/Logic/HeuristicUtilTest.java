package com.checkers.Logic;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for HeuristicUtil.
 */
public class HeuristicUtilTest
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	
    private char[][] customCheckersBoardInstance;
    private CheckerBoard boardforTest;
    char typeR = 'r';
    char typeB = 'b';
    char empty = '_';
    char invalid = '#';
    char rKing = 'R';
    char bKing = 'B';
		
    public HeuristicUtilTest() {
    	
    }
    
    @Before
    public void setUp() {
        boardforTest = new CheckerBoard(8);
        resetCustomCheckerBoard();
        boardforTest.setCheckersBoard(customCheckersBoardInstance);
        HeuristicUtil.setboard(customCheckersBoardInstance);

    }

    @Test
    public void testPieceCount() {
    	assertEquals(12, HeuristicUtil.pieceCount('r'));	    	
    }
    
    @Test
    public void testIsUsed() {
    	assertTrue(HeuristicUtil.isUsed('r', 0, 0));
        assertFalse(HeuristicUtil.isUsed('r', 0, 1));
    }
    
    @Test
    public void testIsUsedByQueen() {
    	assertFalse(HeuristicUtil.isUsedByQueen('b', 0, 2));
        customCheckersBoardInstance[0][0] = bKing;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        assertTrue(HeuristicUtil.isUsedByQueen('b', 0, 0));
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
    

}
