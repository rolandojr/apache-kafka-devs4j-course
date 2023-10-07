package com.devs4j.kafka.tecnical.challenge.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String name;
    private String email;

}
