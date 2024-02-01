package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * Make sure you have partitions.
     * 
     * guessFllowsGraphEmpty Test Partition
     * - Graph Empty
     * - One User no follow(mention)
     * - One user multiple follow(mention), mention itself
     * - Multiple user multiple following, same user mention twice in tweet with capital letters
     * 
     * influencers Test Partition
     * - One user one follower
     * - Multiple user multiple followers
     */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test
    public void testGuessFollowsOneUserNoFollowing() {
    	
    	final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
        
    	final String usernameAlyssa = "alyssa";
        final Tweet tweet1 = new Tweet(1, usernameAlyssa, "is it reasonable to talk about rivest so much?", d1);
    	
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1));
        
        assertTrue("expected one user " + usernameAlyssa, followsGraph.containsKey(usernameAlyssa));
        assertTrue("expected no following from " + usernameAlyssa, followsGraph.get(usernameAlyssa).isEmpty());
    }
    
    @Test
    public void testGuessFollowsOneUserMultipleFollow() {
    	
    	final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
        
    	final String usernameAlyssa = "alyssa";
        final Tweet tweet1 = new Tweet(1, usernameAlyssa, "@leonardoDecaprio @gretaThunberg @alyssa is it reasonable to talk about the green movement so much?", d1);
    	
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1));
        
        assertEquals("expected one user", 1, followsGraph.size());
        assertTrue("expected user " + usernameAlyssa, followsGraph.containsKey(usernameAlyssa));
        
        assertEquals("expected 2 follow from " + usernameAlyssa, 2, followsGraph.get(usernameAlyssa).size());
        assertFalse("expected not to follow herself", followsGraph.get(usernameAlyssa).contains(usernameAlyssa));
        assertTrue("expected " + usernameAlyssa + " to follow leonardoDecaprio and gretaThunberg", followsGraph.get(usernameAlyssa).contains("leonardodecaprio") && followsGraph.get(usernameAlyssa).contains("gretathunberg"));
    }
    
    @Test
    public void testGuessFollowsMutlipleUserMultipleFollow() {
    	
    	final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    	final Instant d2 = Instant.parse("2016-02-17T10:00:00Z");
    	final Instant d3 = Instant.parse("2016-02-17T10:00:00Z");
        
    	final String usernameAlyssa = "alyssa";
        final Tweet tweet1 = new Tweet(1, usernameAlyssa, "@leonardoDecaprio @gretaThunberg @LEONARDODECAPRIO is it reasonable to talk about the green movement so much?", d1);
        final Tweet tweet2 = new Tweet(2, usernameAlyssa, "@leonardoDecaprio @gretaThunberg @GRETATHUNBERG @ryan is it reasonable to talk about the green movement so much?", d2);
        final Tweet tweet3 = new Tweet(2, "ryan", "@leonardoDecaprio @GRETATHUNBERG @ryan @ivan is it reasonable to talk about the green movement so much?", d3);
    	
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3));
        
        assertEquals("expected two user", 2, followsGraph.size());
        assertTrue("expected user ryan and " + usernameAlyssa, followsGraph.containsKey(usernameAlyssa) && followsGraph.containsKey("ryan"));
        
        assertEquals("expected 2 follow from " + usernameAlyssa, 3, followsGraph.get(usernameAlyssa).size());
        assertEquals("expected 2 follow from ryan", 3, followsGraph.get("ryan").size());
        assertTrue("expected ryan to follow leonardodecaprio, gretathunberg, ivan", 
        		followsGraph.get("ryan").contains("leonardodecaprio") && followsGraph.get("ryan").contains("gretathunberg") && followsGraph.get("ryan").contains("ivan"));
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }
    
    @Test
    public void testInfluencersOneUserOneFollowers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        
        Set<String> ryanFollowing = new HashSet<>();
        ryanFollowing.add("ivan");
        followsGraph.put("ryan", ryanFollowing);
        followsGraph.put("ivan", new HashSet<String>());
        
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected one user with follower", influencers.contains("ivan"));
    }
    
    @Test
    public void testInfluencersMultipleUserMultipleFollowers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        
        Set<String> ryanFollowing = new HashSet<>();
        ryanFollowing.add("ivan");
        ryanFollowing.add("jake");
        followsGraph.put("ryan", ryanFollowing);
        
        Set<String> gretaFollowing = new HashSet<>();
        gretaFollowing.add("ivan");
        gretaFollowing.add("jake");
        followsGraph.put("greta", gretaFollowing);
        
        Set<String> ivanFollowing = new HashSet<>();
        ivanFollowing.add("greta");
        ivanFollowing.add("ryan");
        ivanFollowing.add("jake");
        followsGraph.put("ivan", ivanFollowing);
        
        followsGraph.put("jake", new HashSet<String>());
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertEquals("expected jake user with the most followers", "jake", influencers.get(0));
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
