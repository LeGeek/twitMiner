package main.com.github.legeek.Formatter;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by legeek on 02/04/14.
 */
public class TransFormatter {
    private String csvFilePath;

    public TransFormatter(String csvPathFile) {
        this.csvFilePath = csvPathFile;
    }

    public void generateTransFile(String transFilePath) throws IOException {
        File file = new File(csvFilePath);
        File transFile = new File(transFilePath);
        BufferedReader bufferedReader = new BufferedReader( new FileReader(file) );
        BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter(transFile) );
        String strTrans = new String();
        String line, token;
        StringTokenizer stringTokenizer;

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        while((line = bufferedReader.readLine()) != null) {
            stringTokenizer = new StringTokenizer(line, ";");

            while(stringTokenizer.hasMoreTokens()){
                token = stringTokenizer.nextToken().toUpperCase();
                if(!hashMap.containsKey(token)) {
                    hashMap.put(token, hashMap.size() + 1);
                }
                strTrans += hashMap.get(token) + " ";
            }
            strTrans += "\n";
        }

        if(!transFile.exists()) {
            transFile.createNewFile();
        }

        bufferedWriter.write(strTrans);

        bufferedReader.close();
        bufferedWriter.close();
    }
}
