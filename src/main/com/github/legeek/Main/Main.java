package main.com.github.legeek.Main;

import main.com.github.legeek.TwitMiner.TwitMiner;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.List;

/**
 * Created by legeek on 02/04/14.
 */
public class Main {

    public static void main(String[] args) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        TwitMiner twitMiner = new TwitMiner(twitter, 1000);

        twitMiner.query("laule");
        twitMiner.executeQuery();
        List<Status> tweetList = twitMiner.getTweetList();

        for(Status tweet : tweetList) {
            System.out.println("@" + tweet.getUser().getScreenName() + " : " + tweet.getText());
        }

        System.out.println("\n\nThere are " + tweetList.size() + " results");
    }
}

