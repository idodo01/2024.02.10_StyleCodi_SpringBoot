package ido.style.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class ProductDTO {
    private Integer no;
    private String name;
    private Integer price;
    private String detail;
    private LocalDateTime uploadedAt;
    private CategoryDTO category;
    private List<ProductImageDTO> images;

}











