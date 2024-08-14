package com.wefood.back.user.dto.request;

public record UserCreateRequest(
        String phoneNumber, String password, String name, Boolean isSeller,
        String accountNumber) {


}
