package main.com.github.legeek.Main;

import main.com.github.legeek.Formatter.TransFormatter;
import main.com.github.legeek.Formatter.CSVFormatter;
import main.com.github.legeek.TwitMiner.TwitMiner;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by legeek on 02/04/14.
 */
public class Main {

    public static void main(String[] args) throws TwitterException, IOException {
        //twitMiner();
        transFormatter();
    }

    private static void transFormatter() throws IOException {
        final int SUPPORT = 2;
        TransFormatter transFormatter = new TransFormatter("tweets.csv");
        transFormatter.generateTransFile("tweets.trans");
    }

    private static void twitMiner() throws TwitterException, IOException {
        Twitter twitter = TwitterFactory.getSingleton();
        TwitMiner twitMiner = new TwitMiner(twitter, 10000);

        twitMiner.query("raspberry");

        twitMiner.executeQuery();
        List<Status> tweetList = twitMiner.getTweetList();

        for(Status tweet : tweetList) {
            System.out.println("@" + tweet.getUser().getScreenName() + " : " + tweet.getText());
        }

        CSVFormatter csvFormatter = new CSVFormatter(tweetList);
        csvFormatter.generateCVSFile("tweets.csv");


        System.out.println("\n\nThere are " + tweetList.size() + " Tweets");
    }
}

