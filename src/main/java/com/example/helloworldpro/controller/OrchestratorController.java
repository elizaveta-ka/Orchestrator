package com.example.helloworldpro.controller;

import com.example.helloworldpro.kafka.MessageProducer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orchestra")
public class OrchestratorController {

    MessageProducer messageProducer;

    public OrchestratorController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listener(String product) {
        System.out.println("Recieved message: " + product);
        messageProducer.sendMessage(product);
    }
}
