package com.example.usermanagement.repository;

import com.example.usermanagement.entity.UserPermission;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
    
    List<UserPermission> findByUserId(Long userId);
    
    List<UserPermission> findByPermissionId(Long permissionId);
    
    Optional<UserPermission> findByUserIdAndPermissionId(Long userId, Long permissionId);
    
    boolean existsByUserIdAndPermissionId(Long userId, Long permissionId);
    
    void deleteByUserIdAndPermissionId(Long userId, Long permissionId);
    
    @Query("SELECT up FROM UserPermission up JOIN FETCH up.permission WHERE up.user.id = :userId")
    List<UserPermission> findByUserIdWithPermissions(@Param("userId") Long userId);
} 