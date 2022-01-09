package com.todotogether.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;

@Getter
@AllArgsConstructor
public class UploadFileDto {
    // 원본파일 명
    private String originalFileName;
    // 파일경로
    private String uploadFilePath;

    public static UploadFileDto create(String originalFileName, String uploadFilePath) {
        return new UploadFileDto(originalFileName, uploadFilePath);
    }
}
