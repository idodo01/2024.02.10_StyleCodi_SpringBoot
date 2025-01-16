package ido.style.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class StyleProductDTO {
    private Integer no;
    private String name;
    private String detail;
    private LocalDateTime uploadedAt;
    private StyleCategoryDTO category;
    private List<StyleProductImageDTO> images;

}











