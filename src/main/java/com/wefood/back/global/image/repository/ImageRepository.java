package com.wefood.back.global.image.repository;

import com.wefood.back.global.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
