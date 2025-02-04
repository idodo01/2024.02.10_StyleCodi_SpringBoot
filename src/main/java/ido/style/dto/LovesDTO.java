package ido.style.dto;

import ido.style.searchApi.ProductNaverShopDTO;
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
//    private ProductDTO product;
    private ProductNaverShopDTO product;

}
