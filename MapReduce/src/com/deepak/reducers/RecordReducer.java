package com.deepak.reducers;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class RecordReducer extends MapReduceBase implements Reducer<Text, LongWritable, Text, LongWritable> {

    public void reduce(Text _key, Iterator<LongWritable> values, OutputCollector<Text, LongWritable> output, Reporter reporter)
            throws IOException {
        Text key = (Text) _key;
        long count = 0;
        while (values.hasNext()) {
            count += values.next().get();
        }
        output.collect(key, new LongWritable(count));
    }

}
