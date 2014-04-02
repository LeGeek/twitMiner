package main.com.github.legeek.Formatter;

import twitter4j.Status;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by legeek on 02/04/14.
 */
public class CSVFormatter {
    List<Status> tweetList;

    public CSVFormatter(List<Status> tweetList) {
        this.tweetList = tweetList;
    }

    public void generateCVSFile(String pathFile) throws IOException {
        File file = new File(pathFile);

        if(!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter(file));
        String strCVS = new String();

        for(Status tweet : tweetList) {
            strCVS += dateFormatter(tweet.getCreatedAt()) + ";" +
                        "@" + tweet.getUser().getScreenName() + ";" +
                        prepareTweetText(tweet.getText()) + "\n";
        }

        bufferedWriter.write(strCVS);
        bufferedWriter.close();
    }

    private String dateFormatter(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        return format.format(date);
    }

    private String prepareTweetText(String tweetText) {
        return tweetText.replaceAll("[\"';,\\.\\n\\r]", "")
                        .trim()
                        .replace(' ', ';');
    }
}
