package com.wefood.back.user.controller;

import com.wefood.back.user.dto.request.UserCreateRequest;
import com.wefood.back.user.dto.request.UserGetRequest;
import com.wefood.back.user.dto.request.UserLoginRequest;
import com.wefood.back.user.dto.response.UserGetResponse;
import com.wefood.back.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.OK)
    public void loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        userService.loginUser(userLoginRequest);
    }

    @GetMapping
    public ResponseEntity<UserGetResponse> getUser(@RequestBody UserGetRequest getRequest) {
        UserGetResponse userGetResponse = userService.getUser(getRequest);
        return new ResponseEntity<>(userGetResponse, HttpStatus.OK);
    }

}
