package com.example.scheduleProject.domain.user.service;


import com.example.scheduleProject.domain.user.dto.request.LoginDto;
import com.example.scheduleProject.domain.user.dto.request.SaveUserRequestDto;
import com.example.scheduleProject.domain.user.dto.request.UpdateUserRequestDto;
import com.example.scheduleProject.domain.user.dto.response.SaveUserResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    SaveUserResponseDto saveUser(SaveUserRequestDto requestDto);

    void updateUser(Long userId, UpdateUserRequestDto requestDto);

    void deleteUser(Long userId);

    void login(LoginDto requestDto, HttpServletRequest request);
}
