package com.carrefour.kata.controllers;


import com.carrefour.kata.dtos.DeliveryDto;
import com.carrefour.kata.entities.Customer;
import com.carrefour.kata.services.DeliveryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@AllArgsConstructor
@RestController
@RequestMapping ("/api/v1/customers")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public Flux<Customer> getAllCustomers() {
        return deliveryService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Mono<Customer> getCustomerById(@PathVariable long id) {
        return deliveryService.getCustomerById(id);
    }

    @PostMapping("/{idCustomer}/deliveries")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createDelivery(@PathVariable long idCustomer, @Valid @RequestBody DeliveryDto deliveryDto) {
        deliveryDto.setIdCustomer(idCustomer);
        return deliveryService.createDelivery(deliveryDto);
    }
}