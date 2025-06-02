package com.example.usermanagement.service;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.exception.DuplicateEmailException;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
    
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateEmailException("User with email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }
    
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        
        // Check if email is being changed and if it already exists
        if (!user.getEmail().equals(userDetails.getEmail()) && 
            userRepository.existsByEmail(userDetails.getEmail())) {
            throw new DuplicateEmailException("User with email " + userDetails.getEmail() + " already exists");
        }
        
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        
        return userRepository.save(user);
    }
    
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
    
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<User> searchUsers(String searchTerm) {
        return userRepository.findBySearchTerm(searchTerm);
    }
    
    public List<User> getUsersByFirstName(String firstName) {
        return userRepository.findByFirstNameContainingIgnoreCase(firstName);
    }
    
    public List<User> getUsersByLastName(String lastName) {
        return userRepository.findByLastNameContainingIgnoreCase(lastName);
    }
} 