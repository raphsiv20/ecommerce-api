package com.ecommerce.api.services.implementations;

import com.ecommerce.api.models.dtos.CartDto;
import com.ecommerce.api.models.dtos.CartItemsDto;
import com.ecommerce.api.models.entities.CartEntity;
import com.ecommerce.api.models.entities.CartItemsEntity;
import com.ecommerce.api.repositories.CartItemsRepository;
import com.ecommerce.api.repositories.CartRepository;
import com.ecommerce.api.repositories.ProductRepository;
import com.ecommerce.api.services.CartItemsService;
import com.ecommerce.api.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CartItemsServiceImpl implements CartItemsService {

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Collection<CartItemsDto> getAll() {
        Collection<CartItemsDto> cartItemsDtosCollection = new ArrayList<>();
        for (CartItemsEntity cartItemsEntity : this.cartItemsRepository.findAll()) {
            CartItemsDto cartItemsDto = this.mapper.map(cartItemsEntity, CartItemsDto.class);
            cartItemsDtosCollection.add(cartItemsDto);
        }
        return cartItemsDtosCollection;

    }

    @Override
    public void create(CartItemsDto cartItemsDto, double productPrice) {
        boolean productPresent = false;
        CartItemsEntity cartItemsEntity = this.mapper.map(cartItemsDto, CartItemsEntity.class);
        for (CartItemsEntity cartItemsEntityLoop : this.cartItemsRepository.findAll()) {
            //Verifier si le panier contient deja le produit pour faire un +1 de la quantité et mettre à jour le prix du cart
            if (cartItemsEntityLoop.getCart().getCartId().equals(cartItemsEntity.getCart().getCartId()) && cartItemsEntityLoop.getProduct().getProductId().equals(cartItemsEntity.getProduct().getProductId())) {
                productPresent = true;
                cartItemsEntityLoop.setQuantity(cartItemsEntityLoop.getQuantity() + 1);
                this.cartItemsRepository.save(cartItemsEntityLoop);
                for (CartDto cartDto: this.cartService.getAll()) {
                    if (cartDto.getCartId().equals(cartItemsEntity.getCart().getCartId())){
                        this.cartService.updatePriceQuantityPositive(cartDto, 1, productPrice);
                    }
                }
            }
        }
        if (!productPresent) {
            this.cartItemsRepository.save(cartItemsEntity);
            int quantity = cartItemsEntity.getQuantity();
            for (CartDto cartDto: this.cartService.getAll()) {
                if (cartDto.getCartId().equals(cartItemsEntity.getCart().getCartId())){
                    this.cartService.updatePriceQuantityPositive(cartDto, quantity, productPrice);
                }
            }
        }

    }

    @Override
    public void updateQuantityPositive(CartItemsDto cartItemsDto, long cartId, long productId, int quantityAdded, double productPrice) {
        Collection<CartItemsEntity> cartItemsEntityCollection = this.cartItemsRepository.findAll();
        for (CartItemsEntity cartsItemsEntity : cartItemsEntityCollection) {
            if (cartsItemsEntity.getCart().getCartId().equals(cartId) && cartsItemsEntity.getProduct().getProductId().equals(productId)) {
                cartsItemsEntity.setQuantity(cartItemsDto.getQuantity());
                this.cartItemsRepository.save(cartsItemsEntity);

                for (CartDto cartDto: this.cartService.getAll()) {
                    if (cartDto.getCartId().equals(cartItemsDto.getCart().getCartId())){
                        this.cartService.updatePriceQuantityPositive(cartDto, quantityAdded, productPrice);
                    }
                }
                //update la quantitetotale et prixtotal du cart avec le updatePositif
            }
        }
    }

    @Override
    public void updateQuantityNegative(CartItemsDto cartItemsDto, long cartId, long productId, int quantityRemoved, double productPrice) {
        Collection<CartItemsEntity> cartItemsEntityCollection = this.cartItemsRepository.findAll();
        for (CartItemsEntity cartsItemsEntity : cartItemsEntityCollection) {
            if (cartsItemsEntity.getCart().getCartId().equals(cartId) && cartsItemsEntity.getProduct().getProductId().equals(productId)) {
                cartsItemsEntity.setQuantity(cartItemsDto.getQuantity());
                this.cartItemsRepository.save(cartsItemsEntity);

                for (CartDto cartDto: this.cartService.getAll()) {
                    if (cartDto.getCartId().equals(cartItemsDto.getCart().getCartId())){
                        this.cartService.updatePriceQuantityNegative(cartDto, quantityRemoved, productPrice);
                    }
                }
                //update la quantitetotale et prixtotal du cart avec le updateNegatif
            }
        }
    }


    @Override
    public void delete(Long cartId, Long productId, double productPrice, int quantityRemoved) {
        this.cartService.updatePriceQuantityNegative(this.cartService.getACartById(cartId), quantityRemoved, productPrice);
        for (CartItemsEntity cartItemsEntity : this.cartItemsRepository.findAll()) {
            if (cartItemsEntity.getCart().getCartId().equals(cartId) && cartItemsEntity.getProduct().getProductId().equals(productId)) {
                this.cartItemsRepository.deleteById(cartItemsEntity.getId());
            }
        }
    }
}
