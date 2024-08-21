package com.wefood.back.farm.service;

import com.wefood.back.farm.dto.FarmImageResponse;
import com.wefood.back.farm.dto.FarmListResponse;
import com.wefood.back.farm.dto.FarmRequest;
import com.wefood.back.farm.dto.FarmResponse;
import com.wefood.back.farm.entity.Farm;
import com.wefood.back.farm.exception.FarmNotFoundException;
import com.wefood.back.farm.repository.FarmRepository;
import com.wefood.back.global.image.repository.FarmImageRepository;
import com.wefood.back.product.dto.ProductResponse;
import com.wefood.back.product.repository.ProductRepository;
import com.wefood.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {

    private static final String imgRoute = "https://s3.ap-northeast-2.amazonaws.com";
    private static final String farmURL = "/farm/";
    private static final String productURL = "/product/";
    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    private final FarmImageRepository farmImageRepository;

    private final FarmRepository farmRepository;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Page<FarmListResponse> getFarms(Pageable pageable) {
        Page<FarmListResponse> farms = farmRepository.findFarms(pageable);
        farms.forEach(farmListResponse -> farmListResponse.setImg(imgRoute + "/" + bucketName + farmURL + farmListResponse.getId() + "/" + farmListResponse.getImg()));
        return farms;
    }

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
            response.setName(imgRoute + "/" + bucketName + farmURL + id + "/" + response.getImg());
        }
        return farmImageResponses;
    }

    public FarmResponse getFarm(Long userId) {

        if (farmRepository.existsByUserId(userId)) {
            return farmRepository.findByUserId(userId);
        }

        return null;
    }

    public FarmResponse getFarmById(Long id) {
        if (farmRepository.existsById(id)) {
            return farmRepository.findFarmById(id);
        }

        throw new FarmNotFoundException();
    }

    public Page<ProductResponse> getProductsByFarm(Long farmId, Pageable pageable) {
        if (!farmRepository.existsById(farmId)) {
            throw new FarmNotFoundException();
        }
        Page<ProductResponse> products = productRepository.findProductByFarm_Id(farmId, pageable);
        products.forEach(productResponse -> productResponse.setImg(imgRoute + "/" + bucketName + productURL + productResponse.getId() + "/" + productResponse.getImg()));
        return products;
    }
}
