package com.example.helloworldpro.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class MessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value(value = "${kafka.topic.name}")
    private String topicName;//название топика на кот потом подписываем консьюмера, чтобы слушать сообщения

    public void sendMessage(String message) {
//        kafkaTemplate.send(topicName, message);
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Невозможно отправить сообщение=["
                        + message + "] из-за : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Отправлено сообщение=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }
}
