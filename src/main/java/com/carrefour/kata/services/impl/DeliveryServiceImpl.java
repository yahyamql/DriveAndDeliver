package com.carrefour.kata.services.impl;

import com.carrefour.kata.dtos.DeliveryDto;
import com.carrefour.kata.entities.Customer;
import com.carrefour.kata.entities.Delivery;
import com.carrefour.kata.enums.DeliveryMethod;
import com.carrefour.kata.exceptions.CustomException;
import com.carrefour.kata.repositories.CustomerRepository;
import com.carrefour.kata.repositories.DeliveryRepository;
import com.carrefour.kata.services.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<Delivery> getAvailableTimeSlots(DeliveryMethod deliveryMethod) {
        return deliveryRepository.findByDeliveryMethod(deliveryMethod);
    }

    @Override
    public void createDelivery(DeliveryDto deliveryDto) throws CustomException {
//        Delivery delivery = deliveryRepository.findById(timeSlotId)
//                .orElseThrow(() -> new CustomException("TimeSlot not found"));

        Customer customer = customerRepository.findById(deliveryDto.getIdCustomer())
                .orElseThrow(() -> new CustomException("Customer not found"));

        Delivery delivery = new Delivery();
        delivery.setDeliveryMethod(deliveryDto.getDeliveryMethod());
        delivery.setDeliveryDateTime(deliveryDto.getDeliveryDateTime());
        delivery.setCustomer(customer);
        customer.getDeliveries().add(delivery);

        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(long idCustomer) {
        return customerRepository.findById(idCustomer)
                .orElseThrow(() -> new CustomException("Customer not found !"));
    }
}
