package com.wefood.back.global.image.service;

import com.wefood.back.product.dto.UploadImageRequestDto;
import java.io.IOException;
import java.util.List;

/**
 * class: ImageService.
 *
 * ImageService는 이미지를 저장하는 기능을 반드시 지원 할 필요가 있다.
 * 이 떄 한 종류의 Cloud Storage에 종속되지 않게 구현 할 필요가 있다.
 *
 * @author JBumLee
 * @version 2024/08/11
 */
public interface StorageService {
    List<String> saveImages(UploadImageRequestDto uploadImageRequestDto, String dirName) throws IOException;
}
