package com.example.helloworldpro.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@EnableKafka
@Configuration//принимаем файл с фронта для парсера
public class ConsumerFileConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public ConsumerFactory<String, File> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "consuming");

        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        Map<String, Class<?>> classMap = new HashMap<>();
        typeMapper.setIdClassMapping(classMap);
        typeMapper.addTrustedPackages("*");

        JsonDeserializer<File> jsonDeserializer = new JsonDeserializer<>(File.class);
        jsonDeserializer.setTypeMapper(typeMapper);
        jsonDeserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, File> kafkaListenerFileContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, File> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        log.info("Configure consumerOrchestrator File");
        return factory;
    }
}
