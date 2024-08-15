package com.wefood.back.user.repository;

import com.wefood.back.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<Address> findByUserId(Long userId);

}
