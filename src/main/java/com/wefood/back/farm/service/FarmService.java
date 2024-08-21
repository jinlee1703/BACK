package com.wefood.back.farm.service;

import com.wefood.back.farm.dto.FarmResponse;
import com.wefood.back.farm.repository.FarmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FarmService {

    private final FarmRepository farmRepository;

    public FarmService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    public Page<FarmResponse> getFarms(Pageable pageable) {
        return farmRepository.findFarmById(pageable);
    }
}
