package bigdata;

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
            evaluator.buildDataSet();
        } catch (IOException e) {
            System.out.println("Unable to find lexicon file.");
            System.exit(1);
        }

        boolean success = runFirstJob(args[0], args[1]) && runSecondJob(args[1], args[2]);

        System.exit(success ? 0 : 1);
    }

    public static boolean runFirstJob(String dataFile, String outputPath) throws Exception {
        Job job = new Job();
        job.setJarByClass(Main.class);
        job.setJobName("Yelp Sentiment Analysis");

        FileInputFormat.setInputPaths(job, new Path(dataFile));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        job.setMapperClass(SentimentMapper.class);
        job.setReducerClass(RatingReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        return job.waitForCompletion(true);
    }

    public static boolean runSecondJob(String dataFile, String outputPath) throws Exception {
        Job job = new Job();
        job.setJarByClass(Main.class);
        job.setJobName("Yelp Sentiment Analysis");

        FileInputFormat.setInputPaths(job, new Path(dataFile + "/part-r-00000"));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        job.setMapperClass(VarianceMapper.class);
        job.setReducerClass(AccuracyRatingReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        return job.waitForCompletion(true);
    }
}
