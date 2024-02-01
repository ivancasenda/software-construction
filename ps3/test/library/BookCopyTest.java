package library;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * Test suite for BookCopy ADT.
 */
public class BookCopyTest {

    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     * 
     * BookCopy()
     * book instance
     * getBook()
     * book instance
     * setCondition()
     * good, damaged, multiple set
     * getCondition()
     * good, damaged
     * toString()
     * good, damaged
     * 
     * 
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    @Test
    public void testExampleTest() {
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        BookCopy copy = new BookCopy(book);
        assertEquals(book, copy.getBook());
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testBookCopyConstructor() {
    	Book book = new Book("Harry Potter", Arrays.asList("JK Rowling"), 2003);
    	BookCopy copy = new BookCopy(book);
    	assertEquals(book, copy.getBook());
    	assertEquals(BookCopy.Condition.GOOD, copy.getCondition());
    	assertEquals(book.toString() + "," + BookCopy.Condition.GOOD, copy.toString());
    }
    
    @Test
    public void testGetBook() {
    	Book book = new Book("Juno", Arrays.asList("Tina George", "Cindy Clarefield", "Amy Schumer"), 2010);
    	BookCopy copy = new BookCopy(book);
    	assertEquals(book, copy.getBook());
    	assertEquals(book.toString(), copy.getBook().toString());
    }
    
    @Test
    public void testSetConditionGood() {
    	Book book = new Book("Generation Z", Arrays.asList("Han Goodman"), 1994);
    	BookCopy copy = new BookCopy(book);
    	copy.setCondition(BookCopy.Condition.GOOD);
    	assertEquals(BookCopy.Condition.GOOD, copy.getCondition());
    }
    
    @Test
    public void testSetConditionDamaged() {
    	Book book = new Book("Wildlife", Arrays.asList("Sandy Forth", "Mary Elizabeth"), 1920);
    	BookCopy copy = new BookCopy(book);
    	copy.setCondition(BookCopy.Condition.DAMAGED);
    	assertEquals(BookCopy.Condition.DAMAGED, copy.getCondition());
    }
    
    @Test
    public void testSetConditionMultipleTimes() {
    	Book book = new Book("2008 Financial Crisis", Arrays.asList("Prof. Richard Henry"), 2012);
    	BookCopy copy = new BookCopy(book);
    	copy.setCondition(BookCopy.Condition.DAMAGED);
    	copy.setCondition(BookCopy.Condition.GOOD);
    	assertEquals(BookCopy.Condition.GOOD, copy.getCondition());
    }
    
    @Test
    public void testToStringConditionGood() {
    	Book book = new Book("SARS Epidemic", Arrays.asList("Dr. Debra Brix"), 2007);
    	BookCopy copy = new BookCopy(book);
    	assertEquals(book.toString() + "," + BookCopy.Condition.GOOD, copy.toString());
    }
    
    @Test
    public void testToStringConditionDamaged() {
    	Book book = new Book("Ebola", Arrays.asList("Dr. Drake Ramorez"), 2007);
    	BookCopy copy = new BookCopy(book);
    	copy.setCondition(BookCopy.Condition.DAMAGED);
    	assertEquals(book.toString() + "," + BookCopy.Condition.DAMAGED, copy.toString());
    }


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
