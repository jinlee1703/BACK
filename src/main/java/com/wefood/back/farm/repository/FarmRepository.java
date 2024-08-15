package com.wefood.back.farm.repository;

import com.wefood.back.farm.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
