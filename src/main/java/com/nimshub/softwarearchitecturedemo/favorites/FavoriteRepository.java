package com.nimshub.softwarearchitecturedemo.favorites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @Author Nirmala : 08:September:2023
 * This interface implements all methods required database transactions for Favorite using Jpa
 */

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
    Optional<Favorite> findByUserId(UUID userId);
}
