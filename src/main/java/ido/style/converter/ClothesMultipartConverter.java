package ido.style.converter;


import ido.style.dto.ClothesImageDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

// admin 게시글 이미지 업로드시,
public class ClothesMultipartConverter implements Converter<MultipartFile, ClothesImageDTO> {
    @Override
    public ClothesImageDTO convert(MultipartFile multipartFile) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            byte[] data = multipartFile.getBytes();
            return ClothesImageDTO.builder()
                    .fileName(originalFilename)
                    .data(data)
                    .build();
        }catch (Exception e){
//            log.error("파일 변환 중 오류 발생!: " + e.getMessage());
        }
        return null;
    }
}