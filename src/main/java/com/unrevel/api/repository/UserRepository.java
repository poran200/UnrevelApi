package com.unrevel.api.repository;

import com.unrevel.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     Optional<User> findByUserNameIsAndIsActiveTrue(String userName);
     Optional<User> findByEmailAndIsActiveTrue(String email);
}
