package com.codingshuttle.projects.lovable_clone.service;

import com.codingshuttle.projects.lovable_clone.dto.auth.AuthResponse;
import com.codingshuttle.projects.lovable_clone.dto.auth.LoginRequest;
import com.codingshuttle.projects.lovable_clone.dto.auth.SignUpRequest;
import com.codingshuttle.projects.lovable_clone.dto.auth.UserProfileResponse;

public interface AuthService {
    AuthResponse signup(SignUpRequest request);

    AuthResponse login(LoginRequest request);

    UserProfileResponse getProfile(Long userId);
}
