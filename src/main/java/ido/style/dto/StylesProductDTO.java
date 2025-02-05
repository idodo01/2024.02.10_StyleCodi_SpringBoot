package ido.style.dto;

import ido.style.searchApi.ProductNaverShopDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StylesProductDTO {
    private Integer no;

    private ProductNaverShopDTO product_top;
    private ProductNaverShopDTO product_outer;
    private ProductNaverShopDTO product_bottom;
    private ProductNaverShopDTO product_shoes;
    private ProductNaverShopDTO product_bag;

    private UserDTO user;

}
