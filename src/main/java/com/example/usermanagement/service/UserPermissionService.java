package com.example.usermanagement.service;

import com.example.usermanagement.entity.Permission;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.entity.UserPermission;
import com.example.usermanagement.exception.DuplicateUserPermissionException;
import com.example.usermanagement.exception.UserPermissionNotFoundException;
import com.example.usermanagement.repository.UserPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserPermissionService {
    
    private final UserPermissionRepository userPermissionRepository;
    private final UserService userService;
    private final PermissionService permissionService;
    
    @Autowired
    public UserPermissionService(UserPermissionRepository userPermissionRepository,
                                UserService userService,
                                PermissionService permissionService) {
        this.userPermissionRepository = userPermissionRepository;
        this.userService = userService;
        this.permissionService = permissionService;
    }
    
    public UserPermission assignPermissionToUser(Long userId, Long permissionId, String grantedBy) {
        User user = userService.getUserById(userId);
        Permission permission = permissionService.getPermissionById(permissionId);
        
        if (userPermissionRepository.existsByUserIdAndPermissionId(userId, permissionId)) {
            throw new DuplicateUserPermissionException(
                "User " + user.getEmail() + " already has permission " + permission.getName());
        }
        
        UserPermission userPermission = new UserPermission(user, permission, grantedBy);
        return userPermissionRepository.save(userPermission);
    }
    
    public void removePermissionFromUser(Long userId, Long permissionId) {
        if (!userPermissionRepository.existsByUserIdAndPermissionId(userId, permissionId)) {
            throw new UserPermissionNotFoundException(
                "User permission association not found for user ID: " + userId + " and permission ID: " + permissionId);
        }
        
        userPermissionRepository.deleteByUserIdAndPermissionId(userId, permissionId);
    }
    
    public List<UserPermission> getUserPermissions(Long userId) {
        // Verify user exists
        userService.getUserById(userId);
        return userPermissionRepository.findByUserIdWithPermissions(userId);
    }
    
    public List<Permission> getUserPermissionsList(Long userId) {
        return getUserPermissions(userId)
                .stream()
                .map(UserPermission::getPermission)
                .collect(Collectors.toList());
    }
    
    public List<UserPermission> getPermissionUsers(Long permissionId) {
        // Verify permission exists
        permissionService.getPermissionById(permissionId);
        return userPermissionRepository.findByPermissionId(permissionId);
    }
    
    public boolean userHasPermission(Long userId, String permissionName) {
        return getUserPermissionsList(userId)
                .stream()
                .anyMatch(permission -> permission.getName().equals(permissionName));
    }
    
    public boolean userHasPermission(Long userId, Long permissionId) {
        return userPermissionRepository.existsByUserIdAndPermissionId(userId, permissionId);
    }
} 