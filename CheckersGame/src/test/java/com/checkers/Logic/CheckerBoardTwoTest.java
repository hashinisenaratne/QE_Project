package com.checkers.Logic;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        setCustomCheckerBoard();
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
        setCustomCheckerBoard();

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
        setCustomCheckerBoard();
    }
    
    @Test
    public void testHasMoreCuts(){
 
         boardforTest.setLastCutPieceRow(-1);
         assertFalse(boardforTest.hasMoreCuts(typeR));
    }
    
    @Test
    public void testCutPeiceByType(){
        customCheckersBoardInstance[3][7]=typeR;
        customCheckersBoardInstance[4][6]= typeB;
        customCheckersBoardInstance[2][6]= empty;
        customCheckersBoardInstance[5][5]= empty;
        setRBLists();
        assertTrue(boardforTest.cutPieceByType(typeR, 3, 7, 4, 6));
        assertFalse(boardforTest.cutPieceByType(typeB, 4, 6, 3, 7));
        setCustomCheckerBoard();
    }
    
    @Test
    public void testCanMove(){
        
    }
    /**
     * Initialize a custom checker board for testing
     */
    private void setCustomCheckerBoard() {
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
    
    
   private void setRBLists(){
       int boardSize=customCheckersBoardInstance.length;
       boardforTest.clearTypeBList();
       boardforTest.clearTypeRList();
       for(int i=0;i<boardSize;i++){
           for (int j=0;j<boardSize;j++){
               if(customCheckersBoardInstance[i][j]==typeB){
                   boardforTest.addToTypeBList(i, j);
               }else if(customCheckersBoardInstance[i][j]==typeR){
                   boardforTest.addToTypeRList(i, j);
                          }
           }
       }
       
       
   }
}