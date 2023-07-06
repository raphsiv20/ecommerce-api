package com.ecommerce.api.services.implementations;

import com.ecommerce.api.models.dtos.CartDto;
import com.ecommerce.api.models.dtos.ProductDto;
import com.ecommerce.api.models.entities.CartEntity;
import com.ecommerce.api.models.entities.UserEntity;
import com.ecommerce.api.repositories.CartRepository;
import com.ecommerce.api.repositories.UserRepository;
import com.ecommerce.api.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Collection<CartDto> getAll() {
        Collection<CartDto> cartDtosCollection = new ArrayList<>();
        for (CartEntity cartEntity : this.cartRepository.findAll()) {
            CartDto cartDto = this.mapper.map(cartEntity, CartDto.class);
            cartDtosCollection.add(cartDto);
        }
        return cartDtosCollection;
    }

    @Override
    public CartDto getACartById(long cartId) {
        CartDto cartDto;
        cartDto = this.mapper.map(this.cartRepository.findById(cartId).get(), CartDto.class);
        return cartDto;
    }

    @Override
    public CartDto getACartByUserId(long userId) {
        CartDto cartDto;
        for (CartEntity cartEntity: this.cartRepository.findAll()) {
            if (cartEntity.getUser().getUserId() == userId) {
                cartDto = this.mapper.map(cartEntity, CartDto.class);
                return cartDto;
            }
        }
        return null;
    }


    @Override
    public void updatePriceQuantityPositive(CartDto cartDto, int quantityDifference, double productPrice) { //augmentation du prixTotal et de la quantité
        CartEntity cartEntity = this.mapper.map(cartDto, CartEntity.class);
        int currentTotalQuantity = cartEntity.getTotalQuantity();
        double currentTotalPrice = cartEntity.getTotalPrice();
        double priceToAdd = quantityDifference * productPrice;
        cartEntity.setTotalQuantity(currentTotalQuantity + quantityDifference);
        cartEntity.setTotalPrice(currentTotalPrice + priceToAdd);
        this.cartRepository.save(cartEntity);
    }

    @Override
    public void updatePriceQuantityNegative(CartDto cartDto, int quantityDifference, double productPrice) {//diminution du prixTotal et de la quantité
        CartEntity cartEntity = this.mapper.map(cartDto, CartEntity.class); //cartEntity initial
        int currentTotalQuantity = cartEntity.getTotalQuantity();
        double currentTotalPrice = cartEntity.getTotalPrice();
        double priceToRemove = quantityDifference * productPrice;
        cartEntity.setTotalQuantity(currentTotalQuantity - quantityDifference);
        cartEntity.setTotalPrice(currentTotalPrice - priceToRemove);
        this.cartRepository.save(cartEntity);
    }

    @Override
    public void updateTotalPrice(long cartId, double priceDifference, boolean upOrDown) {
        CartEntity cartEntity = this.cartRepository.findById(cartId).get();
        double currentTotalPrice = cartEntity.getTotalPrice();
        if (upOrDown) {
            cartEntity.setTotalPrice(currentTotalPrice + priceDifference);
        } else {
            cartEntity.setTotalPrice(currentTotalPrice - priceDifference);
        }
        this.cartRepository.save(cartEntity);
    }

    @Override
    public void resetQuantityPrice(long cartId) {
        CartEntity cartEntity = this.cartRepository.findById(cartId).get();
        cartEntity.setTotalPrice(0.0);
        cartEntity.setTotalQuantity(0);
        this.cartRepository.save(cartEntity);
    }

    @Override
    public void uptadeCartUser(long cartId, long userId) {
        CartEntity cartEntity = this.cartRepository.findById(cartId).get();
        cartEntity.setUser(this.userRepository.findById(userId).get());
        this.cartRepository.save(cartEntity);
    }


    @Override
    public void create(CartDto cartDto) {
        this.cartRepository.save(this.mapper.map(cartDto, CartEntity.class));
    }

    @Override
    public void delete(Long id) {
        this.cartRepository.deleteById(id);
    }

    @Override
    public Boolean exists(long userId) {
        for (CartEntity cartEntity : this.cartRepository.findAll()) {
            if (cartEntity.getUser().getUserId().equals(userId)){
                return true;
            }
        }
        return false;
    }

}
