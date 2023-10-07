package com.devs4j.kafka.tecnical.challenge.main;

import com.devs4j.kafka.tecnical.challenge.model.Transaction;
import com.devs4j.kafka.tecnical.challenge.model.User;
import com.devs4j.kafka.tecnical.challenge.producer.TecnicalChallengeProducer;

import java.util.ArrayList;
import java.util.List;

public class TecnicalChallenge {

    public static void main(String[] args) {
        User user = User.builder()
                .id(1020)
                .name("Rolando")
                .email("rolando@gmail.com")
                .build();
        User user1 = User.builder()
                .id(1021)
                .name("Javier")
                .email("javier@gmail.com")
                .build();

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(Transaction.builder().user(user).action("Deposito").monto(200.0).build());
        transactionList.add(Transaction.builder().user(user).action("Deposito").monto(100.0).build());
        transactionList.add(Transaction.builder().user(user).action("Deposito").monto(200.0).build());
        transactionList.add(Transaction.builder().user(user).action("Retiro").monto(-300.0).build());
        transactionList.add(Transaction.builder().user(user1).action("Deposito").monto(200.0).build());
        transactionList.add(Transaction.builder().user(user1).action("Deposito").monto(200.0).build());

        TecnicalChallengeProducer.producer(transactionList);

    }
}
