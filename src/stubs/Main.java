package stubs;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        SentimentEvaluator evaluator = SentimentEvaluator.getInstance();

        try {
            evaluator.readWords();
        } catch (IOException e) {
            System.out.println("File, where you at?");
            System.exit(1);
        }


	    Job job = new Job();
        job.setJarByClass(Main.class);
        job.setJobName("Yelp Sentiment Analysis");

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(SentimentMapper.class);
        job.setReducerClass(RatingReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        boolean success = job.waitForCompletion(true);
        System.exit(success ? 0 : 1);
    }
}
