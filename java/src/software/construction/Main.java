package software.construction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Main {
	private final String test;
	
	public Main(String test) {
		this.test = test;
		
		System.out.println(this.test);
		
		List<String> listOfStrings = new ArrayList<>();
		listOfStrings.add("hello");
		List<String> unmodifiableListOfStrings = Collections.unmodifiableList(listOfStrings);
		listOfStrings.add("world");
		for (String string : unmodifiableListOfStrings) {
			System.out.println(string);
		}
		unmodifiableListOfStrings.add("insert");
		
	}
	
	public static void main(String[] args) {
		new Main("test");
		int n = 1;
	    while (n != 1) {
	        if (n % 2 == 0) {
	            n = n / 2;
	        } else {
	            n = 3 * n + 1;
	        }
	    }
	    final int startNumber = 1;
	    //hailStone(startNumber);
	    try {
	    	hailStone(startNumber);
	    }catch(Exception exception) {
	    	exception.printStackTrace();
	    }
	    System.out.println("Hello world!");
	}
	
	/**
	 * Compute a hailstone sequence
	 * @param n is the starting number, assume n > 0
	 * @return list of integer hailstone sequence starting at n ending at 1
	 **/
	public static List<Integer> hailStone(final int n) throws Exception{
		List<Integer> sequence = new ArrayList<>();	
		if(n < 1) {
			//throw new RuntimeException("starting number n is less than 1");
			throw new SequenceNotFoundException("starting number n is less than 1");
		}
		final int EMPTY = 0;
		if(sequence.size() == EMPTY) {
			//throw new EmptyListException("sequence is empty!");
			throw new IOException();
		}
		return sequence;
	}

}
