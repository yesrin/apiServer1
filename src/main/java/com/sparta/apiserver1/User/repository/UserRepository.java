package com.sparta.apiserver1.User.repository;

import com.sparta.apiserver1.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
   // Optional<User> findByEmail(String email);
}
