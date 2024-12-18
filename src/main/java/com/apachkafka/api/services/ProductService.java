package com.apachkafka.api.services;

import com.apachkafka.api.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public Product salvar(Product product) {
        return product;
    }

    public List<Product> listar(){
        Product productFirst = Product.builder()
                .id(1)
                .name("TV")
                .price(2000)
                .build();

        Product productFirst2 = Product.builder()
                .id(20)
                .name("Radio")
                .price(4000)
                .build();

        return List.of(productFirst, productFirst2);
    }
}
