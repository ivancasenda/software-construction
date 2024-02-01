package library;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * BookCopy is a mutable type representing a particular copy of a book that is held in a library's
 * collection.
 */
public class BookCopy {

    // TODO: rep
    private final Book book;
    private BookCopy.Condition bookCondition;
	
	
    // TODO: rep invariant
    // book instance non null
    // bookCondition GOOD, DAMAGED
    
    // TODO: abstraction function
    // represent a copy of a book with its book condition
    
    // TODO: safety from rep exposure argument
    // all fields are private and immutable
    
    public static enum Condition {
        GOOD, DAMAGED
    };
    
    /**
     * Make a new BookCopy, initially in good condition.
     * @param book the Book of which this is a copy
     */
    public BookCopy(Book book) {
    	if(book == null)throw new IllegalArgumentException("Book cannot be null");
        this.book = book;
        this.bookCondition = BookCopy.Condition.GOOD;
        checkRep();
    }
    
    // assert the rep invariant
    private void checkRep() {
        assertNotEquals(null, book);
        assertNotEquals(null, bookCondition);
        assertTrue(bookCondition == BookCopy.Condition.GOOD || bookCondition == BookCopy.Condition.DAMAGED);
    }
    
    /**
     * @return the Book of which this is a copy
     */
    public Book getBook() {
    	checkRep();
        return book;
    }
    
    /**
     * @return the condition of this book copy
     */
    public Condition getCondition() {
    	checkRep();
        return bookCondition;
    }

    /**
     * Set the condition of a book copy.  This typically happens when a book copy is returned and a librarian inspects it.
     * @param condition the latest condition of the book copy
     */
    public void setCondition(Condition condition) {
        if(condition == null)throw new IllegalArgumentException("Condition cannot be null");
        bookCondition = condition;
        checkRep();
    }
    
    /**
     * @return human-readable representation of this book that includes book.toString()
     *    and the words "good" or "damaged" depending on its condition
     */
    public String toString() {
    	checkRep();
        return book.toString() + "," + bookCondition;
    }

    // uncomment the following methods if you need to implement equals and hashCode,
    // or delete them if you don't
    // @Override
    // public boolean equals(Object that) {
    //     throw new RuntimeException("not implemented yet");
    // }
    // 
    // @Override
    // public int hashCode() {
    //     throw new RuntimeException("not implemented yet");
    // }


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
