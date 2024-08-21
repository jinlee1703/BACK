package com.wefood.back.farm.controller;

import com.wefood.back.farm.dto.FarmResponse;
import com.wefood.back.farm.service.FarmService;
import com.wefood.back.global.Message;
import com.wefood.back.global.exception.FileUploadException;
import com.wefood.back.global.exception.InvalidRequestException;
import com.wefood.back.global.image.dto.UploadImageRequestDto;
import com.wefood.back.global.image.dto.UploadThumbnailRequestDto;
import com.wefood.back.global.image.service.StorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    private final FarmService farmService;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/thumbnail")
    public void uploadThumbnail(
        @Valid @ModelAttribute UploadThumbnailRequestDto requestDto,
        BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidRequestException(result);
        }

        try {
            storageService.saveThumbnail(requestDto, DIR_NAME);
        } catch (IOException e) {
            throw new FileUploadException("An error occurred while uploading files.", e);
        }
    }

    @GetMapping
    public ResponseEntity<Message<Page<FarmResponse>>> getFarms(Pageable pageable) {
        Page<FarmResponse> farms = farmService.getFarms(pageable);
        Message<Page<FarmResponse>> message = new Message<>(200, "농가 조회 성공", farms);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
