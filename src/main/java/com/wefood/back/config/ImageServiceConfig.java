package com.wefood.back.config;

import com.amazonaws.services.s3.AmazonS3Client;
import com.wefood.back.global.image.service.ImageService;
import com.wefood.back.global.image.service.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class: ImageServiceConfig.
 *
 * @author JBumLee
 * @version 2024/08/11
 */
@Configuration
public class ImageServiceConfig {
    @Value("${image.service.impl}")
    private String imageServiceImpl;
    @Bean
    @ConditionalOnProperty(name = "image.service.impl", havingValue = "s3Service", matchIfMissing = true)
    public ImageService s3Service(AmazonS3Client amazonS3Client) {
        System.out.println(imageServiceImpl);
        return new S3Service(amazonS3Client);
    }
}
