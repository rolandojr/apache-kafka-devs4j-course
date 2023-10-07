package com.devs4j.kafka.tecnical.challenge.producer;

import com.devs4j.kafka.tecnical.challenge.model.Transaction;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class TecnicalChallengeProducer {

    public static final Logger log = LoggerFactory.getLogger(TecnicalChallengeProducer.class);

    public static void producer(List<Transaction> transactionList) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("linger.ms", "10");

        try (Producer<String, String> producer = new KafkaProducer<String, String>(props);) {
            for (Transaction transaction : transactionList) {
                producer.send(new ProducerRecord<>("devs4j-topic", transaction.getUser().getId().toString(), transaction.toString()));
            }
            producer.flush();
        }
    }
}
