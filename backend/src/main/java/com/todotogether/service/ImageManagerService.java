

package com.todotogether.service;

import com.todotogether.domain.dto.UploadFileDto;
import com.todotogether.util.FileManager;
import com.todotogether.util.UploadImageS3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageManagerService {
    private final FileManager fileManager;
    private final UploadImageS3 uploadImageS3;


    // 임시 파일 생성 & 업데이트 & 임시 파일 삭제
    @Transactional
    public UploadFileDto createAndUploadFile(MultipartFile mf, String filePath) throws Exception {
        long time = System.currentTimeMillis();
        String originalFilename = mf.getOriginalFilename();
        String saveFileName = String.format("%d_%s", time, originalFilename.replaceAll(" ", ""));

        // 파일 생성
        File uploadFile = null;
        try {
            Optional<File> uploadFileOpt = fileManager.convertMultipartFileToFile(mf);
            if (!uploadFileOpt.isPresent()) {
                throw new Exception("파일변환에 실패했습니다");
            }
            uploadFile = uploadFileOpt.get();

            // 파일 업로드
            String saveFilePath = uploadImageS3.upload(uploadFile, filePath, saveFileName);

            return UploadFileDto.create(originalFilename
                    , File.separator + saveFilePath);
        } catch(IOException e) {
            e.printStackTrace();
            throw new Exception("파일을 업로드 하던 중 에러가 발생했습니다");
        } finally {
            // 파일 삭제
            if (uploadFile != null) {
                uploadFile.delete();
            }
        }
    }
}

