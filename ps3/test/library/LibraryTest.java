package library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test suite for Library ADT.
 */
@RunWith(Parameterized.class)
public class LibraryTest {

    /*
     * Note: all the tests you write here must be runnable against any
     * Library class that follows the spec.  JUnit will automatically
     * run these tests against both SmallLibrary and BigLibrary.
     */

    /**
     * Implementation classes for the Library ADT.
     * JUnit runs this test suite once for each class name in the returned array.
     * @return array of Java class names, including their full package prefix
     */
    @Parameters(name="{0}")
    public static Object[] allImplementationClassNames() {
        return new Object[] { 
            "library.SmallLibrary", 
            "library.BigLibrary"
        }; 
    }

    /**
     * Implementation class being tested on this run of the test suite.
     * JUnit sets this variable automatically as it iterates through the array returned
     * by allImplementationClassNames.
     */
    @Parameter
    public String implementationClassName;    

    /**
     * @return a fresh instance of a Library, constructed from the implementation class specified
     * by implementationClassName.
     */
    public Library makeLibrary() {
        try {
            Class<?> cls = Class.forName(implementationClassName);
            return (Library) cls.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     * 
     * buy()
     * existing book instance
     * 
     * checkout()
     * available book copy
     * 
     * checkin()
     * with book copy that has been checkout
     * 
     * isAvailable()
     * with book copy that is available
     * and book copy that is unavailable
     * 
     * allCopies()
     * with some book checkout and available
     * with book not exist in library
     * 
     * availableCopies()
     * with no available book in library
     * with some available book in library
     * with book not exist in library
     * 
     * find()
     * with empty string
     * string query exist in the book title
     * string query exist in the book author
     * string query exist on 2 book with the same title and authors but different publication year
     * 
     * lose()
     * 
     * 
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    @Test
    public void testExampleTest() {
        Library library = makeLibrary();
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        assertEquals(Collections.emptySet(), library.availableCopies(book));
    }
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testBuy() {
    	Library library = makeLibrary();
    	Book book = new Book("Harry Potter", Arrays.asList("JK Rownling"), 2004);
    	BookCopy bookCopy = library.buy(book);
    	assertTrue(library.isAvailable(bookCopy));
    	
    	Set<BookCopy> allBookCopies = library.allCopies(book);
    	assertTrue(allBookCopies.contains(bookCopy));
    	
    	Set<BookCopy> allAvailableBookCopies = library.availableCopies(book);
    	assertTrue(allAvailableBookCopies.contains(bookCopy));
    	
    	List<Book> foundBooks = library.find("Harry Potter");
    	assertTrue(foundBooks.contains(book));
    }
    
    @Test
    public void testCheckout() {
    	Library library = makeLibrary();
    	Book book = new Book("Litte Women", Arrays.asList("Greta Gerwig"), 2018);
    	BookCopy bookCopy = library.buy(book);
    	library.checkout(bookCopy);
    	assertFalse(library.isAvailable(bookCopy));
    	
    	Set<BookCopy> allBookCopies = library.allCopies(book);
    	assertTrue(allBookCopies.contains(bookCopy));
    	
    	Set<BookCopy> allAvailableBookCopies = library.availableCopies(book);
    	assertFalse(allAvailableBookCopies.contains(bookCopy));
    	
    	List<Book> foundBooks = library.find("Greta Gerwig");
    	assertTrue(foundBooks.contains(book));
    }
    
    @Test
    public void testCheckin() {
    	Library library = makeLibrary();
    	Book book = new Book("Se7en", Arrays.asList("Michael Fox"), 2001);
    	BookCopy bookCopy = library.buy(book);
    	library.checkout(bookCopy);
    	
    	library.checkin(bookCopy);
    	assertTrue(library.isAvailable(bookCopy));
    	
    	Set<BookCopy> allBookCopies = library.allCopies(book);
    	assertTrue(allBookCopies.contains(bookCopy));
    	
    	Set<BookCopy> allAvailableCopies = library.availableCopies(book);
    	assertTrue(allAvailableCopies.contains(bookCopy));
    	
    	List<Book> foundBooks = library.find("se7en");
    	assertTrue(foundBooks.contains(book));
    }
    
    @Test
    public void testAllCopiesBookNotExist() {
    	Library library = makeLibrary();
    	Book book = new Book("Breaking Bad", Arrays.asList("Bryan Cranston"), 2008);
    	
    	Set<BookCopy> allBookCopies = library.allCopies(book);
    	assertEquals(0, allBookCopies.size());
    }
    
    @Test
    public void testAvailableCopiesBookNotExist() {
    	Library library = makeLibrary();
    	Book book = new Book("Breaking Bad", Arrays.asList("Bryan Cranston"), 2008);
    	
    	Set<BookCopy> allAvailableCopies = library.availableCopies(book);
    	assertEquals(0, allAvailableCopies.size());
    }
    
    @Test
    public void testFindNoMatch() {
    	Library library = makeLibrary();
    	Book book = new Book("Iranian Revolution", Arrays.asList("Dick Chainey", "Hillary Clinton"), 1999);
    	Book book2 = new Book("Artificial Intellegence", Arrays.asList("Andrew NG", "James Comey"), 2004);
    	library.buy(book);
    	library.buy(book2);
    	
    	List<Book> foundBooks = library.find("Captain Philps");
    	assertEquals(0, foundBooks.size());
    }
    
    @Test
    public void testFindEmptyString() {
    	Library library = makeLibrary();
    	Book book = new Book("Iranian Revolution", Arrays.asList("Dick Chainey", "Hillary Clinton"), 1999);
    	Book book2 = new Book("Artificial Intellegence", Arrays.asList("Andrew NG", "James Comey"), 2004);
    	library.buy(book);
    	library.buy(book2);
    	
    	List<Book> foundBooks = library.find("");
    	assertEquals(0, foundBooks.size());
    }
    
    @Test
    public void testFindOnlyDifferentYear() {
    	
    }

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
