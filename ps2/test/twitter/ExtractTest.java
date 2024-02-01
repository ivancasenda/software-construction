package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * Make sure you have partitions.
     * 
     * getTimespan
     * Test Partition:
     * - an empty list;
     * - a list containing same timespan
     * - a large timespan between 2 tweets
     * - a list containing one tweet
     * 
     * getMentionedUser
     * Test Partition:
     * - no mentioned username
     * - one mentioned username
     * - multiple mentioned username
     * - email address in tweet text
     * - username in the start of the string
     * - username in the begining of the string
     */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
    	
    	final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
        final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
        
        final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
        final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    	
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    
    @Test
    public void testGetTimespanSameTweetTimespan() {
    	
    	final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    	final Instant d2 = Instant.parse("2016-02-17T10:00:00Z");
    	final Instant d3 = Instant.parse("2016-02-17T10:00:00Z");
    	    
    	final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    	final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    	final Tweet tweet3 = new Tweet(3, "lorem", "lorem ipsum adfus nel delthor", d3);
    	
    	final List<Tweet> tweetList = new ArrayList<>();
    	tweetList.add(tweet1);
    	tweetList.add(tweet2);
    	tweetList.add(tweet3);
    	
        Timespan timespan = Extract.getTimespan(tweetList);
        
        assertEquals("expected start", d1.getEpochSecond(), timespan.getStart().getEpochSecond());
        assertEquals("expected end", d1.getEpochSecond(), timespan.getEnd().getEpochSecond());
    }
    
    @Test
    public void testGetTimespanLargeTweetTimespan() {
    	
    	final Instant d1 = Instant.parse("2000-02-17T10:00:00Z");
    	final Instant d2 = Instant.parse("2010-02-17T10:00:00Z");
    	final Instant d3 = Instant.parse("2031-02-17T10:00:00Z");
    	    
    	final Tweet tweet1 = new Tweet(1, "alyssa", "is #test it reasonable to talk about rivest so much?", d1);
    	final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    	final Tweet tweet3 = new Tweet(3, "lorem", "lorem ipsum adfus nel delthor", d3);
    	
    	final List<Tweet> tweetList = new ArrayList<>();
    	tweetList.add(tweet1);
    	tweetList.add(tweet2);
    	tweetList.add(tweet3);
    	
        Timespan timespan = Extract.getTimespan(tweetList);
        
        assertEquals("expected start", d1.getEpochSecond(), timespan.getStart().getEpochSecond());
        assertEquals("expected end", d2.getEpochSecond(), timespan.getEnd().getEpochSecond());
    }
    
    @Test
    public void testGetTimespanOneTweet() {
    	
    	final Instant d1 = Instant.parse("1999-02-17T10:00:00Z");
    	    
    	final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    	
    	final List<Tweet> tweetList = new ArrayList<>();
    	tweetList.add(tweet1);
    	
        Timespan timespan = Extract.getTimespan(tweetList);
        
        assertEquals("expected start", d1.getEpochSecond(), timespan.getStart().getEpochSecond());
        assertEquals("expected end", d1.getEpochSecond(), timespan.getEnd().getEpochSecond());
    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
    	final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
        
        final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
        
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    @Test
    public void testGetMentionedUsersOneMention() {
    	
    	final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    	final Instant d2 = Instant.parse("2016-02-17T10:00:00Z");
    	final Instant d3 = Instant.parse("2016-02-17T10:00:00Z");
    	    
    	final Tweet tweet1 = new Tweet(1, "alyssa", "@ryan reasonable to talk about rivest so much?", d1);
    	final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    	final Tweet tweet3 = new Tweet(3, "lorem", "lorem ipsum adfus nel delthor", d3);
    	
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet3));
        
        assertTrue("expected one username ryan", mentionedUsers.contains("ryan"));
        assertEquals("expected one username ryan", mentionedUsers.size(), 1);
    }
    
    @Test
    public void testGetMentionedUsersMultipleMention() {
    	
    	final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    	final Instant d2 = Instant.parse("2016-02-17T10:00:00Z");
    	final Instant d3 = Instant.parse("2016-02-17T10:00:00Z");
    	    
    	final Tweet tweet1 = new Tweet(1, "alyssa", "@ryan test @ivan,@dani reasonable to talk about rivest so much?", d1);
    	final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest @realDonaldTrump @AdamShift talk in 30 minutes #hype", d2);
    	final Tweet tweet3 = new Tweet(3, "lorem", "lorem ipsum adfus nel delthor @NancyPelosi", d3);
    	
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet3));
        
        Set<String> allUsernameMentioned = new HashSet<>();
        allUsernameMentioned.add("ryan");
        allUsernameMentioned.add("ivan");
        allUsernameMentioned.add("dani");
        allUsernameMentioned.add("realDonaldTrump");
        allUsernameMentioned.add("AdamShift");
        allUsernameMentioned.add("NancyPelosi");
        
        for (String username : mentionedUsers) {
        	assertTrue("contains username " + username, allUsernameMentioned.contains(username));
        }
        assertEquals("expected total username found 6", mentionedUsers.size(), 6);
    }
    
    @Test
    public void testGetMentionedUsersContainsEmail() {
    	
    	final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    	
    	final Tweet tweet1 = new Tweet(1, "alyssa", "test nicholas1ivan@gmail.com reasonable to talk about rivest so much?", d1);
    	
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
