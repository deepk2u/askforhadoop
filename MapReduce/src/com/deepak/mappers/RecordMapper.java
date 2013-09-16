package com.deepak.mappers;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class RecordMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, LongWritable> {

    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, LongWritable> output, Reporter reporter) throws IOException {

        LongWritable one = new LongWritable(1);
        String find = "got it.......................... yeah";
        if (value != null && value.toString().contains(find)) {
            output.collect(new Text(find), one);
        }
    }
}
