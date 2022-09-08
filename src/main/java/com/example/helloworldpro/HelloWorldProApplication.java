package com.example.helloworldpro;

import com.example.helloworldpro.kafka.MessageListener;
import com.example.helloworldpro.kafka.MessageProducer;
import com.example.helloworldpro.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HelloWorldProApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HelloWorldProApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);


        Product tom = new Product("футболка", 10000, "одежда", "мужская", "хлопок", "50", 10);
        Product theo = new Product("куртка", 50000, "одежда", "женская", "кожа", "44", 8);
        Product black = new Product("штаны", 7000, "одежда", "мужская", "деним", "50", 10);
        producer.sendMessage(tom);
        producer.sendMessage(theo);
        producer.sendMessage(black);
    }
}
