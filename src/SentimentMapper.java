import java.io.IOException;
import clover.com.google.gson.Gson;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SentimentMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Gson gson = new Gson();
        Review review;

        try {
            review = gson.fromJson(value.toString(), Review.class);
        } catch (Exception e) {
            // Not a review
            return;
        }


        DoubleWritable sentimentValue = new DoubleWritable(review.calculateSentimentValue());
        Text businessId = new Text(review.business_id);

        context.write(businessId, sentimentValue);
    }

}
