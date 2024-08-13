package com.wefood.back.product.controller;

import com.wefood.back.global.Message;
import com.wefood.back.global.exception.FileUploadException;
import com.wefood.back.global.exception.InvalidRequestException;
import com.wefood.back.product.dto.UploadImageRequestDto;
import com.wefood.back.global.image.service.ImageService;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * class: ProductController.
 *
 * @author JBumLee
 * @version 2024/08/11
 */
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ImageService imageService;
    private final static String DIR_NAME = "product";

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void uploadImages(
        @Valid @ModelAttribute UploadImageRequestDto requestDto,
        BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException(result);
        }
        try {
            imageService.saveImages(requestDto, DIR_NAME);
        } catch (IOException e) {
            throw new FileUploadException("An error occurred while uploading files.", e);
        }
    }
}
