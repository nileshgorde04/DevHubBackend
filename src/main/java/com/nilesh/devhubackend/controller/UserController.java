package com.nilesh.devhubackend.controller;


import com.nilesh.devhubackend.model.User;
import com.nilesh.devhubackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") 
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        boolean isValid = userService.loginUser(user.getEmail(), user.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
    @GetMapping("/check-auth")
    public ResponseEntity<Boolean> checkAuthentication() {
        boolean isAuthenticated = userService.isUserAuthenticated();
        return ResponseEntity.ok(isAuthenticated);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        userService.logoutUser();
        return ResponseEntity.ok("Logged out successfully");
    }


}
