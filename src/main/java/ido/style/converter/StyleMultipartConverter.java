package ido.style.converter;


import ido.style.dto.ProductImageDTO;
import ido.style.dto.StyleProductImageDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

// admin 게시글 이미지 업로드시,
public class StyleMultipartConverter implements Converter<MultipartFile, StyleProductImageDTO> {
    @Override
    public StyleProductImageDTO convert(MultipartFile multipartFile) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            byte[] data = multipartFile.getBytes();
            return StyleProductImageDTO.builder()
                    .fileName(originalFilename)
                    .data(data)
                    .build();
        }catch (Exception e){
//            log.error("파일 변환 중 오류 발생!: " + e.getMessage());
        }
        return null;
    }
}
