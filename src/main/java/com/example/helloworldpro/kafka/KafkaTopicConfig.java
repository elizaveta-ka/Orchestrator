package com.example.helloworldpro.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;


    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);

        return new KafkaAdmin(config);
    }

    @Bean
    public NewTopic topicParser() {
        return new NewTopic("parser", 1, (short) 1);
    }

    @Bean
    public NewTopic topicFrontToParser() {
        return new NewTopic("topicFrontToParser", 1, (short) 1);
    }
    @Bean
    public NewTopic topicSave() {
        return new NewTopic("save", 2, (short) 1);
    }

    //    @Bean
//    public NewTopic topicSaveOne() {
//        return new NewTopic("saveOne", 2, (short) 1);
//    }
    @Bean
    public NewTopic topicUpdate() {
        return new NewTopic("update", 2, (short) 1);
    }

    @Bean
    public NewTopic topicDelete() {
        return new NewTopic("delete", 2, (short) 1);
    }

    @Bean
    public NewTopic topicGetOne() {
        return new NewTopic("getOne", 2, (short) 1);
    }

    @Bean
    public NewTopic topicGetAll() {
        return new NewTopic("getAll", 2, (short) 1);
    }
    //topicName для создания новых топико

//    @Bean
//    public NewTopic topic() {
//        return new NewTopic(topicName, 1, (short) 1);
//    }

}
