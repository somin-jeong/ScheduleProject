package com.example.scheduleProject.domain.user.service;

import com.example.scheduleProject.domain.user.dto.request.LoginDto;
import com.example.scheduleProject.domain.user.dto.request.SaveUserRequestDto;
import com.example.scheduleProject.domain.user.dto.request.UpdateUserRequestDto;
import com.example.scheduleProject.domain.user.dto.response.SaveUserResponseDto;
import com.example.scheduleProject.domain.user.entity.Users;
import com.example.scheduleProject.domain.user.repository.UserRepository;
import com.example.scheduleProject.global.auth.util.PasswordEncoder;
import com.example.scheduleProject.global.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.scheduleProject.global.response.status.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    public static final String SESSION_NAME = "loginUser";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SaveUserResponseDto saveUser(SaveUserRequestDto requestDto) {
        Users user = Users.builder()
                .name(requestDto.name())
                .email(requestDto.email())
                .password(passwordEncoder.encode(requestDto.password()))
                .build();

        Users savedUser = userRepository.save(user);
        return new SaveUserResponseDto(savedUser.getUserId());
    }

    @Override
    public void updateUser(Long userId, UpdateUserRequestDto requestDto) {
        userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserException(NOT_EXIST_USER_ERROR));

        Integer count = userRepository.updateUser(userId, requestDto.name(), requestDto.email())
                .orElseThrow(() -> new UserException(FAIL_USER_UPDATE_ERROR));
        if (count != 1) {
            throw new UserException(FAIL_USER_UPDATE_ERROR);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserException(NOT_EXIST_USER_ERROR));

        Integer count = userRepository.deleteByUserId(userId)
                .orElseThrow(() -> new UserException(FAIL_USER_DELETE_ERROR));
        if (count != 1) {
            throw new UserException(FAIL_USER_DELETE_ERROR);
        }
    }

    @Override
    public void login(LoginDto requestDto, HttpServletRequest request) {
        Users user = userRepository.findByEmail(requestDto.email())
                .orElseThrow(() -> new UserException(NOT_EXIST_EMAIL_ERROR));

        if (!passwordEncoder.matches(requestDto.password(), user.getPassword())) {
            throw new UserException(NOT_MATCH_PASSWORD_ERROR);
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_NAME, user.getUserId());
    }
}
