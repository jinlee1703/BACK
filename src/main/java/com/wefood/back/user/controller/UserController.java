package com.wefood.back.user.controller;

import com.wefood.back.global.Message;
import com.wefood.back.user.dto.request.UserCreateRequest;
import com.wefood.back.user.dto.request.UserGetRequest;
import com.wefood.back.user.dto.request.UserLoginRequest;
import com.wefood.back.user.dto.response.UserGetResponse;
import com.wefood.back.user.dto.response.UserLoginResponse;
import com.wefood.back.user.entity.User;
import com.wefood.back.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserCreateRequest userCreateRequest) {
        userService.createUser(userCreateRequest);
    }


    @PostMapping("/login")
    public ResponseEntity<Message<UserLoginResponse>> loginUser(@RequestBody UserLoginRequest userLoginRequest) {

        Optional<User> user = userService.loginUser(userLoginRequest);

        if (user.isPresent()) {
            UserLoginResponse userLoginResponse = new UserLoginResponse(user.get().getIsSeller());
            return new ResponseEntity<>(new Message<>(200, "로그인 성공", userLoginResponse), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message(200, "로그인 실패", null), HttpStatus.OK);
    }

    @PostMapping("/mypage")
    public ResponseEntity<Message<UserGetResponse>> getUser(@RequestBody UserGetRequest getRequest) {

        UserGetResponse userGetResponse = userService.getUser(getRequest);
        return new ResponseEntity<>(new Message<>(200, "조회완료", userGetResponse), HttpStatus.OK);
    }

}
