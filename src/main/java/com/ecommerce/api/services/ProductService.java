package com.ecommerce.api.services;

import com.ecommerce.api.models.dtos.ProductDto;

import java.util.Collection;

public interface ProductService {
    Collection<ProductDto> getAll();
    Collection<ProductDto> getProductsByCat(String category);
    int getAProductStock(long productId);
    ProductDto get(Long id);
    void update(ProductDto productDto);
    void create(ProductDto productDto);
    void delete(Long id);
    Boolean exists(String productName);
}
