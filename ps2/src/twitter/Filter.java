package twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {
	
	/**
	 * Helper method to check illegal argument, null or list of tweets with non distinct ids
	 * 
	 * @param tweets
	 * 		list of tweets, not modified by this method.
	 * 
	 * @throws IllegalArgumentException
	 */
	private static void checkIllegalArgument(final List<Tweet> tweets) {
		if (tweets == null) throw new IllegalArgumentException();
    	
    	final Map<Long, Integer> foundTweets = new HashMap<>();
    	for (int i = 0; i < tweets.size(); ++i) {
    		Tweet tweet = tweets.get(i);
    		
    		if ( foundTweets.containsKey( tweet.getId() ) ) {
    			throw new IllegalArgumentException();
    		}else {
    			foundTweets.put( tweet.getId(), 1);
    		}
    	}
	}

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        checkIllegalArgument(tweets);
        final String validUsernamePattern = "^[a-zA-Z0-9_-]+$";
        
        final boolean isValidUsername = username.matches(validUsernamePattern);
        if (!isValidUsername) throw new IllegalArgumentException("Invalid Username");
        
        List<Tweet> tweetsWithAuthorUsername = new ArrayList<>();
        
        for (int i = 0; i < tweets.size(); ++i) {
        	Tweet tweet = tweets.get(i);
        	if (!tweet.getAuthor().equals(username)) continue;
        	tweetsWithAuthorUsername.add(tweet);
        }
        
        return tweetsWithAuthorUsername;
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
    	checkIllegalArgument(tweets);
    	
    	final long timespanStartInEpochSecond = timespan.getStart().getEpochSecond();
    	final long timespanEndInEpochSecond = timespan.getEnd().getEpochSecond();
    	
    	final List<Tweet> tweetsWithinTimespan = new ArrayList<>();
        
        for (int i = 0; i < tweets.size(); ++i) {
        	final Tweet tweet = tweets.get(i);
        	final long timeOfTweetInEpochSecond = tweet.getTimestamp().getEpochSecond();
        	
        	if (timeOfTweetInEpochSecond < timespanStartInEpochSecond || timeOfTweetInEpochSecond > timespanEndInEpochSecond) continue;
   
        	tweetsWithinTimespan.add(tweets.get(i));
        }
        
        return tweetsWithinTimespan;
    }

    /**
     * Find tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. 
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when 
     *         represented as a sequence of nonempty words bounded by space characters 
     *         and the ends of the string) includes *at least one* of the words 
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        final Map<String, Integer> hashedWords = new HashMap<>();
        for (String word : words) {
        	word = word.toLowerCase();
        	hashedWords.put(word, 1);
        }
        
        final List<Tweet> tweetsThatContainWords = new ArrayList<>();
        
        for (int i = 0; i < tweets.size(); ++i) {
        	final Tweet tweet = tweets.get(i);
        	final String whiteSpaceRegex = "[\\s]+";
        	final String[] tweetText = tweet.getText().split(whiteSpaceRegex);
        	
        	boolean foundWordInTweet = false;
        	
        	for (String wordInTweet : tweetText) {
        		wordInTweet = wordInTweet.toLowerCase();
        		if (hashedWords.containsKey(wordInTweet)) {
        			foundWordInTweet = true;
        			break;
        		}
        	}
        	
        	if(foundWordInTweet) tweetsThatContainWords.add(tweet);
        }
        return tweetsThatContainWords;
    }

    /* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
