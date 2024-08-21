package com.wefood.back.global.image.repository;

import com.wefood.back.global.image.dto.ImageDetailResponse;
import com.wefood.back.global.image.entity.FarmImage;
import com.wefood.back.global.image.entity.ProductImage.Pk;
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
public interface FarmImageRepository extends JpaRepository<FarmImage, Pk>{

    @Query("select new com.wefood.back.global.image.dto.ImageDetailResponse(i.name, fi.isThumbnail, fi.sequence) from FarmImage fi inner join Image i on i.id=fi.pk.imageId where fi.pk.farmId=:farmId")
    List<ImageDetailResponse> findByPk_FarmId(@Param("farmId") Long farmId);
}
