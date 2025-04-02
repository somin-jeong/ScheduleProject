package com.example.scheduleProject.domain.user.service;


import com.example.scheduleProject.domain.user.dto.request.SaveUserRequestDto;
import com.example.scheduleProject.domain.user.dto.request.UpdateUserRequestDto;
import com.example.scheduleProject.domain.user.dto.response.SaveUserResponseDto;

public interface UserService {
    SaveUserResponseDto saveUser(SaveUserRequestDto requestDto);

    void updateUser(Long userId, UpdateUserRequestDto requestDto);

    void deleteUser(Long userId);
}
