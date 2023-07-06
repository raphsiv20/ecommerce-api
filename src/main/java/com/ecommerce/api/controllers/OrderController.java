package com.ecommerce.api.controllers;

import com.ecommerce.api.models.dtos.OrderDto;
import com.ecommerce.api.models.dtos.OrderItemsDto;
import com.ecommerce.api.models.dtos.UserDto;
import com.ecommerce.api.models.requests.OrderRequest;
import com.ecommerce.api.models.responses.OrderResponse;
import com.ecommerce.api.services.OrderItemsService;
import com.ecommerce.api.services.OrderService;

import com.ecommerce.api.services.PdfGenerateService;
import com.ecommerce.api.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/v1/orders")
@CrossOrigin
public class OrderController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PdfGenerateService pdfGenerateService;

    @Value("${pdf.directory}")
    private String pdfDirectory;

    //faire une methode qui me genere les infos de la commande!
    @GetMapping(value = "/generatePdf/{orderId}/{filename}")
    public void getPDF(@PathVariable long orderId, @PathVariable String filename) throws IOException {

        //Je récup les données
        OrderDto orderDto = this.orderService.getAnOrderById(orderId);
        Collection<OrderItemsDto> orderItemsDto = this.orderItemsService.getAllProductsOnAnOrder(orderId);
        UserDto userDto = this.userService.getAUserById(orderDto.getUser().getUserId());

        this.pdfGenerateService.generatePdfFile("orderPdfFile", orderDto, orderItemsDto, userDto, filename + ".pdf");

    }

    @GetMapping(value = "/renderPdf/{filename}", produces= MediaType.APPLICATION_PDF_VALUE)
    public byte[] renderPdfFile(@PathVariable String filename) throws IOException {
        try {
            FileInputStream fis= new FileInputStream(this.pdfDirectory + filename + ".pdf");
            byte[] targetArray = new byte[fis.available()];
            fis.read(targetArray);
            return targetArray;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/allOrders")
    public ResponseEntity<Collection<OrderResponse>> getAllOrders() {
        Collection<OrderResponse> orderResponseCollection = new ArrayList<>();
        for (OrderDto orderDto : this.orderService.getAll()) {
            OrderResponse orderResponse = this.mapper.map(orderDto, OrderResponse.class);
            orderResponseCollection.add(orderResponse);
        }
        return new ResponseEntity<>(orderResponseCollection, HttpStatus.OK);
    }


    @GetMapping(value = "/anOrder/{orderId}") //get
    public ResponseEntity<OrderResponse> getAnOrderById(@PathVariable long orderId) throws NoSuchElementException {
        OrderResponse orderResponse;
        try {
            orderResponse = this.mapper.map(this.orderService.getAnOrderById(orderId), OrderResponse.class);
        } catch (NoSuchElementException e) {
            log.error("Le cart n'existe pas");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}") //get
    public ResponseEntity<Collection<OrderResponse>> getAllOrdersByUserId(@PathVariable long userId) {
        Collection<OrderResponse> orderResponseCollection = new ArrayList<>();
        for (OrderDto orderDto: this.orderService.getAllOrdersByUser(userId)) {
            orderResponseCollection.add(this.mapper.map(orderDto, OrderResponse.class));
        }
        return new ResponseEntity<>(orderResponseCollection, HttpStatus.OK);
    }

    @PostMapping //create
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = new OrderResponse();
        OrderDto orderDto = this.mapper.map(orderRequest, OrderDto.class);
        orderResponse = this.mapper.map(this.orderService.create(orderDto), OrderResponse.class);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PutMapping("/shipping/{orderId}")//id = cartId
    public void updateOrderShippingStatus(@RequestBody OrderRequest orderRequest, @PathVariable long orderId) {
        String newOrderShippingStatus = orderRequest.getOrderShippingStatus();
        this.orderService.updateOrderShippingStatus(orderId, newOrderShippingStatus);
    }

    @PutMapping("/payment/{orderId}")//id = cartId
    public void updateOrderPaymentStatus(@RequestBody OrderRequest orderRequest, @PathVariable long orderId) {
        String newOrderPaymentStatus = orderRequest.getOrderPaymentStatus();
        this.orderService.updateOrderPaymentStatus(orderId, newOrderPaymentStatus);
    }

    @DeleteMapping("/{orderId}") //delete
    public void deleteOrder(@PathVariable Long orderId) {
        //System.out.println("DELETE PRODUCT");
        this.orderService.delete(orderId);

    }
}
