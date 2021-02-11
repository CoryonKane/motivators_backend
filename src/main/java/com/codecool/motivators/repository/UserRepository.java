package com.codecool.motivators.repository;

import com.codecool.motivators.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM account u WHERE LOWER(u.name) = LOWER(?1)")
    List<User> findAllByName (String name);
    Optional<User> findByEmail (String email);
}
