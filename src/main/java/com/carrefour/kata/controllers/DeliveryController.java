package com.carrefour.kata.controllers;


import com.carrefour.kata.dtos.DeliveryDto;
import com.carrefour.kata.entities.Customer;
import com.carrefour.kata.entities.Delivery;
import com.carrefour.kata.exceptions.CustomException;
import com.carrefour.kata.services.DeliveryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping ("/api/v1/customers")
//@CrossOrigin("*")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public List<Customer> getAllCustomers() {

        return deliveryService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable long id) {
        return deliveryService.getCustomerById(id);
    }

    @GetMapping("/{idCustomer}/deliveries")
    public Set<Delivery> getdeliveriesByCustomerName(@PathVariable long idCustomer) {
        return deliveryService.getCustomerById(idCustomer).getDeliveries();
    }

    @GetMapping("/{idCustomer}/deliveries/{idDelivery}")
    public Delivery getDeliveryByCustomerName(@PathVariable long idCustomer, @PathVariable long idDelivery) {
        return deliveryService.getCustomerById(idCustomer).getDeliveries()
                .stream()
                .filter(e-> idDelivery == e.getId())
                .findFirst()
                .orElseThrow(()-> new CustomException("Delivery not found !"));
    }

    @PostMapping("/{idCustomer}/deliveries")
    public ResponseEntity<String> bookTimeSlot(
            @PathVariable long idCustomer,
            @Valid @RequestBody DeliveryDto deliveryDto) {
        deliveryDto.setIdCustomer(idCustomer);
        deliveryService.createDelivery(deliveryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Delivery created !");
    }
}