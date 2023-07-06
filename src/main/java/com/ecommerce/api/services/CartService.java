package com.ecommerce.api.services;

import com.ecommerce.api.models.dtos.CartDto;
import com.ecommerce.api.models.dtos.ProductDto;

import java.util.Collection;

public interface CartService {
    Collection<CartDto> getAll();
    CartDto getACartById(long cartId);
    CartDto getACartByUserId(long cartId);
    void updatePriceQuantityPositive(CartDto cartDto, int quantityDifference, double productPrice);
    void updatePriceQuantityNegative(CartDto cartDto, int quantityDifference, double productPrice);
    void updateTotalPrice(long cartId, double priceDifference, boolean upOrDown);
    void resetQuantityPrice(long cartId);
    void uptadeCartUser (long cartId, long userId);
    void create(CartDto cartDto);
    void delete(Long id);
    Boolean exists(long userId);
}
