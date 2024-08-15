package com.wefood.back.global.image.repository;

import com.wefood.back.global.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * class: ImageRepository.
 *
 * @author JBumLee
 * @version 2024/08/15
 */
public interface ImageRepository extends JpaRepository<Image,Long> {

}
