package com.nimshub.softwarearchitecturedemo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);

    Optional<User> findByVerificationCode(String verificationCode);
    Optional<User> findByEmail(String email);
}
