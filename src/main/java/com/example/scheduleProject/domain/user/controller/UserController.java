package com.example.scheduleProject.domain.user.controller;

import com.example.scheduleProject.domain.user.dto.request.LoginDto;
import com.example.scheduleProject.domain.user.dto.request.SaveUserRequestDto;
import com.example.scheduleProject.domain.user.dto.request.UpdateUserRequestDto;
import com.example.scheduleProject.domain.user.dto.response.SaveUserResponseDto;
import com.example.scheduleProject.domain.user.service.UserService;
import com.example.scheduleProject.global.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 로그인
     *
     * @param requestDto (이메일, 비밀번호)
     * @param request (HttpServletRequest)
     * @return 로그인 완료 메시지
     */
    @PostMapping("/login")
    private BaseResponse<String> saveUser(@RequestBody @Valid LoginDto requestDto, HttpServletRequest request) {
        userService.login(requestDto, request);
        return new BaseResponse<>("로그인 성공");
    }

    /**
     * 사용자 생성
     *
     * @param requestDto (사용자 이름, 이메일, 비밀번호)
     * @return 생성된 사용자의 id를 포함한 응답 객체
     */
    @PostMapping("/users/signup")
    private BaseResponse<SaveUserResponseDto> saveUser(@RequestBody @Valid SaveUserRequestDto requestDto) {
        SaveUserResponseDto responseDto = userService.saveUser(requestDto);
        return new BaseResponse<>(responseDto);
    }

    /**
     * 특정 사용자 ID에 해당하는 사용자 이름 수정
     *
     * @param userId 수정할 사용자 ID
     * @param requestDto 수정할 이름, 이메일
     * @return 수정 성공 메시지
     */
    @PutMapping("/users/{userId}")
    private BaseResponse<String> updateUsername(@PathVariable @Valid Long userId, @RequestBody @Valid UpdateUserRequestDto requestDto) {
        userService.updateUser(userId, requestDto);
        return new BaseResponse<>("수정 완료했습니다.");
    }

    /**
     * 특정 사용자 ID에 해당하는 사용자 삭제
     *
     * @param userId 삭제할 사용자 ID
     * @return 삭제 성공 메시지
     */
    @DeleteMapping("/users/{userId}")
    private BaseResponse<String> deleteUser(@PathVariable @Valid Long userId) {
        userService.deleteUser(userId);
        return new BaseResponse<>("삭제 완료했습니다.");
    }

}
