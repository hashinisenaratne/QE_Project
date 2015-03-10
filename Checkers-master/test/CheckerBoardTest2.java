/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Logic.CheckerBoard;
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
public class CheckerBoardTest2 {

    private char[][] customCheckersBoardInstance;
    private CheckerBoard boardforTest;

    public CheckerBoardTest2() {
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
    public void TestMove() {
        char typeR = 'r';
        char typeB = 'b';
        char empty = '_';
        char invalid = '#';
        customCheckersBoardInstance[6][2] = empty;
        customCheckersBoardInstance[5][3] = typeR;
        customCheckersBoardInstance[7][5] = empty;
        customCheckersBoardInstance[6][4] = typeB;
        assertTrue(boardforTest.move(5, 5, 4, 4));
        assertFalse(boardforTest.move(5, 3, 5, 4));
        assertFalse(boardforTest.move(5, 5, 6, 4));

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
}