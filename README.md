# project_hadoop


## flowsumsort
概述：  
Map Reduce练习；完全分布式环境搭建练习  
统计手机用户流量数据，降序排列，并对其所在省份进行分区

### 环境：
JDK版本: 1.8.0_144 64位  
hadoop版本： 2.7.2

### 运行模式：完全分布式
3台linux（CentOS 6 64位，4G）虚拟机名称：atticus.cloud; hadoop101; hadoop102  
NameNode: atticus.cloud  
2nn: hadoop102  
RecourceManager: hadoop101  
slaves: atticus.cloud; hadoop101; hadoop102

### 数据格式：
id	手机号码 网络ip 上行流量 下行流量 网络状态码  
7 	13560436666	120.196.100.99		1116		 954			200

### 需求：
基本需求：  
统计每一个手机号耗费的总上行流量、下行流量、总流量  
附加需求：  
降序排列；通过手机号前三位数字对其所在省份进行分区

### fs.jar----------------------------------代码实现
两次MapReduce：  
第一次：Flowsum  
实现上行流量、下行流量、总流量的统计  
第二次：FlowCountSort  
实现排序与所在省份进行分区

### Hadoop环境运行命令
hadoop jar fs.jar com.atticus.mr.flowsum.FlowsumDriver /inputflow/ /output1  
hadoop jar fs.jar com.atticus.mr.flowsort.FlowCountSortDriver /output1 /output2

