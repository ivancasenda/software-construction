package twitter;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.Instant;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {
	
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
    	
    	Map<Long, Integer> foundTweets = new HashMap<>();
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
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(final List<Tweet> tweets) {
    	checkIllegalArgument(tweets);
    	
    	Collections.sort(tweets, new Comparator<Tweet>() {
			@Override
			public int compare(Tweet o1, Tweet o2) {
				if (o1.getTimestamp().getEpochSecond() < o2.getTimestamp().getEpochSecond()) {
					return -1;
				}else if(o1.getTimestamp().getEpochSecond() > o2.getTimestamp().getEpochSecond()) {
					return 1;
				}
				return 0;
			}
		});
    	
    	long minimumLength = Long.MAX_VALUE;
    	Instant start = null;
    	Instant end = null;
    	
    	if(tweets.size() == 1) {
    		start = tweets.get(0).getTimestamp();
    		end = tweets.get(0).getTimestamp();
    	}
    	
    	for (int i = 0; i < tweets.size() - 1; ++i) {
    		final long tweetEpochSecond1 = tweets.get(i).getTimestamp().getEpochSecond();
    		final long tweetEpochSecond2 = tweets.get(i + 1).getTimestamp().getEpochSecond();
    		final long absoluteEpochSecondDifference = Math.abs(tweetEpochSecond2 - tweetEpochSecond1);
    		
    		if (absoluteEpochSecondDifference <= minimumLength) {
    			minimumLength = absoluteEpochSecondDifference;
    			
    			if (tweetEpochSecond1 > tweetEpochSecond2) {
    				start = tweets.get(i + 1).getTimestamp();
    				end = tweets.get(i).getTimestamp();
    			}else {
    				start = tweets.get(i).getTimestamp();
    				end = tweets.get(i + 1).getTimestamp();
    			}
    		}
    	}
    	
    	return new Timespan(start, end);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	checkIllegalArgument(tweets);
    	Set<String> usernames = new HashSet<>();
    	
    	String usernameRegexPattern = "@[a-zA-Z0-9._-]+";
    	Pattern pattern = Pattern.compile(usernameRegexPattern);
    	for(int i = 0; i < tweets.size(); ++i) {
    		String tweetText = tweets.get(i).getText();
    		Matcher matcher = pattern.matcher(tweetText);
    		while(matcher.find()) {
    	         String username = matcher.group();
    	         int usernameStartIndex = matcher.start();
    	         int usernameEndIndex = matcher.end();
    	         
    	         if (!username.matches("(.*)\\.(.*)")) {
    	        	usernames.add(tweetText.substring(usernameStartIndex + 1, usernameEndIndex)); 
    	         }
    	      }
    		
    	}
    	return usernames;
    }

    /* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
