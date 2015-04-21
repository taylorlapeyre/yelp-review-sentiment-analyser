package bigdata;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class SentimentEvaluator {
    private static SentimentEvaluator ourInstance = new SentimentEvaluator();

    public static SentimentEvaluator getInstance() {
        return ourInstance;
    }

    HashMap<String, Double> lexicon = new HashMap<String, Double>();

    private SentimentEvaluator() { }

    public void buildDataSet() throws IOException {
        FileReader fr = new FileReader("bigdata/lexicon.ttf");
        BufferedReader reader = new BufferedReader(fr);
        String line;

        while ((line = reader.readLine()) != null) {
            String type = "";
            String word = "";
            String polarity = "";

            String[] properties = line.split(" ");
            for (String property : properties) {

                if (!property.contains("=")) {
                    continue;
                }

                String[] keyVal = property.split("=");
                String key = keyVal[0];
                String val = keyVal[1];

                if (key.equals("type")) {
                    type = val;
                } else if (key.equals("word1")) {
                    word = val;
                } else if (key.equals("priorpolarity")) {
                    polarity = val;
                }
            }

            double sentimentValue;
            if (type.equals("strongsubj")) {
                sentimentValue = 0.3;
            } else {
                sentimentValue = 0.1;
            }

            if (polarity.equals("negative")) {
                sentimentValue = -sentimentValue;
            }


            lexicon.put(word, sentimentValue);
        }
    }
}
