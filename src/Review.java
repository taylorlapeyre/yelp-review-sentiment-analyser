import clover.retrotranslator.edu.emory.mathcs.backport.java.util.Arrays;

import java.util.ArrayList;

/**
 * Created by taylorlapeyre on 4/20/15.
 */
public class Review {
    public String business_id;
    public String date;
    public String stars;
    public String text;
    public String type;
    public String user_id;

    public double calculateSentimentValue() {
        ArrayList<String> badWords = new ArrayList<String>();
        badWords.add("terrible");
        badWords.add("disgusting");
        badWords.add("awful");
        badWords.add("bad");
        badWords.add("gross");
        badWords.add("overcooked");
        badWords.add("undercooked");

        ArrayList<String> goodWords = new ArrayList<String>();
        goodWords.add("tasty");
        goodWords.add("good");
        goodWords.add("excellent");
        goodWords.add("perfect");
        goodWords.add("awesome");
        goodWords.add("happy");
        goodWords.add("delicious");
        goodWords.add("amazing");

        String[] words = text.split(" ");
        double sentiment = 2.5;
        for (String word : words) {
            if (goodWords.contains(word)) {
                sentiment += 0.2;
            }

            if (badWords.contains(word)) {
                sentiment -= 0.2;
            }
        }

        return sentiment;
    }
}
