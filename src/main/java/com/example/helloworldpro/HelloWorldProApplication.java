package com.example.helloworldpro;

import com.example.helloworldpro.kafka.MessageListener;
import com.example.helloworldpro.kafka.MessageProducerFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

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
