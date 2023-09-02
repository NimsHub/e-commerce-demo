package com.nimshub.softwarearchitecturedemo.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

/**
 * @Author Nirmala : 31:August:2023
 * This entity class defines the all the properties of Product
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(name = "PRODUCT_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
    private Integer id;
    @NotNull
    private UUID productId;
    private String name;
    private String price;
    private boolean isAvailable;
    private String description;
    private String image;
}
