package com.nimshub.softwarearchitecturedemo.favorites;

import com.nimshub.softwarearchitecturedemo.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @Author Nirmala : 08:September:2023
 * This entity class defines the all the properties of Favorite
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Favorite {
    @Id
    @SequenceGenerator(name = "FAV_SEQ", sequenceName = "FAV_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FAV_SEQ")
    private Integer id;
    private UUID favId;
    private UUID userId;
    @OneToMany
    private List<Product> productList;
}