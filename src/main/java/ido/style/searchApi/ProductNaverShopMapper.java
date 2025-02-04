package ido.style.searchApi;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductNaverShopMapper {

    void insertProducts(List<ProductNaverShopDTO> products);  // XML에서 처리할 메서드
}