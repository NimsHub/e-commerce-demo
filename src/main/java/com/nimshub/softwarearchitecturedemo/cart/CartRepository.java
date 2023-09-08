package com.nimshub.softwarearchitecturedemo.cart;

import com.nimshub.softwarearchitecturedemo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @Author Nirmala : 31:August:2023
 * This interface implements all methods required database transactions for Cart using Jpa
 */

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByUserId(UUID userId);
}
