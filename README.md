### Yelp Review Sentiment Analyser

This is a Hadoop mapreduce program for [Yelp's academic dataset](https://www.yelp.com/academic_dataset) which will run sentiment analysis on reviews.

**The goal of this project is to accurately predict the rating of a business** based on the language that reviewers use. To give us something to go off of, we used [Pitt University's Subjectivity Lexicon](http://mpqa.cs.pitt.edu/lexicons/subj_lexicon/) as a database.

### Running It

First, you will need access to Yelp's academic dataset. Once you have it, put it into hdfs:

```bash
$ hadoop fs -mkdir -p /user/you
$ hadoop fs -put dataset.json /user/you
```

Then, compile the source code and compress it into a jar:

```bash
$ javac src/bigdata/*.java
$ jar cvf analyser.jar src/*.class src/lexicon.ttf
```

Finally, run the mapreduce job with hadoop. The first argument is the location of the dataset in hdfs and the second is the name of an output directory in hdfs.

```bash
hadoop jar sentiment.jar bigdata.Main /user/you/dataset.json rating-predictions
```

Once completed, you can view the results:

```
hadoop fs -cat rating-predictions/*
```
