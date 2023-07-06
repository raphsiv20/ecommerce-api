package com.ecommerce.api.services;

import com.ecommerce.api.models.dtos.CartItemsDto;

import java.util.Collection;

public interface CartItemsService {
    Collection<CartItemsDto> getAll();

    void updateQuantityPositive(CartItemsDto cartItemsDto, long cartId, long productId, int quantityAdded, double productPrice);
    void updateQuantityNegative(CartItemsDto cartItemsDto, long cartId, long productId, int quantityRemoved, double productPrice);

    void create(CartItemsDto cartItemsDto, double productPrice);

    void delete(Long cartId, Long productId, double productPrice, int quantityRemoved);
}
