package com.apachkafka.api.services;

import com.apachkafka.api.models.Product;
import com.apachkafka.api.publishers.ProductPublisher;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

   // private final ProductPublisher publisher;

    @Value("${spring.kafka.topic}")
    private String productCreatedEventTopic;
    
	private final KafkaTemplate<String, Product> kafkaTemplate;
	
    public Product salvar(Product product) {
    	// TODO: Save products
    	String productId = UUID.randomUUID().toString(); 
        product.setId(productId);
        
        // Sent to the topic.
        kafkaTemplate.send(productCreatedEventTopic, productId, product);
        //publisher.sendProductToTopic(product);

        return product;
    }

}
