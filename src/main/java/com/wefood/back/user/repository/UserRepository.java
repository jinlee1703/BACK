package com.wefood.back.user.repository;

import com.wefood.back.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByPhoneNumberAndPassword(String phoneNumber, String password);

    User findByPhoneNumberAndPassword(String phoneNumber, String password);

}
