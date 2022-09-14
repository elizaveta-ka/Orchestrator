package com.example.helloworldpro.controller;

import com.example.helloworldpro.kafka.MessageProducer;
import com.example.helloworldpro.kafka.MessageProducerFile;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class OrchestratorController {

    MessageProducer messageProducer;
    MessageProducerFile messageProducerFile;

    @Autowired
    public OrchestratorController(MessageProducer messageProducer, MessageProducerFile messageProducerFile) {
        this.messageProducer = messageProducer;
        this.messageProducerFile = messageProducerFile;
    }

    @GetMapping("/orchestra")
    @KafkaListener(topics = "parser", containerFactory = "kafkaListenerContainerFactory")
    public void listener(String product) {
        log.info("Listener orchestrator: from parser String, parser " + product);
        //  messageProducer.sendMessage(product);

    }
//пробовали отправить на фронт
//    @GetMapping("/orchestra")
//    @KafkaListener(topics = "parser", containerFactory = "kafkaListenerContainerFactory")
//    public String listener(String product) {
//        //System.out.println("Recieved message: from parser String " + product);
//        String string=product;
//        //  messageProducer.sendMessage(product);
//        return string;
//    }

    //принимаем файл с фронта и передаем в парсер
    @GetMapping()
    @KafkaListener(topics = "topicFrontToParser", containerFactory = "kafkaListenerContainerFactory")
    public void listener() {
        File file = new File("/Users/elizavetakabak/repos/ServiceDemoProject/src/main/resources/file3.csv");
        log.info("Listener orchestrator: file from Front {}", file.getName());
        messageProducerFile.sendMessage(file, "topicFrontToParser");
        log.info("Producer orchestrator: file {} to Parser, topicFrontToParser", file.getName());
    }

}
