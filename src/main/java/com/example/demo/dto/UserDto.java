package com.example.demo.dto;

import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * Created by Devon Ravihansa on 9/22/2017.
 */
@Service
@Data
public class UserDto {
    private String username;
    private String password;
    private boolean isAdmin;
}
