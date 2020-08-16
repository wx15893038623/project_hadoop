package com.atticus.mr.flowsum;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

	Text k = new Text();
	FlowBean v = new FlowBean();

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		// 7 13560436666 120.196.100.99 1116 954 200

		// 1 ��ȡһ��
		String line = value.toString();

		// 2 �и� \t
		String[] fields = line.split("\t");

		// 3 ��װ����
		k.set(fields[1]);// ��װ�ֻ���

		long upFlow = Long.parseLong(fields[fields.length - 3]);
		long downFlow = Long.parseLong(fields[fields.length - 2]);
		
		v.setUpFlow(upFlow);
		v.setDownFlow(downFlow);
//		v.set(upFlow,downFlow);

		// 4 д��
		context.write(k, v);
	}
}
