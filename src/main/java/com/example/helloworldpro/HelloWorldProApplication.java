package com.example.helloworldpro;

import com.example.helloworldpro.kafka.MessageListener;
import com.example.helloworldpro.kafka.MessageProducerFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class HelloWorldProApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldProApplication.class, args);
        log.warn("Orchestrator run! " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")));

    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

}
