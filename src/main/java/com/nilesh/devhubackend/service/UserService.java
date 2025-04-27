package com.nilesh.devhubackend.service;

import com.nilesh.devhubackend.model.User;

public interface UserService {
    User registerUser(User user);
    boolean loginUser(String email, String password);
    boolean isUserAuthenticated();
    void logoutUser();
}