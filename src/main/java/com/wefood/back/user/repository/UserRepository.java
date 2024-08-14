package com.wefood.back.user.repository;

import com.wefood.back.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByPhoneNumberAndPassword(String phoneNumber, String password);


    Optional<User> findByPhoneNumberAndPassword(String phoneNumber, String password);

}
