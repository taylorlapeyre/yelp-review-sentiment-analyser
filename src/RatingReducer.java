import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RatingReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    @Override
    public void reduce(Text key, Iterable<DoubleWritable> values, Context context)
            throws IOException, InterruptedException {
        int total = 0;
        int length = 0;

        for (DoubleWritable sentiment : values) {
            total += sentiment.get();
            length++;
        }

        double average = total / length;

        context.write(key, new DoubleWritable(average));
    }
}
