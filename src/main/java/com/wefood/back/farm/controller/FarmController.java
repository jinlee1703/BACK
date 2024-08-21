package com.wefood.back.farm.controller;

import com.wefood.back.farm.dto.FarmImageResponse;
import com.wefood.back.farm.dto.FarmRequest;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    private final FarmService farmService;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload-part")
    public void uploadImages(
            @RequestPart("files") MultipartFile[] files, @RequestParam("id") Long id) {

        UploadImageRequestDto uploadImageRequestDto = new UploadImageRequestDto(Arrays.stream(files).toList(), id);
        try {
            storageService.saveImages(uploadImageRequestDto, DIR_NAME);
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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create")
    public void createFarm(@RequestParam Long id, @RequestBody FarmRequest farmRequest) {
        farmService.createFarm(farmRequest, id);
    }

    @GetMapping
    public Message<FarmResponse> getFarm(@RequestParam Long id) {
        return new Message<>(200, "농장 조회", farmService.getFarm(id));
    }

    @GetMapping("/image")
    public Message<List<FarmImageResponse>> getFarmImage(@RequestParam Long id) {
        return new Message<>(200, "농장 이미지 조회", farmService.getFarmImage(id));
    }

    @GetMapping
    public ResponseEntity<Message<Page<FarmResponse>>> getFarms(Pageable pageable) {
        Page<FarmResponse> farms = farmService.getFarms(pageable);
        Message<Page<FarmResponse>> message = new Message<>(200, "농가 조회 성공", farms);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
