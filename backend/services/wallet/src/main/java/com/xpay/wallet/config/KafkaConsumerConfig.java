package com.xpay.wallet.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.xpay.wallet.dto.event.UserCreatedEventDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, UserCreatedEventDTO> userCreatedEventConsumerFactory() {
        JsonDeserializer<UserCreatedEventDTO> deserializer = new JsonDeserializer<>(UserCreatedEventDTO.class);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(false);
        deserializer.setRemoveTypeHeaders(false);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "wallet-service-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserCreatedEventDTO> userCreatedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserCreatedEventDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userCreatedEventConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, UUID> uuidConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "wallet-service-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        JsonDeserializer<UUID> deserializer = new JsonDeserializer<>(UUID.class);
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UUID> uuidKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UUID> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(uuidConsumerFactory());
        return factory;
    }
}
