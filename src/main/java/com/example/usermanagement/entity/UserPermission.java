package com.example.usermanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_permissions", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "permission_id"}))
public class UserPermission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;
    
    @Column(name = "granted_at", nullable = false)
    private LocalDateTime grantedAt;
    
    @Column(name = "granted_by")
    private String grantedBy;
    
    // Constructors
    public UserPermission() {
    }
    
    public UserPermission(User user, Permission permission, String grantedBy) {
        this.user = user;
        this.permission = permission;
        this.grantedBy = grantedBy;
        this.grantedAt = LocalDateTime.now();
    }
    
    // JPA Lifecycle methods
    @PrePersist
    protected void onCreate() {
        grantedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Permission getPermission() {
        return permission;
    }
    
    public void setPermission(Permission permission) {
        this.permission = permission;
    }
    
    public LocalDateTime getGrantedAt() {
        return grantedAt;
    }
    
    public void setGrantedAt(LocalDateTime grantedAt) {
        this.grantedAt = grantedAt;
    }
    
    public String getGrantedBy() {
        return grantedBy;
    }
    
    public void setGrantedBy(String grantedBy) {
        this.grantedBy = grantedBy;
    }
    
    @Override
    public String toString() {
        return "UserPermission{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : null) +
                ", permission=" + (permission != null ? permission.getName() : null) +
                ", grantedAt=" + grantedAt +
                ", grantedBy='" + grantedBy + '\'' +
                '}';
    }
} 