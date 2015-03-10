/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Logic.CheckerBoard;
import Logic.GameEngine;
import java.util.Arrays;
import java.util.Collection;
import javax.print.DocFlavor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Dinu
 */
@RunWith(Parameterized.class)
public class CheckerBoardTest2 {
    private int srow;
    private int scol;
    private int drow;
    private int dcol;
    private boolean movable;
    private char[][] customCheckersBoardInstance;
            
    
    public CheckerBoardTest2(int srow, int scol, int drow, int dcol, boolean movable) {
        srow= this.srow;
        scol= this.scol;
        drow= this.drow;
        dcol= this.dcol;
        movable= this.movable;
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        CheckerBoard boardforTest= new CheckerBoard(8);
        boardforTest.setCheckersBoard(customCheckersBoardInstance);
                         
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Parameterized.Parameters
    public static Collection Numbers() {
        return Arrays.asList(new Object[][]{{2314,true},{456834,true},{678423,false},{4632489,false}});
}
     @Test
     public void hello() {
         assertEquals(movable, scol);
     }
}