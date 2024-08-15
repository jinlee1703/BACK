package com.wefood.back.farm.controller;

import com.wefood.back.global.exception.FileUploadException;
import com.wefood.back.global.exception.InvalidRequestException;
import com.wefood.back.global.image.service.StorageService;
import com.wefood.back.product.dto.UploadImageRequestDto;
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
 * class: FarmController.
 *
 * @author JBumLee
 * @version 2024/08/12
 */
@RestController
@RequestMapping("/api/farm")
@RequiredArgsConstructor
public class FarmController {
    private final StorageService storageService;
    private final static String DIR_NAME = "farm";

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void uploadImages(
        @Valid @ModelAttribute UploadImageRequestDto requestDto,
        BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException(result);
        }

        try {
            storageService.saveImages(requestDto, DIR_NAME);
        } catch (IOException e) {
            throw new FileUploadException("An error occurred while uploading files.", e);
        }
    }
}
