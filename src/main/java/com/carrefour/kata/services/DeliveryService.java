package com.carrefour.kata.services;

import com.carrefour.kata.dtos.DeliveryDto;
import com.carrefour.kata.entities.Customer;
import com.carrefour.kata.entities.Delivery;
import com.carrefour.kata.enums.DeliveryMethod;
import com.carrefour.kata.exceptions.CustomException;

import java.util.List;

public interface DeliveryService {
    List<Delivery> getAvailableTimeSlots(DeliveryMethod deliveryMethod);
    void createDelivery(DeliveryDto deliveryDto) throws CustomException;

    List<Customer> getAllCustomers();

    Customer getCustomerById(long id);


}
