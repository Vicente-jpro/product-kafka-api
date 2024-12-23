package com.apachkafka.api.publishers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.apachkafka.api.models.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductPublisher {
	
    private final KafkaTemplate<String, Product> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String productCreatedEventTopic;

    public void sendProductToTopic(Product product) {
    	String productId = product.getId();
        kafkaTemplate.send(productCreatedEventTopic, productId, product);
    }

}
