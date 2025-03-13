package com.microservices.product.service;

import com.microservices.product.dto.request.ProductRequest;

public interface ProductService {
    void create(ProductRequest productRequest);
}
