package com.ecommerce.api.controllers;

import com.ecommerce.api.models.dtos.ProductDto;
import com.ecommerce.api.models.requests.ProductRequest;
import com.ecommerce.api.models.responses.ProductResponse;
import com.ecommerce.api.models.responses.ProductStockResponse;
import com.ecommerce.api.services.ProductService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/v1/products")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Collection<ProductResponse>> getAll() {
        Collection<ProductResponse> productResponseCollection = new ArrayList<>();
        for (ProductDto productDto : this.productService.getAll()) {
            ProductResponse productResponse = this.mapper.map(productDto, ProductResponse.class);
            productResponseCollection.add(productResponse);
        }
        return new ResponseEntity<>(productResponseCollection, HttpStatus.OK);
    }

    @GetMapping("/{id}") //get
    public ResponseEntity<ProductResponse> getAProduct(@PathVariable Long id) throws NoSuchElementException {
        ProductResponse productResponse;
        try {
            productResponse = this.mapper.map(this.productService.get(id), ProductResponse.class);
        } catch (NoSuchElementException e) {
            log.error("Le product n'existe pas");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productResponse, HttpStatus.OK);

    }

    @GetMapping("/stockProduct/{productId}") //get
    public ResponseEntity<ProductStockResponse> getAProductStock(@PathVariable Long productId) throws NoSuchElementException {
        ProductStockResponse productStockResponse = new ProductStockResponse();
        try {
            productStockResponse.setStock(this.productService.getAProductStock(productId));
        } catch (NoSuchElementException e) {
            log.error("Le product n'existe pas");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productStockResponse, HttpStatus.OK);
    }

    @GetMapping("/cat/{category}") //get
    public ResponseEntity<Collection<ProductResponse>> getProductsByCat(@PathVariable String category) throws NoSuchElementException {
        Collection<ProductResponse> productResponseCollection = new ArrayList<>();
        for (ProductDto productDto : this.productService.getProductsByCat(category)) {
            ProductResponse productResponse = this.mapper.map(productDto, ProductResponse.class);
            productResponseCollection.add(productResponse);
        }
        return new ResponseEntity<>(productResponseCollection, HttpStatus.OK);

    }


    @PostMapping //create
    public void create(@RequestBody ProductRequest productRequest) {
        ProductDto productDto = this.mapper.map(productRequest, ProductDto.class);
        if (!this.productService.exists(productDto.getProductName())) {
            this.productService.create(productDto);
        }
    }

    @PutMapping("/{productId}")
    public void updateAProduct(@RequestBody ProductRequest productRequest, @PathVariable("productId") Long productId) {
        ProductDto productDto = this.mapper.map(productRequest, ProductDto.class);
        productDto.setProductId(productId);
        this.productService.update(productDto);
    }

    @DeleteMapping("/{id}") //delete
    public void delete(@PathVariable("id") Long id) {
        //System.out.println("DELETE PRODUCT");
        this.productService.delete(id);

    }
}
