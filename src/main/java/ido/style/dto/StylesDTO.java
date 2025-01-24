package ido.style.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StylesDTO {
    private Integer no;

    private ProductDTO product_top;
    private ProductDTO product_outer;
    private ProductDTO product_bottom;
    private ProductDTO product_shoes;
    private ProductDTO product_bag;

    private UserDTO user;

}
