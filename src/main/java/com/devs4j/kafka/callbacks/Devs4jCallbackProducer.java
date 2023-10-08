package com.devs4j.kafka.callbacks;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Devs4jCallbackProducer {

    public static final Logger log = LoggerFactory.getLogger(Devs4jCallbackProducer.class);

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("acks", "1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("linger.ms", "10");

        try (Producer<String, String> producer = new KafkaProducer<String, String>(props);) {
            for (int i = 0; i < 100; i++) {
                producer.send(new ProducerRecord<String, String>("devs4j-topic", String.valueOf(i), "devs4j-value"),
                        (recordMetadata, e) -> {
                            if (e != null) {
                                log.info("There was an error {} ", e.getMessage());
                            }
                            log.info("Offset = {} , Partition = {} , Topic = {} , Thread = {} ", recordMetadata.offset(),
                                    recordMetadata.partition(), recordMetadata.topic(), Thread.currentThread().getName());
                        });
            }
            producer.flush();
        }
    }
}
