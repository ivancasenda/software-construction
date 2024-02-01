package library;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

/**
 * Book is an immutable type representing an edition of a book -- not the physical object, 
 * but the combination of words and pictures that make up a book.  Each book is uniquely
 * identified by its title, author list, and publication year.  Alphabetic case and author 
 * order are significant, so a book written by "Fred" is different than a book written by "FRED".
 */
public class Book {

    // TODO: rep
	private final String title;
	private final List<String> authorList;
	private final int publicationYear;
    
    // TODO: rep invariant
	// title must contain at least one non-space character.
	// authors must have at least one name, and each name must contain at least one non-space character.
	// publicationYear is <= 4 digit non negative
	
    // TODO: abstraction function
	// representing an edition of a book with title, 
	// a list of author where alphabetic case and author order are significant,
	// and the book publication year.
	
    // TODO: safety from rep exposure argument
    // all fields are private
	// instance variable title, and publicationYear are immutable
	// the mutable list of string is given a defensive copy on the constructor, and the observer method getAuthors.
	
    /**
     * Make a Book.
     * @param title Title of the book. Must contain at least one non-space character.
     * @param authors Names of the authors of the book.  Must have at least one name, and each name must contain 
     * at least one non-space character.
     * @param year Year when this edition was published in the conventional (Common Era) calendar.  Must be nonnegative. 
     */
    public Book(String title, List<String> authors, int year) {
    	if(title.strip().length() == 0)throw new IllegalArgumentException("Title Must contain at least one non-space character");
    	if(authors.size() == 0) {
    		throw new IllegalArgumentException("Authors must have at least one name");
    	}else {
    		for (String author : authors) {
    			if(author.strip().length() == 0) {
    				throw new IllegalArgumentException("Each author name must contain at least one non-space character");
    			}
    		}
    	}
    	if(year >= 10000 || year < 0)throw new IllegalArgumentException("Year must use the common era calendar");
    	
        this.title = title;
        authorList = List.copyOf(authors);
        publicationYear = year;
    }
    
    // assert the rep invariant
    private void checkRep() {
        assertNotEquals(0, title.strip().length());
        assertNotEquals(0, authorList.size());
        for (String author : authorList) {
        	assertNotEquals(0, author.strip().length());
		}
        assertTrue(publicationYear < 10000 && publicationYear >= 0);
    }
    
    /**
     * @return the title of this book
     */
    public String getTitle() {
    	checkRep();
        return title;
    }
    
    /**
     * @return the authors of this book
     */
    public List<String> getAuthors() {
    	checkRep();
        return List.copyOf(authorList);
    }

    /**
     * @return the year that this book was published
     */
    public int getYear() {
    	checkRep();
        return publicationYear;
    }

    /**
     * @return human-readable representation of this book that includes its title,
     *    authors, and publication year
     */
    public String toString() {
    	checkRep();
        return title + "," + authorList.toString() + "," + publicationYear;
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
