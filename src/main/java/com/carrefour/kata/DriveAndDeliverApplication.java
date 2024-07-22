package com.carrefour.kata;

import com.carrefour.kata.entities.Customer;
import com.carrefour.kata.entities.Delivery;
import com.carrefour.kata.enums.DeliveryMethod;
import com.carrefour.kata.repositories.CustomerRepository;
import com.carrefour.kata.repositories.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@SpringBootApplication
public class DriveAndDeliverApplication implements CommandLineRunner {

    private final DeliveryRepository deliveryRepository;
    private final CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(DriveAndDeliverApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //initialize data for testing...

        Delivery delivery1 = new Delivery();
        delivery1.setDeliveryDateTime(LocalDateTime.now().plusDays(1).withHour(9).withMinute(0));
        delivery1.setDeliveryMethod(DeliveryMethod.DRIVE);

        Delivery delivery2 = new Delivery();
        delivery2.setDeliveryDateTime(LocalDateTime.now().plusDays(1).withHour(10).withMinute(0));
        delivery2.setDeliveryMethod(DeliveryMethod.DELIVERY);

        Delivery delivery3 = new Delivery();
        delivery3.setDeliveryDateTime(LocalDateTime.now().plusDays(1).withHour(11).withMinute(0));
        delivery3.setDeliveryMethod(DeliveryMethod.DELIVERY);

        Delivery delivery4 = new Delivery();
        delivery4.setDeliveryDateTime(LocalDateTime.now().plusDays(1).withHour(11).withMinute(0));
        delivery4.setDeliveryMethod(DeliveryMethod.DELIVERY_ASAP);

        Customer customer1 = new Customer();
        customer1.setName("Yahya");
        customer1.setDeliveries(Set.of(delivery1, delivery2));
        delivery1.setCustomer(customer1);
        delivery2.setCustomer(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Toto");
        customer2.setDeliveries(Set.of(delivery3, delivery4));

        delivery3.setCustomer(customer2);
        delivery4.setCustomer(customer2);

        customerRepository.saveAll(List.of(customer2, customer1));
    }
}