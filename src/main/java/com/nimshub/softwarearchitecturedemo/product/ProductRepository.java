package com.nimshub.softwarearchitecturedemo.product;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Author Nirmala : 31:August:2023
 * This interface implements all methods required database transactions for Product using Jpa
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findByProductId(UUID id);
    List<Product> findAll(Specification<Product> spec);

    default List<Product> searchUsersByFirstNameOrLastName(String searchTerm) {
        return findAll((root, query, criteriaBuilder) -> {
            String likePattern = "%" + searchTerm + "%";
            return  criteriaBuilder.or(
                    criteriaBuilder.like(root.get("name"), likePattern),
                    criteriaBuilder.like(root.get("description"), likePattern)
            );

        });
    }

}
