package ido.style.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDTO {
    private Integer no;
    private UserDTO user;
    private ProductDTO product;
    private Integer amount;
}
