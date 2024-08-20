package com.wefood.back.global.image.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: UploadThumbnailRequestDto.
 *
 * @author JBumLee
 * @version 2024/08/20
 */

@Getter
@Setter
public class UploadThumbnailRequestDto {
    @NotNull(message = "파일이 입력되지 않았습니다.")
    private MultipartFile files;

    @NotNull(message = "ID가 null입니다.")
    @Min(value = 1, message = "1이상이어야 합니다.")
    private Long id;
}
