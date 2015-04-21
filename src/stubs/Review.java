package stubs;

import clover.retrotranslator.edu.emory.mathcs.backport.java.util.Arrays;

import java.util.ArrayList;
import java.util.HashMap;

public class Review {
    public String business_id;
    public String date;
    public String stars;
    public String text;
    public String type;
    public String user_id;

    public Review() {
        // noop
    }

    public double calculateSentimentValue() {

        SentimentEvaluator evaluator = SentimentEvaluator.getInstance();

        double sentiment = 2.5;

        for (String word : this.text.split("\\W+")) {
            if (evaluator.lexicon.containsKey(word)) {
                sentiment += evaluator.lexicon.get(word);
            }
        }

        return sentiment;
    }
}
