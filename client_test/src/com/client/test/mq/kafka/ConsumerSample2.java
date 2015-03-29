package com.client.test.mq.kafka;


import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;
  
public class ConsumerSample2 {  
  
    public static void main(String[] args) {  
        // specify some consumer properties  
        Properties props = new Properties();  
        props.put("zk.connect", "192.168.6.13:2181");  
        props.put("zk.connectiontimeout.ms", "6000");  
        props.put("groupid", "test_group");  
  
        // Create the connection to the cluster  
        ConsumerConfig consumerConfig = new ConsumerConfig(props);  
        ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);  
  
        HashMap<String, Integer> map = new HashMap<String, Integer>();  
        map.put("test-topic", 1);  
        Map<String, List<KafkaStream<Message>>> topicMessageStreams =  
                consumerConnector.createMessageStreams(map);  
        List<KafkaStream<Message>> streams = topicMessageStreams.get("test-topic");  
  
        for (final KafkaStream<Message> stream : streams) {  
            for (MessageAndMetadata msgAndMetadata : stream) {  
                // process message (msgAndMetadata.message())  
                System.out.println("topic: " + msgAndMetadata.topic());  
                Message message = (Message) msgAndMetadata.message();  
                ByteBuffer buffer = message.payload();  
                byte[] bytes = new byte[message.payloadSize()];  
                buffer.get(bytes);  
                String tmp = new String(bytes);  
                System.out.println("message content: " + tmp);  
            }  
        }
    }  
}  