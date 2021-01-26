package com.codecool.motivators.repository;

import com.codecool.motivators.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
