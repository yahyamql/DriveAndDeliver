package com.carrefour.kata.repositories;

import com.carrefour.kata.entities.Delivery;
import com.carrefour.kata.enums.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByDeliveryMethod(DeliveryMethod deliveryMethod);
}
