package bigdata;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AccuracyRatingReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    @Override
    public void reduce(Text key, Iterable<DoubleWritable> values, Context context)
            throws IOException, InterruptedException {
        double total = 0;
        double length = 0;

        for (DoubleWritable variance : values) {
            total += variance.get();
            length++;
        }

        double average = total / length;

        context.write(new Text("Accuracy Rating: "), new DoubleWritable(average));
    }
}
