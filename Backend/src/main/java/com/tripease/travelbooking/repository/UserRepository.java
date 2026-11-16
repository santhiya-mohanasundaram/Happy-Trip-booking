package com.tripease.travelbooking.repository;

import com.tripease.travelbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = :identifier OR u.phone = :identifier OR u.username = :identifier")
    Optional<User> findByIdentifier(@Param("identifier") String identifier);
}
