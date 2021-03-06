### Yelp Review Sentiment Analyser

This is a Hadoop mapreduce program for [Yelp's academic dataset](https://www.yelp.com/academic_dataset) which will run sentiment analysis on reviews.

**The goal of this project is to accurately predict the rating of a business** based on the language that reviewers use. To give us something to go off of, we used [Pitt University's Subjectivity Lexicon](http://mpqa.cs.pitt.edu/lexicons/subj_lexicon/) as a database.

### Results

The accuracy rating is the measure of how close to the truth our predictions were. At zero, we are perfectly accurate. A **positive** accuracy rating means that we think that reviewers generally rate more negatively than they think. Conversely, a **negative** value indicates that users tend to inflate their rating.

This algorithm outputs an accuracy rating of **1.0857501712391706** when run over a dataset of 474,434 reviews.


### Running It

First, you will need access to Yelp's academic dataset. Once you have it, put it into hdfs:

```bash
$ hadoop fs -mkdir -p /user/you
$ hadoop fs -put sample-data.json /user/you
```

Then, compile the source code and compress it into a jar:

```bash
$ javac src/bigdata/*.java -classpath `hadoop classpath`
$ jar cvf analyser.jar src/bigdata/*.class src/bigdata/lexicon.ttf
```

Finally, run the mapreduce job with hadoop:

- The first argument is the location of the dataset in hdfs
- The second is the name of the output directory in hdfs for the rating predictions
- The third is the hdfs output directory for the final result: the accuracy rating of the algorithm.

```bash
hadoop jar analyser.jar bigdata.Main /user/you/sample-data.json rating-predictions final-result
```

Once completed, you can view the results:

```
hadoop fs -cat rating-predictions/*
hadoop fs -cat final/*
```
