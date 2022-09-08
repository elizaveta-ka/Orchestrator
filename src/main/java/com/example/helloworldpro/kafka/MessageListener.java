package com.example.helloworldpro.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    @Value("${kafka.topic.name}")
    private String topicName;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @KafkaListener(topics = "${kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void listenGroup(String message){
        System.out.println("Получено сообщение в группе" + groupId+ "  " + message);
    }
}
