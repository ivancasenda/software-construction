package library;

import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for Book ADT.
 */
public class BookTest {

    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     * 
     * title length = 1, length > 1
     * title that contains number, special character, and letter
     * 
     * author list size = 1 and 1 > 1
     * author name contains number, special character, and letter
     * 
     * publication year 0, 0 < year < 10000, and 9999
     * 
     * toString
     * authors len = 1 and len > 1
     * 
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    @Test
    public void testExampleTest() {
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        assertEquals("This Test Is Just An Example", book.getTitle());
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testConstructor() {
    	Book book = new Book("I", Arrays.asList("Arthur M"), 1999);
    	assertEquals("I", book.getTitle());
    	assertEquals(1, book.getAuthors().size());
    	assertEquals("Arthur M", book.getAuthors().get(0));
    	assertEquals(1999, book.getYear());
    }
    
    @Test
    public void testGetTitleLengthOne() {
    	Book book = new Book("$", Arrays.asList("Arthur M"), 1999);
    	assertEquals("$", book.getTitle());
    }
    
    @Test
    public void testGetTitleLengthMoreThanOne() {
    	Book book = new Book("Harry Potter", Arrays.asList("JK Rowling"), 2004);
    	assertEquals("Harry Potter", book.getTitle());
    }
    
    @Test
    public void testGetTitleWithSpecialCharacterAndNumber() {
    	Book book = new Book("Hello world!!! 200", Arrays.asList("Alan Turing", "Djikstra"), 1808);
    	assertEquals("Hello world!!! 200", book.getTitle());
    }
    
    @Test
    public void testGetAuthorsSizeOne() {
    	Book book = new Book("Prisoner", Arrays.asList("Robert J Field"), 117);
    	assertEquals(1, book.getAuthors().size());
    	assertEquals("Robert J Field", book.getAuthors().get(0));
    }
    
    @Test
    public void testGetAuthorsSizeMoreThanOne() {
    	Book book = new Book("Little Feelings", Arrays.asList("Tina Stone", "George Soros", "Regina Specter"), 1990);
    	assertEquals(3, book.getAuthors().size());
    	assertEquals("Tina Stone", book.getAuthors().get(0));
    	assertEquals("George Soros", book.getAuthors().get(1));
    	assertEquals("Regina Specter", book.getAuthors().get(2));
    }
    
    @Test
    public void testGetAuthorsWithSpecialCharacterAndNumber() {
    	Book book = new Book("Auction 101", Arrays.asList("William 7th", "Charles/Kate"), 14);
    	assertEquals(2, book.getAuthors().size());
    	assertEquals("William 7th", book.getAuthors().get(0));
    	assertEquals("Charles/Kate", book.getAuthors().get(1));
    }
    
    @Test
    public void testPublicationYearZero() {
    	Book book = new Book("Moment of Birth", Arrays.asList("Sir M Cain"), 0);
    	assertEquals(0, book.getYear());
    }
    
    @Test
    public void testPublicationYearMillenial() {
    	Book book = new Book("Computing", Arrays.asList("Andrew Paulo"), 1997);
    	assertEquals(1997, book.getYear());
    }
    
    @Test
    public void testToStringOneAuthor() {
    	Book book = new Book("Morale and Impulse", Arrays.asList("Athena"), 1290);
    	assertEquals("Morale and Impulse,[Athena],1290", book.toString());
    }
    
    @Test
    public void testToStringMultipleAuthor() {
    	Book book = new Book("Love and Sacrifice", Arrays.asList("Xiu Chan", "Chang Xiping", "Jackie Chou"), 2005);
    	assertEquals("Love and Sacrifice,[Xiu Chan, Chang Xiping, Jackie Chou],2005", book.toString());
    }
    
    

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
