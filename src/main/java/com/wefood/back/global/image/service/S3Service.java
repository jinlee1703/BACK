package com.wefood.back.global.image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.wefood.back.product.dto.UploadImageRequestDto;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: S3Service.
 *
 * AWS S3 전용 서비스
 * 사진 업로드를 지원한다.
 *
 * @author JBumLee
 * @version 2024/08/11
 */
@RequiredArgsConstructor
@Transactional
@Slf4j
public class S3Service implements ImageService{
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucket;

    /**
     *
     * @param uploadImageRequestDto
     * @param dirName
     * @return
     * @throws IOException
     */
    @Override
    public List<String> saveImages(UploadImageRequestDto uploadImageRequestDto, String dirName) throws IOException {
        List<String> uploadImageUrls = new ArrayList<>();
        List<MultipartFile> multipartFiles = uploadImageRequestDto.getFiles();
        Long id = uploadImageRequestDto.getId();
        for (MultipartFile multipartFile : multipartFiles) {
            File uploadFile = convert(multipartFile)  // 파일 변환할 수 없으면 에러
                .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
            String imageUrl = upload(uploadFile, dirName, id);
            uploadImageUrls.add(imageUrl);
        }

        return uploadImageUrls;
    }
    // S3로 파일 업로드하기
    private String upload(File uploadFile, String dirName, Long id) {
        // 폴더가 없으면 생성
        createDirIfNotExists(dirName);

        String fileName = dirName + "/" + id + "/" + UUID.randomUUID() + uploadFile.getName(); // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    // 폴더가 존재하지 않으면 폴더 생성
    private void createDirIfNotExists(String dirName) {
        if (!amazonS3Client.doesObjectExist(bucket, dirName + "/")) {
            amazonS3Client.putObject(bucket, dirName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
            log.info("Directory created: {}", dirName);
        } else {
            log.info("Directory already exists: {}", dirName);
        }
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("File delete fail");
    }

    // 로컬에 파일 업로드 하기
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File("uploads"+ "/" + file.getOriginalFilename());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
