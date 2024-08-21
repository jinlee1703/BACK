package com.wefood.back.global.image.repository;

import com.wefood.back.farm.dto.FarmImageResponse;
import com.wefood.back.global.image.entity.FarmImage;
import com.wefood.back.global.image.entity.ProductImage.Pk;
import com.wefood.back.product.dto.ProductImageDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * class: FarmImageRepository.
 *
 * @author JBumLee
 * @version 2024/08/15
 */
public interface FarmImageRepository extends JpaRepository<FarmImage, Pk> {

    @Query("select new com.wefood.back.farm.dto.FarmImageResponse(i.name, pi.isThumbnail, pi.sequence) from Image i inner join FarmImage pi on i.id=pi.pk.imageId where pi.pk.farmId=:farmId")
    List<FarmImageResponse> queryByFarmId(Long farmId);



}
