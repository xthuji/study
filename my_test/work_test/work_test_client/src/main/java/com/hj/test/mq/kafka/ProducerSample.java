package com.hj.test.mq.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.javaapi.producer.ProducerData;
import kafka.producer.ProducerConfig;
 
public class ProducerSample {  
 
 
   public static void main(String[] args) throws InterruptedException {  
       ProducerSample ps = new ProducerSample();  
 
       Properties props = new Properties();  
       props.put("zk.connect", "192.168.6.13:2181");  
       props.put("serializer.class", "kafka.serializer.StringEncoder");
 
       ProducerConfig config = new ProducerConfig(props);  
       Producer<String, String> producer = new Producer<String, String>(config);  
       ProducerData<String, String> data = new ProducerData<String, String>("test-topic", "test-message2");  
       for (int i = 0; i < 20; i++) {
    	   producer.send(data);
    	   Thread.currentThread().sleep(500);
       }
       producer.close();  
   }  
} 