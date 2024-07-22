package com.carrefour.kata.dtos;

import com.carrefour.kata.enums.DeliveryMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryDto {

    @Future(message = "Date must be in the future !")
    private LocalDateTime deliveryDateTime;

    private long idCustomer;

    @NotNull
    private DeliveryMethod deliveryMethod;
}
