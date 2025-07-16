package com.example.usermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TagRequest {
    
    @NotBlank(message = "Tag is required")
    @Size(max = 50, message = "Tag must not exceed 50 characters")
    private String tag;
    
    public TagRequest() {
    }
    
    public TagRequest(String tag) {
        this.tag = tag;
    }
    
    public String getTag() {
        return tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    @Override
    public String toString() {
        return "TagRequest{" +
                "tag='" + tag + '\'' +
                '}';
    }
} 