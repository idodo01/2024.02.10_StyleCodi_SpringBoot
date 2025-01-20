package ido.style.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // 같은 객체 참조
        if (o == null || getClass() != o.getClass()) return false; // 타입 검사
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(no, that.no); // no 필드를 기준으로 비교
    }

    @Override
    public int hashCode() {
        return Objects.hash(no); // no를 기준으로 hashCode 생성
    }
}











