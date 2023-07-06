package com.ecommerce.api.controllers;

import com.ecommerce.api.models.dtos.CartDto;
import com.ecommerce.api.models.requests.CartRequest;
import com.ecommerce.api.models.responses.CartExistsResponse;
import com.ecommerce.api.models.responses.CartResponse;
import com.ecommerce.api.services.CartService;
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
@RequestMapping("/v1/carts")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Collection<CartResponse>> getAllCarts() {
        Collection<CartResponse> cartResponseCollection = new ArrayList<>();
        for (CartDto cartDto : this.cartService.getAll()) {
            CartResponse cartResponse = this.mapper.map(cartDto, CartResponse.class);
            cartResponseCollection.add(cartResponse);
        }
        return new ResponseEntity<>(cartResponseCollection, HttpStatus.OK);
    }


    @GetMapping(value = "/{cartId}") //get
    public ResponseEntity<CartResponse> getACartById(@PathVariable long cartId) throws NoSuchElementException {
        CartResponse cartResponse;
        try {
            cartResponse = this.mapper.map(this.cartService.getACartById(cartId), CartResponse.class);
        } catch (NoSuchElementException e) {
            log.error("Le cart n'existe pas");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}") //get
    public ResponseEntity<CartResponse> getACartByUserId(@PathVariable long userId) throws NoSuchElementException {
        CartResponse cartResponse;
        try {
            cartResponse = this.mapper.map(this.cartService.getACartByUserId(userId), CartResponse.class);
        } catch (NoSuchElementException e) {
            log.error("Le cart n'existe pas");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

    @GetMapping("/cartExists/{userId}")
    public ResponseEntity<CartExistsResponse> cartExists (@PathVariable long userId) throws NoSuchElementException {
        CartExistsResponse cartExistsResponse = new CartExistsResponse();
        try {
            cartExistsResponse.setCartExists(this.cartService.exists(userId));

        } catch (NoSuchElementException e) {
            log.error("The user doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cartExistsResponse, HttpStatus.OK);
    }

    @PostMapping //create
    public void createCart(@RequestBody CartRequest cartRequest) {
        CartDto cartDto = this.mapper.map(cartRequest, CartDto.class);
        this.cartService.create(cartDto);
    }

    @PutMapping("/{cartId}")//id = cartId
    public void updateCartUser(@RequestBody CartRequest cartRequest, @PathVariable long cartId) {
        long userId = cartRequest.getUser().getUserId();
        this.cartService.uptadeCartUser(cartId, userId);
    }

    @PutMapping("/priceAndQuantity/{cartId}")//id = cartId
    public void updateCartPriceAndQuantity(@PathVariable long cartId) {
        this.cartService.resetQuantityPrice(cartId);
    }

    @DeleteMapping("/{id}") //delete
    public void deleteCart(@PathVariable("id") Long id) {
        //System.out.println("DELETE PRODUCT");
        this.cartService.delete(id);

    }
}
