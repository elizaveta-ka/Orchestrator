package com.example.helloworldpro.kafka;

import com.example.helloworldpro.model.Product;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@NoArgsConstructor
@Component
public class MessageProducer {

    private KafkaTemplate<String, Product> kafkaTemplate;
    @Autowired
    public MessageProducer(KafkaTemplate<String, Product> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value(value = "${kafka.topic.name}")
    private String topicName;


    public void sendMessage(Product product) {
        ListenableFuture<SendResult<String, Product>> future = kafkaTemplate.send(topicName, product);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Unable to send message = {} dut to: {}", product, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Product> stringDataSendResult) {
                log.info("Sent Message = {} with offset = {}", product, stringDataSendResult.getRecordMetadata().offset());
            }
        });
    }
}
