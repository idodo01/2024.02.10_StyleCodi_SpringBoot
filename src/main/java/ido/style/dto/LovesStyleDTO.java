package ido.style.dto;

import ido.style.searchApi.ProductNaverShopDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LovesStyleDTO {
    private Integer no;
    private UserDTO user;
    private StylesProductDTO style;
    private ProductNaverShopDTO product_top;
    private ProductNaverShopDTO product_outer;
    private ProductNaverShopDTO product_bottom;
    private ProductNaverShopDTO product_shoes;
    private ProductNaverShopDTO product_bag;

}
