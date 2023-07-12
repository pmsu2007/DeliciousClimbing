package sangmyungdae.deliciousclimbing.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sangmyungdae.deliciousclimbing.domain.entity.TbFile;
import sangmyungdae.deliciousclimbing.dto.community.FileDto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FileStore {

    // 루트 경로 불러오기
    private final String rootPath = System.getProperty("user.dir");
    // 프로젝트 루트 경로에 있는 files 디렉토리
    private final String fileDir = rootPath + "/files/";

    public String getFullPath(String filename) { return fileDir + filename; }

    public TbFile storeFile(MultipartFile multipartFile)  {

        if(multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        // 작성자가 업로드한 파일명 -> 서버 내부에서 관리하는 파일명
        String storeFileName = UUID.randomUUID() + "." + extractExt(originalFileName);
        // 파일을 저장하는 부분 -> 파일경로 + storeFileName에 저장

        try {
            multipartFile.transferTo(new File(getFullPath(storeFileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return TbFile.builder()
                .uploadFileName(originalFileName)
                .storeFileName(storeFileName)
                .build();
    }

    public List<TbFile> storeFiles(MultipartFile[] multipartFiles) {
        List<TbFile> storeFileResult = new ArrayList<>();

        // multipartFiles -> null 처리
        if (multipartFiles != null) {
            for (MultipartFile multipartFile : multipartFiles) {
                if(!multipartFile.isEmpty()) {
                    storeFileResult.add(storeFile(multipartFile));
                }
            }
        }

        return storeFileResult;
    }

    // 확장자 추출
    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }

}
