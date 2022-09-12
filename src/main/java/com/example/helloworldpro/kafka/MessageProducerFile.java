package com.example.helloworldpro.kafka;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.File;

@Slf4j
@NoArgsConstructor
@Component
public class MessageProducerFile {

    private KafkaTemplate<String, File> kafkaTemplate;
    @Autowired
    public MessageProducerFile(KafkaTemplate<String, File> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

//    @Value(value = "${kafka.topic.name}")
//    private String topicName;

    public void sendMessage(File fileName, String topicName) {
        ListenableFuture<SendResult<String, File>> future = kafkaTemplate.send(topicName, fileName);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Unable to send message = {} dut to: {}", fileName, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, File> stringDataSendResult) {
                log.info("Message sent successfully with offset = {}", stringDataSendResult.getRecordMetadata().offset());
            }
        });
    }
}
