package com.sparta.apiserver1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ApiServer1Application {

    public static void main(String[] args) {
        SpringApplication.run(ApiServer1Application.class, args);
    }

}
