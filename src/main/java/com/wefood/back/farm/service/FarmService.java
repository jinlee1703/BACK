package com.wefood.back.farm.service;

import com.wefood.back.farm.dto.FarmImageResponse;
import com.wefood.back.farm.dto.FarmRequest;
import com.wefood.back.farm.dto.FarmResponse;
import com.wefood.back.farm.entity.Farm;
import com.wefood.back.farm.repository.FarmRepository;
import com.wefood.back.global.image.repository.FarmImageRepository;
import com.wefood.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {

    private static final String imgRoute = "https://s3.ap-northeast-2.amazonaws.com";
    private static final String productURL = "/farm/";
    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    private final FarmImageRepository farmImageRepository;

    private final FarmRepository farmRepository;

    private final UserRepository userRepository;

    public void createFarm(FarmRequest farmRequest, Long id) {

        Farm farm = Farm.builder()
                .name(farmRequest.name())
                .detail(farmRequest.detail())
                .user(userRepository.findById(id).get())
                .build();

        farmRepository.save(farm);
    }

    public List<FarmImageResponse> getFarmImage(Long id) {

        List<FarmImageResponse> farmImageResponses = farmImageRepository.queryByFarmId(id);

        for (FarmImageResponse response : farmImageResponses) {
            response.setName(imgRoute + "/" + bucketName + productURL + id + "/" + response.getImg());
        }
        return farmImageResponses;
    }

    public FarmResponse getFarm(Long userId) {

        if (farmRepository.existsByUserId(userId)) {
            return farmRepository.findByUserId(userId);
        }

        return null;
    }


}
