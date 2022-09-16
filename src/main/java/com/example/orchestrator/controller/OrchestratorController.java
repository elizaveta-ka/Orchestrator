package com.example.orchestrator.controller;

import com.example.orchestrator.kafka.MessageProducer;
import com.example.orchestrator.kafka.MessageProducerFile;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;

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

    @KafkaListener(topics = "test-topic", containerFactory = "kafkaListenerContainerFactory")
    public void listenerReact(String str) throws IOException, InterruptedException {
        log.info("Listener orchestrator: from React,  str " + str);
//          messageProducer.sendMessage(product, "parser");
    }

    @KafkaListener(topics = "parser", containerFactory = "kafkaListenerContainerFactory")
    public void listener(String product) throws IOException, InterruptedException {
        log.info("Listener orchestrator: from parser String, parser " + product);
          messageProducer.sendMessage(product, "save");
    }

//пробовали отправить на фронт
//    @GetMapping("/orchestra")
//    public String show(String product) {
//        //System.out.println("Recieved message: from parser String " + product);
//        String string=product;
//        //  messageProducer.sendMessage(product);
//        return "";
//    }

    //принимаем файл с фронта и передаем в парсер
    @GetMapping()
    @KafkaListener(topics = "topicFrontToParser", containerFactory = "kafkaListenerContainerFactory")
    public void listener() {
        File file = new File("/Users/elizavetakabak/repos/Parser/src/main/resources/file.csv");
        log.info("Listener orchestrator: file from Front {}", file.getName());
        messageProducerFile.sendMessage(file, "topicFrontToParser");
        log.info("Producer orchestrator: file {} to Parser, topicFrontToParser", file.getName());
    }

}
