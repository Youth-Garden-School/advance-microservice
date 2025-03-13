package com.microservices.product.service.implement;

import com.microservices.product.dto.request.ProductRequest;
import com.microservices.product.entity.Product;
import com.microservices.product.repository.ProductRepository;
import com.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void create(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
        log.info("Product created successfully");
    }
}
