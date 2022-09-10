package com.example.helloworldpro.kafka;

import com.example.helloworldpro.model.Product;
import com.example.helloworldpro.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class MessageListener {

//    @Autowired
//    private IProductRepository productRepository;

//    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerContainerFactory")
//    public void listener(Product product) {
//        System.out.println("Recieved message: " + product);
////        productRepository.save(product);
//    }
}
