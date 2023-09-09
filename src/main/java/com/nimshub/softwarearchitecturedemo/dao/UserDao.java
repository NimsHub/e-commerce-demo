package com.nimshub.softwarearchitecturedemo.dao;

import com.nimshub.softwarearchitecturedemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    User findUserByVerificationCode(String uuid);
}
