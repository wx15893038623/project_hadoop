package com.atticus.mr.flowsum;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean>{
	
	FlowBean v = new FlowBean();
	
	@Override
	protected void reduce(Text key, Iterable<FlowBean> values, Context context)
			throws IOException, InterruptedException {
//		13568436656	2481	24681    30000
//		13568436656	1116	954	 20000
		
		long sum_upFlow = 0;
		long sum_downFlow = 0;
		
		// 1 �ۼ����
		for (FlowBean flowBean : values) {
			
			sum_upFlow += flowBean.getUpFlow();
			sum_downFlow += flowBean.getDownFlow();
		}
		
		v.set(sum_upFlow, sum_downFlow);
		
		// 2 д��
		context.write(key, v);
	}
}
