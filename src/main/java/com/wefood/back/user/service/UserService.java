package com.wefood.back.user.service;

import com.wefood.back.user.dto.request.UserCreateRequest;
import com.wefood.back.user.dto.request.UserGetRequest;
import com.wefood.back.user.dto.request.UserLoginRequest;
import com.wefood.back.user.dto.response.UserGetResponse;
import com.wefood.back.user.entity.User;
import com.wefood.back.user.exception.LoginFailException;
import com.wefood.back.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;


    public void createUser(UserCreateRequest createRequest) {

        User user = User.builder()
                .name(createRequest.name())
                .password(createRequest.password())
                .accountNumber(createRequest.accountNumber())
                .isSeller(createRequest.isSeller())
                .isResign(false)
                .phoneNumber(createRequest.phoneNumber())
                .build();

        userRepository.save(user);
    }

    public void loginUser(UserLoginRequest loginRequest) {

        if (!userRepository.existsByPhoneNumberAndPassword(loginRequest.phoneNumber(), loginRequest.password())) {
            throw new LoginFailException();
        }
    }

    public UserGetResponse getUser(UserGetRequest getRequest) {
        User user = userRepository.findByPhoneNumberAndPassword(getRequest.phoneNumber(), getRequest.password());
        return user.convertToUserGetResponse();
    }

}
