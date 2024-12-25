package com.apachkafka.api.services;

import com.apachkafka.api.models.Product;
import com.apachkafka.api.publishers.ProductPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
@Slf4j
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
        CompletableFuture<SendResult<String, Product>> sendProcess = kafkaTemplate.send(productCreatedEventTopic, productId, product);
        //publisher.sendProductToTopic(product);

        /*
        // When we whant to see the message on the topic.
        sendProcess.whenComplete((result, exception) ->{
        	
        	if (exception != null)
        		log.error("Message was not sent to kafka topic. {}", exception.getMessage());
        	else
        		log.info("Message was successfully set to topic. {}", result.getRecordMetadata());
        });
        
        sendProcess.join();
        */
        return product;
    }

}
