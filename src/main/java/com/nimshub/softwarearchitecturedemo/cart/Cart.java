package com.nimshub.softwarearchitecturedemo.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Nirmala : 31:August:2023
 * This entity class defines the all the properties of Cart
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @SequenceGenerator(name = "CART_SEQ", sequenceName = "CART_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_SEQ")
    private Integer id;

}