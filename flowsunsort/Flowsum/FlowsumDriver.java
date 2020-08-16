package com.atticus.mr.flowsum;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FlowsumDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
//		args = new String[]{"g:/input/inputflow","g:/output1"};
		
		Configuration conf = new Configuration();
		// 1 ��ȡjob����
		Job job = Job.getInstance(conf );
		
		// 2 ����jar��·��
		job.setJarByClass(FlowsumDriver.class);
		
		// 3 ����mapper��reducer
		job.setMapperClass(FlowCountMapper.class);
		job.setReducerClass(FlowCountReducer.class);
		
		// 4 ����mapper�����key��value����
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FlowBean.class);
		
		// 5 �������������key��value����
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);
		
//		job.setPartitionerClass(ProvincePartitioner.class);
//		
//		job.setNumReduceTasks(6);
//		
		
		// 6 �����������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 7 �ύjob
		boolean result = job.waitForCompletion(true);
		
		System.exit(result?0 :1);
	}
}
