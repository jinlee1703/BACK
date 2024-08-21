package com.wefood.back.farm.repository;

import com.wefood.back.farm.dto.FarmResponse;
import com.wefood.back.farm.entity.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * class: FarmRepository.
 *
 * @author JBum
 * @version 2024/08/15
 */
public interface FarmRepository extends JpaRepository<Farm,Long> {

    Page<FarmResponse> findFarmById(Pageable pageable);
}
