package com.wefood.back.user.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("유저 조회 실패");
    }
}
