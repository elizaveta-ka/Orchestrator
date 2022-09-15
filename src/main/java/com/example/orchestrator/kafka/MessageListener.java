package com.example.orchestrator.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;


@Slf4j
public class MessageListener {

    public MessageListener() {
    }
    MessageProducer messageProducer;

    @KafkaListener(topics = "save", containerFactory = "kafkaListenerContainerFactory")
    public void listenerSave(String product) {
        log.info("Received message for save from front:{}", product);
        messageProducer.sendMessage(product, "save");//отправляем на сохранение в базу
    }

    @KafkaListener(topics = "update", containerFactory = "kafkaListenerContainerFactory")
    public void listenerUpdate(String product) {
        log.info("Received message for update:{}", product);
        messageProducer.sendMessage(product, "update");//отправляем в базу
    }

    @KafkaListener(topics = "delete", containerFactory = "kafkaListenerContainerFactory")
    public void listenerDelete(String product) {
        log.info("Received message for delete:{}", product);
        messageProducer.sendMessage(product, "delete");
    }

    //getByID
    @KafkaListener(topics = "getOne", containerFactory = "kafkaListenerContainerFactory")
    public void listenerGetById(String product) {
        log.info("Received message for getById :{}", product);
        messageProducer.sendMessage(product, "getOne");
    }

    @KafkaListener(topics = "getAll", containerFactory = "kafkaListenerContainerFactory")
    public void listenerGetAll(String product) {
        log.info("Received message for getAll:{}", product);
        messageProducer.sendMessage(product, "getAll");
    }

}
