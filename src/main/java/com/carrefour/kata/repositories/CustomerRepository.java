package com.carrefour.kata.repositories;

import com.carrefour.kata.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
}
