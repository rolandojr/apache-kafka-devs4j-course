package com.devs4j.kafka.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Devs4jProducer {

    public static final Logger log = LoggerFactory.getLogger(Devs4jProducer.class);

    public static void main(String[] args) {
        // 490816
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("linger.ms", "10");

        try (Producer<String, String> producer = new KafkaProducer<String, String>(props);) {
            for (int i = 0; i < 100; i++) {
                producer.send(new ProducerRecord<>("devs4j-topic", "devs4j-key" , String.valueOf(i)));
            }
            producer.flush();
        } /*catch (ExecutionException | InterruptedException e) {

        }*/


    }
}
