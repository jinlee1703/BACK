package com.wefood.back.user.controller;

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
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest) {

        Optional<User> user = userService.loginUser(userLoginRequest);

        return user.map(value -> new ResponseEntity<>(new UserLoginResponse(value.getIsSeller()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<UserGetResponse> getUser(@RequestBody UserGetRequest getRequest) {
        UserGetResponse userGetResponse = userService.getUser(getRequest);
        return new ResponseEntity<>(userGetResponse, HttpStatus.OK);
    }

}
