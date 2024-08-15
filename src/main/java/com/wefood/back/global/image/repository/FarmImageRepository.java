package com.wefood.back.global.image.repository;

import com.wefood.back.global.image.entity.FarmImage;
import com.wefood.back.global.image.entity.ProductImage.Pk;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * class: FarmImageRepository.
 *
 * @author JBumLee
 * @version 2024/08/15
 */
public interface FarmImageRepository extends JpaRepository<FarmImage, Pk>{

}
