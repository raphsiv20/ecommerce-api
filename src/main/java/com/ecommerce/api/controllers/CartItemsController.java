package com.ecommerce.api.controllers;

import com.ecommerce.api.models.dtos.CartItemsDto;
import com.ecommerce.api.models.entities.ProductEntity;
import com.ecommerce.api.models.requests.CartItemsRequest;
import com.ecommerce.api.models.responses.CartItemsResponse;
import com.ecommerce.api.repositories.ProductRepository;
import com.ecommerce.api.services.CartItemsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/v1/cartItems")
@CrossOrigin
public class CartItemsController {

    @Autowired
    private CartItemsService cartItemsService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Collection<CartItemsResponse>> getAllCartItems() {
        Collection<CartItemsResponse> cartItemsResponseCollection = new ArrayList<>();
        for (CartItemsDto cartItemsDto : this.cartItemsService.getAll()) {
            CartItemsResponse cartItemsResponse = this.mapper.map(cartItemsDto, CartItemsResponse.class);
            cartItemsResponseCollection.add(cartItemsResponse);
        }
        return new ResponseEntity<>(cartItemsResponseCollection, HttpStatus.OK);
    }


    @GetMapping(value = "/{cartId}") //id=cartId
    public ResponseEntity<Collection<CartItemsResponse>> getACartItems(@PathVariable long cartId) throws NoSuchElementException {
        Collection<CartItemsResponse> cartItemsResponseCollection = new ArrayList<>();
        try {
            for (CartItemsDto cartItemsDto: this.cartItemsService.getAll()) {
                if (cartItemsDto.getCart().getCartId().equals(cartId)) {
                    CartItemsResponse cartItemsResponse = this.mapper.map(cartItemsDto, CartItemsResponse.class);
                    cartItemsResponseCollection.add(cartItemsResponse);
                }
            }
        } catch (NoSuchElementException e) {
            log.error("Le panier n'existe pas");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cartItemsResponseCollection, HttpStatus.OK);
    }

    @PostMapping //pour ajouter un article dans un panier
    public void createCartItems(@RequestBody CartItemsRequest cartItemsRequest) {
        CartItemsDto cartItemsDto = this.mapper.map(cartItemsRequest, CartItemsDto.class);
        for (ProductEntity productEntity: this.productRepository.findAll()) {
            if (productEntity.getProductId() == cartItemsRequest.getProduct().getProductId()) {
                double productPrice = productEntity.getProductPrice();
                this.cartItemsService.create(cartItemsDto, productPrice);
            }
        }

    }

    @PutMapping()
    public void updateCartItemsQuantity(@RequestBody CartItemsRequest cartItemsRequest) {
        Collection<CartItemsDto> collectionCartItems = this.cartItemsService.getAll();
        CartItemsDto cartItemsDto = this.mapper.map(cartItemsRequest, CartItemsDto.class);
        long productId = cartItemsDto.getProduct().getProductId();
        long cartId = cartItemsDto.getCart().getCartId();
        for (ProductEntity productEntity: this.productRepository.findAll()) {
            if (productEntity.getProductId() == cartItemsRequest.getProduct().getProductId()) {
                double productPrice = productEntity.getProductPrice();
                for (CartItemsDto cartsItemsDto: collectionCartItems){
                    if (cartsItemsDto.getCart().getCartId().equals(cartId) && cartsItemsDto.getProduct().getProductId().equals(productId)){
                        int initialQuantity = cartsItemsDto.getQuantity();
                        if (cartItemsDto.getQuantity() > cartsItemsDto.getQuantity()) {
                            this.cartItemsService.updateQuantityPositive(cartItemsDto, cartId, productId, cartItemsDto.getQuantity() - initialQuantity, productPrice);
                        } else {
                            this.cartItemsService.updateQuantityNegative(cartItemsDto, cartId, productId, initialQuantity - cartItemsDto.getQuantity(), productPrice);
                        }
                    }
                }
            }
        }




    }

    @DeleteMapping("/{cartId}/{productId}") //appeler la methode update negative pour mettre à jour les infos du panier
    public void deleteCartItems(@PathVariable long cartId, @PathVariable long productId) {
        for (CartItemsDto cartItemsDto : this.cartItemsService.getAll()) {
            if (cartItemsDto.getProduct().getProductId().equals(productId) && cartItemsDto.getCart().getCartId().equals(cartId)) {
                int quantityRemoved = cartItemsDto.getQuantity();
                for (ProductEntity productEntity: this.productRepository.findAll()) {
                    if (productEntity.getProductId() == productId) {
                        double productPrice = productEntity.getProductPrice();
                        this.cartItemsService.delete(cartId, productId, productPrice, quantityRemoved);
                    }
                }
            }
        }
    }
}
