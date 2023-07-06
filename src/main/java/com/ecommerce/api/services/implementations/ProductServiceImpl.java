package com.ecommerce.api.services.implementations;

import com.ecommerce.api.models.dtos.CartDto;
import com.ecommerce.api.models.dtos.CartItemsDto;
import com.ecommerce.api.models.dtos.ProductDto;
import com.ecommerce.api.models.entities.ProductEntity;
import com.ecommerce.api.repositories.ProductRepository;
import com.ecommerce.api.services.CartItemsService;
import com.ecommerce.api.services.CartService;
import com.ecommerce.api.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemsService cartItemsService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Collection<ProductDto> getAll() {
        Collection<ProductDto> productDtosCollection = new ArrayList<>();
        for (ProductEntity productEntity : this.productRepository.findAll()) {
            ProductDto productDto = this.mapper.map(productEntity, ProductDto.class);
            productDtosCollection.add(productDto);
        }
        return productDtosCollection;
    }

    @Override
    public Collection<ProductDto> getProductsByCat(String category) {
        Collection<ProductDto> productDtosCollection = new ArrayList<>();
        for (ProductEntity productEntity : this.productRepository.findAll()) {
            ProductDto productDto = this.mapper.map(productEntity, ProductDto.class);
            if (productDto.getProductCategory().equals(category)) {
                productDtosCollection.add(productDto);
            }
        }
        return productDtosCollection;
    }

    @Override
    public int getAProductStock(long productId) {
        for (ProductEntity productEntity: this.productRepository.findAll()) {
            if (productEntity.getProductId().equals(productId)) {
                return productEntity.getStock();
            }
        }
        return 0;
    }

    @Override
    public ProductDto get(Long id) {
        ProductDto productDto;
        productDto = this.mapper.map(this.productRepository.findById(id).get(), ProductDto.class);
        return productDto;
    }

    @Override
    public void update(ProductDto productDto) {
        ProductEntity productEntity = this.mapper.map(this.productRepository.findById(productDto.getProductId()).get(), ProductEntity.class);
        if (productDto.getProductImage() != null) {
            productEntity.setProductImage(productDto.getProductImage());
        }

        if (productDto.getProductDescription() != null) {
            productEntity.setProductDescription(productDto.getProductDescription());
        }

        if (productDto.getProductName() != null) {
            productEntity.setProductName(productDto.getProductName());
        }

        if (productDto.getProductPrice() != null) {
            for (CartItemsDto cartItemDto : this.cartItemsService.getAll()) {
                if (cartItemDto.getProduct().getProductId() == productDto.getProductId()) {
                    CartDto cartDto = this.cartService.getACartById(cartItemDto.getCart().getCartId());
                    int itemQuantity = cartItemDto.getQuantity();
                    double currentPrice = productEntity.getProductPrice();
                    double newPrice = productDto.getProductPrice();
                    if (newPrice > currentPrice) {
                        double priceDifference = (newPrice - currentPrice) * itemQuantity;
                        this.cartService.updateTotalPrice(cartDto.getCartId(), priceDifference, true);
                    } else {
                        double priceDifference = (currentPrice - newPrice) * itemQuantity;
                        this.cartService.updateTotalPrice(cartDto.getCartId(), priceDifference, false);
                    }
                }
            }
            productEntity.setProductPrice(productDto.getProductPrice());
        }

        if (productDto.getProductCategory() != null) {
            productEntity.setProductCategory(productDto.getProductCategory());
        }

        if (productDto.getStock() != null) {
            productEntity.setStock(productDto.getStock());
        }

        this.productRepository.save(productEntity);
    }

    @Override
    public void create(ProductDto productDto) {
        this.productRepository.save(this.mapper.map(productDto, ProductEntity.class));
    }

    @Override
    public void delete(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Boolean exists(String productName) {
        for (ProductEntity productEntity : this.productRepository.findAll()) {
            if (productEntity.getProductName().equals(productName)) {
                return true;
            }
        }
        return false;
    }
}
