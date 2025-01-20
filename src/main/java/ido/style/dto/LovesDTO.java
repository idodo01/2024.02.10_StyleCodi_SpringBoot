package ido.style.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class LovesDTO {
    private Integer no;
    private UserDTO user;
    private ProductDTO product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // 같은 객체 참조
        if (o == null || getClass() != o.getClass()) return false; // 타입 검사
        LovesDTO lovesDTO = (LovesDTO) o;
        return Objects.equals(product, lovesDTO.product); // product 비교
    }

    @Override
    public int hashCode() {
        return Objects.hash(product); // product를 기준으로 hashCode 생성
    }
}
