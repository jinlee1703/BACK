package com.wefood.back.user.exception;

public class LoginFailException extends RuntimeException{


    public LoginFailException() {
        super("로그인 실패");
    }
}
