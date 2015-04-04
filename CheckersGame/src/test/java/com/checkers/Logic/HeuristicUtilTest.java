package com.checkers.Logic;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
        resetCustomCheckerBoard();
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
        resetCustomCheckerBoard();
        HeuristicUtil.setboard(customCheckersBoardInstance);
    }
    
    @Test
    public void testIsOnlyKings() {
    	assertFalse(HeuristicUtil.isOnlyKings());
        clearCustomCheckerBoard();
        customCheckersBoardInstance[0][0] = bKing;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        assertTrue(HeuristicUtil.isOnlyKings());
        resetCustomCheckerBoard();
        HeuristicUtil.setboard(customCheckersBoardInstance);
    }
    
    @Test
    public void testCalcBoardHeuristicValue3() {
    	customCheckersBoardInstance[0][0] = bKing;
        customCheckersBoardInstance[0][2] = rKing;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        HashMap<Character, Integer> map = HeuristicUtil.calcBoardHeuristicValue3();
        assertEquals(101, (int)map.get(typeB));
        assertEquals(72, (int)map.get(typeR));
        resetCustomCheckerBoard();
        HeuristicUtil.setboard(customCheckersBoardInstance);
    }
    
    @Test
    public void testCalcBoardHeuristicValue2() {
    	customCheckersBoardInstance[0][0] = bKing;
        customCheckersBoardInstance[0][2] = rKing;
        customCheckersBoardInstance[0][4] = typeB;
        customCheckersBoardInstance[7][1] = typeR;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        HashMap<Character, Integer> map = HeuristicUtil.calcBoardHeuristicValue2();
        assertEquals(79, (int)map.get(typeB));
        assertEquals(62, (int)map.get(typeR));
        resetCustomCheckerBoard();
        HeuristicUtil.setboard(customCheckersBoardInstance);
    }
    
    @Test
    public void testCalcBoardHeuristicValue4() {
    	customCheckersBoardInstance[0][0] = bKing;
        customCheckersBoardInstance[0][2] = rKing;
        customCheckersBoardInstance[0][4] = typeB;
        customCheckersBoardInstance[7][1] = typeR;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        HashMap<Character, Integer> map = HeuristicUtil.calcBoardHeuristicValue4();
        assertEquals(60, (int)map.get(typeB));
        assertEquals(56, (int)map.get(typeR));     
        resetCustomCheckerBoard();
        HeuristicUtil.setboard(customCheckersBoardInstance);
    }
    
    @Test
    public void testCalcBoardHeuristicValueEnding1() {
    	customCheckersBoardInstance[0][0] = bKing;
        customCheckersBoardInstance[0][2] = rKing;
        customCheckersBoardInstance[0][4] = typeB;
        customCheckersBoardInstance[7][1] = typeR;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        HashMap<Character, Integer> map = HeuristicUtil.calcBoardHeuristicValueEnding1();
        assertEquals(1, (int)map.get(typeB));
        assertEquals(5, (int)map.get(typeR));
        resetCustomCheckerBoard();
        HeuristicUtil.setboard(customCheckersBoardInstance);
    }
    
    @Test
    public void testCalcBoardHeuristicValueEnding2() {
    	customCheckersBoardInstance[0][0] = bKing;
        customCheckersBoardInstance[0][2] = rKing;
        customCheckersBoardInstance[0][4] = typeB;
        customCheckersBoardInstance[7][1] = typeR;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        HashMap<Character, Integer> map = HeuristicUtil.calcBoardHeuristicValueEnding2();
        assertEquals(7, (int)map.get(typeB));
        assertEquals(7, (int)map.get(typeR));
        resetCustomCheckerBoard();
        HeuristicUtil.setboard(customCheckersBoardInstance);
    }
    
    @Test
    public void testCalcHeuristicValueEnding() {
    	customCheckersBoardInstance[0][0] = bKing;
        customCheckersBoardInstance[0][2] = rKing;
        customCheckersBoardInstance[0][4] = typeB;
        customCheckersBoardInstance[7][1] = typeR;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        assertEquals(-3, HeuristicUtil.calcHeuristicValueEnding(customCheckersBoardInstance, typeB));
        assertEquals(3, HeuristicUtil.calcHeuristicValueEnding(customCheckersBoardInstance, typeR));
        assertEquals(-1, HeuristicUtil.calcHeuristicValueEnding(customCheckersBoardInstance, empty));
        resetCustomCheckerBoard();
        HeuristicUtil.setboard(customCheckersBoardInstance);
    }
    
    @Test
    public void testCalcHeuristicValue() {
    	customCheckersBoardInstance[0][0] = bKing;
        customCheckersBoardInstance[0][2] = rKing;
        customCheckersBoardInstance[0][4] = typeB;
        customCheckersBoardInstance[7][1] = typeR;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        assertEquals(29, HeuristicUtil.calcHeuristicValue(customCheckersBoardInstance, typeB));
        assertEquals(-29, HeuristicUtil.calcHeuristicValue(customCheckersBoardInstance, typeR));
        assertEquals(-1, HeuristicUtil.calcHeuristicValue(customCheckersBoardInstance, empty));
        
        clearCustomCheckerBoard();
        customCheckersBoardInstance[0][0] = bKing;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        assertEquals(0, HeuristicUtil.calcHeuristicValue(customCheckersBoardInstance, typeB));
        resetCustomCheckerBoard();
        HeuristicUtil.setboard(customCheckersBoardInstance);
        
        clearCustomCheckerBoard();
        customCheckersBoardInstance[0][0] = bKing;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        assertEquals(0, HeuristicUtil.calcHeuristicValue(customCheckersBoardInstance, typeR));
        resetCustomCheckerBoard();
        HeuristicUtil.setboard(customCheckersBoardInstance);
        
        clearCustomCheckerBoard();
        customCheckersBoardInstance[0][0] = rKing;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        assertEquals(0, HeuristicUtil.calcHeuristicValue(customCheckersBoardInstance, typeR));
        resetCustomCheckerBoard();
        HeuristicUtil.setboard(customCheckersBoardInstance);
        
        clearCustomCheckerBoard();
        customCheckersBoardInstance[0][0] = rKing;
        HeuristicUtil.setboard(customCheckersBoardInstance);
        assertEquals(0, HeuristicUtil.calcHeuristicValue(customCheckersBoardInstance, typeB));
        resetCustomCheckerBoard();
        HeuristicUtil.setboard(customCheckersBoardInstance);
    }
    
    /**
     * Initialize a custom checker board for testing and reset it
     */
    private void resetCustomCheckerBoard() {
        int boardSize = 8;
        clearCustomCheckerBoard();
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
    
    private void clearCustomCheckerBoard() {
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
    }
    

}
