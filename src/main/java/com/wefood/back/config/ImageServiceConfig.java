package com.wefood.back.config;

import com.wefood.back.global.image.repository.ProductImageRepository;
import com.wefood.back.global.image.service.ImageService;
import com.wefood.back.global.image.service.S3Service;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * class: ImageServiceConfig.
 *
 * @author JBumLee
 * @version 2024/08/11
 */
@Configuration
public class ImageServiceConfig {
    @Bean
    @ConditionalOnProperty(name = "image.service.impl", havingValue = "s3Service", matchIfMissing = true)
    public ImageService s3Service(S3Client s3Client, ProductImageRepository productImageRepository) {
        return new S3Service(s3Client, productImageRepository);
    }
}
