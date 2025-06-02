package com.example.usermanagement.repository;

import com.example.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    List<User> findByFirstNameContainingIgnoreCase(String firstName);
    
    List<User> findByLastNameContainingIgnoreCase(String lastName);
    
    @Query("SELECT u FROM User u WHERE " +
           "u.firstName LIKE %:searchTerm% OR " +
           "u.lastName LIKE %:searchTerm% OR " +
           "u.email LIKE %:searchTerm%")
    List<User> findBySearchTerm(@Param("searchTerm") String searchTerm);
    
    boolean existsByEmail(String email);
} 