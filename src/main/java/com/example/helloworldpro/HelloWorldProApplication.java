package com.example.helloworldpro;

import com.example.helloworldpro.kafka.MessageListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HelloWorldProApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldProApplication.class, args);
    }
    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }
}
