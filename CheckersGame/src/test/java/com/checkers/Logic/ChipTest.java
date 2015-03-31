
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
public class ChipTest {
    Chip chipUnderTest;
    Chip kingUndertest;
    
    public ChipTest() {
        chipUnderTest= new Chip(2, 4);
        kingUndertest= new Chip(1, 5);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        chipUnderTest.setIsKing(false);
        chipUnderTest.setOnBoard(true);
        kingUndertest.setIsKing(true);
        kingUndertest.setOnBoard(true);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetters() {
        assertEquals(chipUnderTest.getRow(), 4);
        assertEquals(chipUnderTest.getCol(), 2);
        assertEquals(kingUndertest.getRow(), 5);
        assertEquals(kingUndertest.getCol(), 1);
        assertFalse(chipUnderTest.isKing());
        assertTrue(kingUndertest.isKing());
        assertTrue(chipUnderTest.isOnBoard());
        assertTrue(kingUndertest.isOnBoard());
    }
    
    @Test
    public void testEquals(){
        // check whether the method works for chips equal to the given chip
        assertTrue(chipUnderTest.equals(new Chip(2,4)));
        assertTrue(kingUndertest.equals(new Chip(1, 5)));
        // check whether the method do not return true for the chips which are equals with other chips in the context
        assertFalse(chipUnderTest.equals(new Chip(1, 5)));
        assertFalse(kingUndertest.equals(new Chip(2, 4)));
        // check whether the method return false for the chips having same column or row
        assertFalse(chipUnderTest.equals(new Chip(2, 2)));
        assertFalse(chipUnderTest.equals(new Chip(0, 4)));
        assertFalse(kingUndertest.equals(new Chip(1, 7)));
        assertFalse(kingUndertest.equals(new Chip(3, 7)));
        // check whether the method return false for completely different chips
        assertFalse(chipUnderTest.equals(new Chip(3, 1)));
        assertFalse(kingUndertest.equals(new Chip(2, 4)));
 
        
    }
}