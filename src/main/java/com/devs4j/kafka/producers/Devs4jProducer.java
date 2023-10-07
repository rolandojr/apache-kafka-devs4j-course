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
        long startTime = System.currentTimeMillis();
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("linger.ms", "10");

        try (Producer<String, String> producer = new KafkaProducer<String, String>(props);) {
            for (int i = 0; i < 100; i++) {
                producer.send(new ProducerRecord<>("devs4j-topic", i % 2 == 0 ? "devs4j-key2" : "devs4j-key3", String.valueOf(i)));
            }
            producer.flush();
        } /*catch (ExecutionException | InterruptedException e) {
        }*/
//        2832 ms to send 100,000 messages -- linger 0
//        1786 ms to send 100,000 messages -- linger 6
//        1732 ms to send 100,000 messages -- linger 4
//        2138 ms to send 100,000 messages -- linger 10
//        1764 ms to send 100,000 messages -- linger 15
//        2199 ms to send 100,000 messages -- linger 20
        log.info("Processing time = {} ", (System.currentTimeMillis() - startTime));
    }
}
