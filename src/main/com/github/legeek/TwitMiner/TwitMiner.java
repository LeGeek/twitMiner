package main.com.github.legeek.TwitMiner;

import twitter4j.*;

import java.util.List;

/**
 * Created by legeek on 02/04/14.
 */
public class TwitMiner {
    private int maxResult;
    private Twitter twitter;
    private QueryResult queryResult;
    private List<Status> tweetList;

    public TwitMiner(Twitter twitter, int maxResult) {
        this.twitter = twitter;
        this.maxResult = maxResult;
    }

    public void query(String search) throws TwitterException {
        queryResult = twitter.search(new Query(search));
    }

    public void executeQuery() {
        if(queryResult == null) {
            return;
        }

        tweetList = queryResult.getTweets();

        while(queryResult.nextQuery() != null && tweetList.size() < maxResult) {
            tweetList.addAll(queryResult.getTweets());
        }
    }

    public List<Status> getTweetList() {
        return tweetList;
    }


    public List<Status> getTruncateTweetList() {
        return tweetList.subList(0, maxResult );
    }
}
