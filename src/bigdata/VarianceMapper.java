package bigdata;

import java.io.IOException;
import com.google.gson.Gson;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class VarianceMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(" ");
        String[] moreWords = words[2].split("\t");

        String predictedRatingString = moreWords[1];
        String actualRatingString = moreWords[0].substring(0, 1);

        int actualRating = Integer.parseInt(actualRatingString);
        double predictedRating = Double.parseDouble(predictedRatingString);

        double variance = predictedRating - actualRating;

        context.write(new Text("hooplah"), new DoubleWritable(variance));
    }

}
